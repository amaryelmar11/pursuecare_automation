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
import org.testng.annotations.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
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
import java.util.Properties;

public class MobileBaseClass {

    protected static AppiumDriver driver;
    protected static AppiumDriverLocalService service;
    protected Properties p;
    protected Logger logger;

    private static final String DEFAULT_APPIUM_URL = "http://127.0.0.1:4723";

    @Parameters({ "platform", "deviceName", "appPath", "udid" })
    @BeforeClass
    public void setup(
            String platform,
            @Optional("") String deviceName,
            @Optional("") String appPath,
            @Optional("") String udid) throws IOException {

        logger = LogManager.getLogger(this.getClass());

        // Load mobile config
        FileReader file = new FileReader("./src/test/resources/config_mobile.properties");
        p = new Properties();
        p.load(file);

        if (deviceName.isEmpty()) {
            deviceName = p.getProperty("mobile.deviceName", "");
        }
        if (appPath.isEmpty()) {
            appPath = p.getProperty("mobile.appPath", "");
        }
        if (udid.isEmpty()) {
            udid = p.getProperty("mobile.udid", "");
        }

        // Start Appium server (SINGLE source of truth)
        startAppiumServer();

        if (!service.isRunning()) {
            throw new RuntimeException("Appium server failed to start");
        }

        // Initialize driver
        switch (platform.toLowerCase()) {
            case "android":
                driver = initializeAndroidDriver(deviceName, appPath, udid);
                break;

            case "ios":
                driver = initializeIOSDriver(deviceName, appPath, udid);
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid platform: " + platform + ". Use 'android' or 'ios'");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /* -------------------- ANDROID DRIVER -------------------- */

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
            String appPackage = p.getProperty("mobile.android.appPackage", "");
            String appActivity = p.getProperty("mobile.android.appActivity", "");
            if (!appPackage.isEmpty() && !appActivity.isEmpty()) {
                options.setAppPackage(appPackage);
                options.setAppActivity(appActivity);
            }
        }

        options.setNoReset(Boolean.parseBoolean(p.getProperty("mobile.android.noReset", "false")));
        options.setFullReset(Boolean.parseBoolean(p.getProperty("mobile.android.fullReset", "false")));
        options.setAutoGrantPermissions(
                Boolean.parseBoolean(p.getProperty("mobile.android.autoGrantPermissions", "true")));

        String platformVersion = p.getProperty("mobile.android.platformVersion", "");
        if (!platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        return new AndroidDriver(new URL(DEFAULT_APPIUM_URL), options);
    }

    /* -------------------- IOS DRIVER -------------------- */

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
            String bundleId = p.getProperty("mobile.ios.bundleId", "");
            if (!bundleId.isEmpty()) {
                options.setBundleId(bundleId);
            }
        }

        options.setNoReset(Boolean.parseBoolean(p.getProperty("mobile.ios.noReset", "false")));
        options.setFullReset(Boolean.parseBoolean(p.getProperty("mobile.ios.fullReset", "false")));
        options.setAutoAcceptAlerts(
                Boolean.parseBoolean(p.getProperty("mobile.ios.autoAcceptAlerts", "true")));

        String platformVersion = p.getProperty("mobile.ios.platformVersion", "");
        if (!platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        return new IOSDriver(new URL(DEFAULT_APPIUM_URL), options);
    }

    /* -------------------- APPIUM SERVER -------------------- */

    private void startAppiumServer() {

        service = new AppiumServiceBuilder()
        .withIPAddress("127.0.0.1")
        .usingPort(4723)
        .withArgument(() -> "--base-path", "/")
        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
        .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
        .build();

        service.start();
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    /* -------------------- SCREENSHOT -------------------- */

    public String captureScreen(String sname) throws IOException {

        String timestamp = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss")
                .format(LocalDateTime.now());

        Path screenshotsDir = Paths.get(System.getProperty("user.dir"),
                "screenshot", "mobile");
        Files.createDirectories(screenshotsDir);

        Path target = screenshotsDir.resolve(sname + "_" + timestamp + ".png");
        byte[] screenshotBytes =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        Files.write(target, screenshotBytes);
        return target.toString();
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
