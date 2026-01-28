package PageObjectClass.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import BasePage.MobileBasePageClass;
import java.time.Duration;

/**
 * Page Object Class for Mobile Zoom functionality.
 * This class contains all the mobile elements and methods related to:
 * - Zoom session interactions
 * - Zoom controls and buttons
 * - Mobile-specific Zoom interactions
 * 
 * Note: Use @AndroidFindBy for Android-specific locators
 *       Use @iOSXCUITFindBy for iOS-specific locators
 *       Use @FindBy for common locators that work on both platforms
 * 
 * @author Automation Team
 */
public class Mobile_Zoom_Page extends MobileBasePageClass {

    AppiumDriver driver;

    /**
     * Constructor to initialize the Mobile_Zoom_Page.
     * 
     * @param driver AppiumDriver instance
     */
    public Mobile_Zoom_Page(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        // Initialize PageFactory with AppiumFieldDecorator for mobile
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // ==================== Zoom Elements ====================
    
    /** Zoom session SVG PathView element - Android */
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
    public WebElement ZoomSessionPathView;

    // ==================== Zoom Methods ====================
    
    /**
     * Clicks on the Zoom session PathView element.
     */
    public void clickZoomSessionPathView() {
        ZoomSessionPathView.click();
    }

    /**
     * Verifies if the Zoom session PathView is visible.
     * 
     * @return true if Zoom session PathView is visible, false otherwise
     */
    public boolean isZoomSessionPathViewVisible() {
        return ZoomSessionPathView.isDisplayed();
    }
}

