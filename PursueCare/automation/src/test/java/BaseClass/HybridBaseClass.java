package BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Hybrid Base Class for tests that need both Web and Mobile drivers.
 * This class manages both WebDriver and AppiumDriver instances simultaneously.
 * 
 * Usage:
 * - Extend this class in your hybrid test classes
 * - Use webDriver for web interactions
 * - Use mobileDriver for mobile app interactions
 * 
 * @author Automation Team
 */
public class HybridBaseClass {

    // Web Driver
    protected static WebDriver driver;
    protected Properties p;
    
    // Mobile Driver
    protected static AppiumDriver mobileDriver;
    protected static AppiumDriverLocalService appiumService;
    protected Properties p1;
    private boolean appiumServiceStartedByUs = false; // Flag to track if we started the service
    
    // Common
    protected Logger logger;
    
    private static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723";

    /**
     * Setup method that initializes both Web and Mobile drivers.
     * 
     * @param browser Browser name for web (chrome, firefox, edge)
     * @param headless Headless mode for web browser
     * @param platform Mobile platform (android, ios)
     * @param deviceName Mobile device name
     * @param appPath Mobile app path
     * @param udid Mobile device UDID
     * @throws IOException if config files cannot be read
     */
    @Parameters({ "browser", "headless", "platform", "deviceName", "appPath", "udid" })
    @BeforeClass
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") String headless,
            @Optional("android") String platform,
            @Optional("") String deviceName,
            @Optional("") String appPath,
            @Optional("") String udid) throws IOException {

        logger = LogManager.getLogger(this.getClass());

        // Initialize Web Driver
        setupWebDriver(browser, headless);
        
        // Initialize Mobile Driver
        setupMobileDriver(platform, deviceName, appPath, udid);
    }

    /**
     * Sets up the Web Driver.
     */
    private void setupWebDriver(String browser, String headless) throws IOException {
        FileReader webFile = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(webFile);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                
                boolean isHeadless = Boolean.parseBoolean(headless);
                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--use-fake-ui-for-media-stream");
                    options.addArguments("--use-fake-device-for-media-stream");
                }

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
                prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
                prefs.put("profile.default_content_setting_values.notifications", 1);
                prefs.put("profile.default_content_setting_values.popups", 1);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--use-fake-ui-for-media-stream");

                driver = new ChromeDriver(options);
                break;

            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                if (Boolean.parseBoolean(headless)) {
                    fOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(fOptions);
                break;

            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                if (Boolean.parseBoolean(headless)) {
                    eOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(eOptions);
                break;

            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get(p.getProperty("appURL_QA"));
        
        logger.info("Web Driver initialized: " + browser);
    }

    /**
     * Sets up the Mobile Driver.
     */
    private void setupMobileDriver(String platform, String deviceName, String appPath, String udid) 
            throws IOException, MalformedURLException {
        
        FileReader mobileFile = new FileReader("./src/test/resources/config_mobile.properties");
        p1 = new Properties();
        p1.load(mobileFile);

        if (deviceName.isEmpty()) {
            deviceName = p1.getProperty("mobile.deviceName", "");
        }
        if (appPath.isEmpty()) {
            appPath = p1.getProperty("mobile.appPath", "");
        }
        if (udid.isEmpty()) {
            udid = p1.getProperty("mobile.udid", "");
        }

        // Start Appium server (or use existing one)
        startAppiumServer();

        // Verify server is accessible (either we started it or it was already running)
        try {
            // Try to connect to verify server is running
            URL appiumUrl = new URL(DEFAULT_APPIUM_URL);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) appiumUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            connection.disconnect();
            logger.info("Appium server is accessible at " + DEFAULT_APPIUM_URL);
        } catch (Exception e) {
            logger.warn("Could not verify Appium server connection: " + e.getMessage());
            // Continue anyway - the driver initialization will fail if server is not available
        }

        // Initialize mobile driver
        switch (platform.toLowerCase()) {
            case "android":
                mobileDriver = initializeAndroidDriver(deviceName, appPath, udid);
                break;

            case "ios":
                mobileDriver = initializeIOSDriver(deviceName, appPath, udid);
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid platform: " + platform + ". Use 'android' or 'ios'");
        }

        mobileDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        logger.info("Mobile Driver initialized: " + platform);
    }

    /**
     * Initializes Android Driver.
     */
    private AndroidDriver initializeAndroidDriver(String deviceName, String appPath, String udid)
            throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        if (!deviceName.isEmpty()) {
            options.setDeviceName(deviceName);
        }

        if (!udid.isEmpty()) {
            options.setUdid(udid);
        }

        if (!appPath.isEmpty()) {
            options.setApp(appPath);
        } else {
            String appPackage = p1.getProperty("mobile.android.appPackage", "");
            String appActivity = p1.getProperty("mobile.android.appActivity", "");
            if (!appPackage.isEmpty() && !appActivity.isEmpty()) {
                options.setAppPackage(appPackage);
                options.setAppActivity(appActivity);
            }
        }

        options.setNoReset(Boolean.parseBoolean(p1.getProperty("mobile.android.noReset", "false")));
        options.setFullReset(Boolean.parseBoolean(p1.getProperty("mobile.android.fullReset", "false")));
        options.setAutoGrantPermissions(
                Boolean.parseBoolean(p1.getProperty("mobile.android.autoGrantPermissions", "true")));

        String platformVersion = p1.getProperty("mobile.android.platformVersion", "");
        if (!platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        options.setCapability("appium:disableIdLocatorAutocompletion", true);

        return new AndroidDriver(new URL(DEFAULT_APPIUM_URL), options);
    }

    /**
     * Initializes iOS Driver.
     */
    private IOSDriver initializeIOSDriver(String deviceName, String appPath, String udid)
            throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setAutomationName("XCUITest");

        if (!deviceName.isEmpty()) {
            options.setDeviceName(deviceName);
        }

        if (!udid.isEmpty()) {
            options.setUdid(udid);
        }

        if (!appPath.isEmpty()) {
            options.setApp(appPath);
        } else {
            String bundleId = p1.getProperty("mobile.ios.bundleId", "");
            if (!bundleId.isEmpty()) {
                options.setBundleId(bundleId);
            }
        }

        options.setNoReset(Boolean.parseBoolean(p1.getProperty("mobile.ios.noReset", "false")));
        options.setFullReset(Boolean.parseBoolean(p1.getProperty("mobile.ios.fullReset", "false")));
        options.setAutoAcceptAlerts(
                Boolean.parseBoolean(p1.getProperty("mobile.ios.autoAcceptAlerts", "true")));

        String platformVersion = p1.getProperty("mobile.ios.platformVersion", "");
        if (!platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        options.setCapability("appium:disableIdLocatorAutocompletion", true);

        return new IOSDriver(new URL(DEFAULT_APPIUM_URL), options);
    }

    /**
     * Starts Appium server or uses existing one if already running.
     */
    private void startAppiumServer() {
        try {
            // Check if Appium server is already running
            URL appiumUrl = new URL(DEFAULT_APPIUM_URL);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) appiumUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.connect();
            connection.disconnect();
            
            // Server is already running
            logger.info("Appium server is already running on " + DEFAULT_APPIUM_URL + ". Using existing server.");
            appiumServiceStartedByUs = false;
            return;
        } catch (Exception e) {
            // Server is not running, start a new one
            logger.info("Appium server not detected. Starting new Appium server...");
        }
        
        try {
            appiumService = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(() -> "--base-path", "/")
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                    .build();

            appiumService.start();
            appiumServiceStartedByUs = true;
            logger.info("Appium server started successfully on " + DEFAULT_APPIUM_URL);
        } catch (Exception e) {
            logger.warn("Failed to start Appium server: " + e.getMessage());
            logger.info("Assuming Appium server is already running externally. Continuing...");
            appiumServiceStartedByUs = false;
        }
    }

    /**
     * Cleanup method that closes both drivers.
     */
    @AfterClass
    public void tearDown() {
        // Close Web Driver
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Web Driver closed");
            } catch (Exception e) {
                logger.warn("Error closing Web Driver: " + e.getMessage());
            }
        }

        // Close Mobile Driver
        if (mobileDriver != null) {
            try {
                // Check if session is still valid before quitting
                try {
                    mobileDriver.getSessionId();
                    mobileDriver.quit();
                    logger.info("Mobile Driver closed");
                } catch (NoSuchSessionException e) {
                    logger.info("Mobile Driver session already terminated");
                }
            } catch (Exception e) {
                logger.warn("Error closing Mobile Driver: " + e.getMessage());
            }
        }

        // Stop Appium service only if we started it
        if (appiumServiceStartedByUs && appiumService != null) {
            try {
                if (appiumService.isRunning()) {
                    appiumService.stop();
                    logger.info("Appium service stopped");
                }
            } catch (Exception e) {
                logger.warn("Error stopping Appium service: " + e.getMessage());
            }
        } else if (appiumService != null && appiumService.isRunning()) {
            logger.info("Appium service was running externally, not stopping it");
        }
    }

    /**
     * Captures screenshot from Web Driver.
     */
    public String captureWebScreen(String sname) throws IOException {
        String timestamp = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss")
                .format(LocalDateTime.now());
        Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "screenshot", "web");
        Files.createDirectories(screenshotsDir);

        Path target = screenshotsDir.resolve(sname + "_" + timestamp + ".png");
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.write(target, screenshotBytes);
        return target.toString();
    }

    /**
     * Captures screenshot from Mobile Driver.
     */
    public String captureMobileScreen(String sname) throws IOException {
        String timestamp = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss")
                .format(LocalDateTime.now());
        Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "screenshot", "mobile");
        Files.createDirectories(screenshotsDir);

        Path target = screenshotsDir.resolve(sname + "_" + timestamp + ".png");
        byte[] screenshotBytes = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.BYTES);
        Files.write(target, screenshotBytes);
        return target.toString();
    }

    // Getters for drivers
    public static WebDriver getWebDriver() {
        return driver;
    }

    public static AppiumDriver getMobileDriver() {
        return mobileDriver;
    }
}

