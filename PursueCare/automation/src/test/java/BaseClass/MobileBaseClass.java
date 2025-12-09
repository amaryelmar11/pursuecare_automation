package BaseClass;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * Base class for Mobile automation using Appium.
 * This class handles:
 * - Android and iOS driver initialization
 * - Appium server management
 * - Mobile-specific capabilities configuration
 * - Screenshot capture for mobile
 * 
 * @author Automation Team
 */
public class MobileBaseClass {

    public static AppiumDriver driver;
    public Properties p;
    public static org.apache.logging.log4j.Logger logger;
    private AppiumDriverLocalService service;
    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    /**
     * Setup method to initialize mobile driver based on platform.
     * 
     * @param platform Platform type: "android" or "ios"
     * @param deviceName Name of the device/emulator
     * @param appPath Path to the app file (.apk for Android, .ipa for iOS)
     * @param udid Device UDID (optional)
     * @param appiumServerUrl Appium server URL (optional, defaults to localhost:4723)
     * @throws IOException if config file cannot be read
     * @throws MalformedURLException if Appium server URL is invalid
     */
    @Parameters({ "platform", "deviceName", "appPath", "udid", "appiumServerUrl" })
    @BeforeClass
    public void setup(
            String platform,
            @Optional("") String deviceName,
            @Optional("") String appPath,
            @Optional("") String udid,
            @Optional(APPIUM_SERVER_URL) String appiumServerUrl) throws IOException, MalformedURLException {

        logger = LogManager.getLogger(this.getClass());

        // Load configuration properties
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Use properties if parameters are not provided
        if (deviceName.isEmpty()) {
            deviceName = p.getProperty("mobile.deviceName", "");
        }
        if (appPath.isEmpty()) {
            appPath = p.getProperty("mobile.appPath", "");
        }
        if (udid.isEmpty()) {
            udid = p.getProperty("mobile.udid", "");
        }

        // Start Appium server if needed (optional - can be started manually)
        // Uncomment the line below if you want to start Appium server programmatically
        // startAppiumServer();

        // Initialize driver based on platform
        switch (platform.toLowerCase()) {
            case "android":
                driver = initializeAndroidDriver(deviceName, appPath, udid, appiumServerUrl);
                break;

            case "ios":
                driver = initializeIOSDriver(deviceName, appPath, udid, appiumServerUrl);
                break;

            default:
                throw new IllegalArgumentException("Invalid platform: " + platform + ". Use 'android' or 'ios'");
        }

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Initializes Android driver with required capabilities.
     * 
     * @param deviceName Device/emulator name
     * @param appPath Path to .apk file
     * @param udid Device UDID
     * @param appiumServerUrl Appium server URL
     * @return AndroidDriver instance
     * @throws MalformedURLException if URL is invalid
     */
    private AndroidDriver initializeAndroidDriver(String deviceName, String appPath, String udid, String appiumServerUrl)
            throws MalformedURLException {
        
        io.appium.java_client.android.options.UiAutomator2Options options = 
            new io.appium.java_client.android.options.UiAutomator2Options();

        // Basic capabilities
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        
        if (!deviceName.isEmpty()) {
            options.setDeviceName(deviceName);
        }
        
        if (!udid.isEmpty()) {
            options.setUdid(udid);
        }

        // App capabilities
        if (!appPath.isEmpty()) {
            options.setApp(appPath);
        } else {
            // If no app path, use package and activity for existing app
            String appPackage = p.getProperty("mobile.android.appPackage", "");
            String appActivity = p.getProperty("mobile.android.appActivity", "");
            if (!appPackage.isEmpty() && !appActivity.isEmpty()) {
                options.setAppPackage(appPackage);
                options.setAppActivity(appActivity);
            }
        }

        // Additional Android capabilities
        options.setNoReset(Boolean.parseBoolean(p.getProperty("mobile.android.noReset", "false")));
        options.setFullReset(Boolean.parseBoolean(p.getProperty("mobile.android.fullReset", "false")));
        options.setAutoGrantPermissions(Boolean.parseBoolean(p.getProperty("mobile.android.autoGrantPermissions", "true")));
        
        // Optional capabilities
        String platformVersion = p.getProperty("mobile.android.platformVersion", "");
        if (!platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        return new AndroidDriver(new URL(appiumServerUrl), options);
    }

    /**
     * Initializes iOS driver with required capabilities.
     * 
     * @param deviceName Device/simulator name
     * @param appPath Path to .ipa file
     * @param udid Device UDID
     * @param appiumServerUrl Appium server URL
     * @return IOSDriver instance
     * @throws MalformedURLException if URL is invalid
     */
    private IOSDriver initializeIOSDriver(String deviceName, String appPath, String udid, String appiumServerUrl)
            throws MalformedURLException {
        
        io.appium.java_client.ios.options.XCUITestOptions options = 
            new io.appium.java_client.ios.options.XCUITestOptions();

        // Basic capabilities
        options.setPlatformName("iOS");
        options.setAutomationName("XCUITest");
        
        if (!deviceName.isEmpty()) {
            options.setDeviceName(deviceName);
        }
        
        if (!udid.isEmpty()) {
            options.setUdid(udid);
        }

        // App capabilities
        if (!appPath.isEmpty()) {
            options.setApp(appPath);
        } else {
            // If no app path, use bundle ID for existing app
            String bundleId = p.getProperty("mobile.ios.bundleId", "");
            if (!bundleId.isEmpty()) {
                options.setBundleId(bundleId);
            }
        }

        // Additional iOS capabilities
        options.setNoReset(Boolean.parseBoolean(p.getProperty("mobile.ios.noReset", "false")));
        options.setFullReset(Boolean.parseBoolean(p.getProperty("mobile.ios.fullReset", "false")));
        options.setAutoAcceptAlerts(Boolean.parseBoolean(p.getProperty("mobile.ios.autoAcceptAlerts", "true")));
        
        // Optional capabilities
        String platformVersion = p.getProperty("mobile.ios.platformVersion", "");
        if (!platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        return new IOSDriver(new URL(appiumServerUrl), options);
    }

    /**
     * Starts Appium server programmatically (optional).
     * Comment out if you prefer to start Appium server manually.
     */
    private void startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    /**
     * Stops Appium server if started programmatically.
     */
    private void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    /**
     * Cleanup method to close driver and stop Appium server.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        stopAppiumServer();
    }

    /**
     * Captures screenshot for mobile tests.
     * 
     * @param sname Screenshot name prefix
     * @return Path to saved screenshot
     * @throws IOException if screenshot cannot be saved
     */
    public String captureScreen(String sname) throws IOException {
        String timestamp = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss").format(LocalDateTime.now());
        Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "screenshot", "mobile");
        Files.createDirectories(screenshotsDir);

        String fileName = sname + "_" + timestamp + ".png";
        Path target = screenshotsDir.resolve(fileName);

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.write(target, screenshotBytes);
        return target.toString();
    }

    /**
     * Gets the current Appium driver instance.
     * 
     * @return AppiumDriver instance
     */
    public static AppiumDriver getDriver() {
        return driver;
    }
}

