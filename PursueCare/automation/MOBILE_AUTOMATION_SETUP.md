# Mobile Automation Setup Guide

This guide explains how to set up and use Appium mobile automation in the PursueCare automation framework.

## Prerequisites

### 1. Install Required Software

#### For Android:
- **Java JDK 17** (already installed)
- **Android Studio** - Download from https://developer.android.com/studio
- **Android SDK** - Install via Android Studio
- **Appium** - Install via npm: `npm install -g appium`
- **Appium Doctor** - Install via npm: `npm install -g appium-doctor`
- **UiAutomator2 Driver** - Install via Appium: `appium driver install uiautomator2`

#### For iOS (Mac only):
- **Xcode** - Install from App Store
- **Xcode Command Line Tools**: `xcode-select --install`
- **CocoaPods**: `sudo gem install cocoapods`
- **XCUITest Driver** - Install via Appium: `appium driver install xcuitest`
- **Carthage** (if needed): `brew install carthage`

### 2. Environment Variables

#### Android:
Add to your system PATH:
```
ANDROID_HOME = C:\Users\<YourUser>\AppData\Local\Android\Sdk
PATH = %ANDROID_HOME%\platform-tools;%ANDROID_HOME%\tools;%ANDROID_HOME%\tools\bin
```

#### iOS:
No additional environment variables needed (uses Xcode defaults)

### 3. Verify Installation

Run Appium Doctor to verify setup:
```bash
appium doctor
```

For Android specifically:
```bash
appium doctor --android
```

## Configuration

### 1. Update config.properties

Edit `src/test/resources/config.properties` and configure:

```properties
# Appium Server
mobile.appiumServerUrl = http://127.0.0.1:4723

# Android Settings
mobile.android.deviceName = emulator-5554
mobile.android.platformVersion = 13.0
mobile.android.appPath = path/to/your/app.apk
mobile.android.appPackage = com.pursuecare.app
mobile.android.appActivity = com.pursuecare.app.MainActivity
mobile.android.autoGrantPermissions = true

# iOS Settings
mobile.ios.deviceName = iPhone 14
mobile.ios.platformVersion = 16.0
mobile.ios.appPath = path/to/your/app.ipa
mobile.ios.bundleId = com.pursuecare.app
```

### 2. Device/Emulator Setup

#### Android:
1. Start Android Studio
2. Open AVD Manager
3. Create a new Virtual Device or use existing one
4. Start the emulator
5. Verify device is connected: `adb devices`

#### iOS:
1. Open Xcode
2. Go to Window > Devices and Simulators
3. Start a simulator
4. Verify device: `xcrun simctl list devices`

## Running Tests

### 1. Start Appium Server

**Option A: Command Line**
```bash
appium
```

**Option B: Appium Desktop**
- Download from https://github.com/appium/appium-desktop/releases
- Launch Appium Desktop
- Click "Start Server"

### 2. Run Tests via TestNG XML

**Using Maven:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/Mobile_Test_Suite.xml
```

**Using IDE:**
- Right-click on `Mobile_Test_Suite.xml`
- Select "Run As" > "TestNG Suite"

### 3. Run Tests Programmatically

The framework can start Appium server automatically (currently commented out in `MobileBaseClass.java`).

## Framework Structure

### Base Classes

1. **MobileBaseClass** (`BaseClass/MobileBaseClass.java`)
   - Handles driver initialization
   - Manages Appium server (optional)
   - Provides screenshot functionality
   - Supports Android and iOS

2. **MobileBasePageClass** (`BasePage/MobileBasePageClass.java`)
   - Base class for mobile page objects
   - Initializes PageFactory for mobile

### Page Objects

- Location: `PageObjectClass/Mobile/`
- Use `@AndroidFindBy` for Android-specific locators
- Use `@iOSXCUITFindBy` for iOS-specific locators
- Use `@FindBy` for common locators

### Test Classes

- Location: `testCases_Mobile/`
- Extend `MobileBaseClass`
- Initialize page objects in `@BeforeClass`

## Locator Strategies

### Android
- **ID**: `@AndroidFindBy(id = "com.example:id/button")`
- **XPath**: `@AndroidFindBy(xpath = "//android.widget.Button[@text='Login']")`
- **Accessibility ID**: `@AndroidFindBy(accessibility = "Login")`
- **Class Name**: `@AndroidFindBy(className = "android.widget.Button")`

### iOS
- **ID**: `@iOSXCUITFindBy(id = "LoginButton")`
- **XPath**: `@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")`
- **Accessibility ID**: `@iOSXCUITFindBy(accessibility = "Login")`
- **Class Name**: `@iOSXCUITFindBy(className = "XCUIElementTypeButton")`

## Common Mobile Actions

### Finding Elements
```java
// Wait for element
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("button")));
```

### Scrolling
```java
// Android
((AndroidDriver) driver).findElementByAndroidUIAutomator(
    "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Element\"))");

// iOS
JavascriptExecutor js = (JavascriptExecutor) driver;
HashMap<String, String> scrollObject = new HashMap<String, String>();
scrollObject.put("direction", "down");
js.executeScript("mobile: scroll", scrollObject);
```

### Swipe Actions
```java
// Use TouchAction or W3C Actions API
TouchAction action = new TouchAction((PerformsTouchActions) driver);
action.press(PointOption.point(startX, startY))
      .moveTo(PointOption.point(endX, endY))
      .release()
      .perform();
```

## Troubleshooting

### Common Issues

1. **Appium server not starting**
   - Check if port 4723 is available
   - Verify Appium installation: `appium --version`
   - Check logs for errors

2. **Device not detected**
   - Android: Run `adb devices` to verify connection
   - iOS: Check Xcode > Window > Devices and Simulators
   - Restart ADB: `adb kill-server && adb start-server`

3. **App not installing**
   - Verify app path is correct
   - Check app permissions
   - For Android: Enable "Install via USB" in developer options

4. **Element not found**
   - Use Appium Inspector to verify locators
   - Check if element is in a different context (WebView vs Native)
   - Increase wait time

### Debugging Tips

1. **Use Appium Inspector**
   - Download from Appium Desktop
   - Connect to your device/emulator
   - Inspect elements and generate locators

2. **Enable Appium Logs**
   - Set log level in `MobileBaseClass.java`
   - Check console output for detailed logs

3. **Take Screenshots**
   - Use `captureScreen()` method from base class
   - Screenshots saved in `screenshot/mobile/` directory

## Best Practices

1. **Use Page Object Model** - Keep page objects separate from test logic
2. **Wait Strategies** - Always use explicit waits instead of Thread.sleep()
3. **Platform-Specific Code** - Use separate locators for Android and iOS
4. **Error Handling** - Implement try-catch blocks for flaky operations
5. **Test Data** - Store test data in config.properties or external files
6. **Screenshots** - Capture screenshots on test failures
7. **Device Management** - Use device UDID for specific device targeting

## Additional Resources

- [Appium Documentation](http://appium.io/docs/en/about-appium/intro/)
- [Appium Java Client](https://github.com/appium/java-client)
- [Android UI Automator](https://developer.android.com/training/testing/ui-automator)
- [iOS XCUITest](https://developer.apple.com/documentation/xctest/xcuielement)

## Support

For issues or questions, contact the automation team.

