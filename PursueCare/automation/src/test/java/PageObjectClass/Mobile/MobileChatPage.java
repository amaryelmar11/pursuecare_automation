package PageObjectClass.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import BasePage.MobileBasePageClass;
import java.time.Duration;

/**
 * Page Object Class for Mobile Chat functionality.
 * This class contains all the mobile elements and methods related to:
 * - Chat interactions
 * - Sending messages
 * - Requesting appointments
 * - Adding images/videos/documents
 * - Camera interactions
 * 
 * Note: Use @AndroidFindBy for Android-specific locators
 *       Use @iOSXCUITFindBy for iOS-specific locators
 *       Use @FindBy for common locators that work on both platforms
 * 
 * @author Automation Team
 */
public class MobileChatPage extends MobileBasePageClass {

    AppiumDriver driver;

    /**
     * Constructor to initialize the MobileChatPage.
     * 
     * @param driver AppiumDriver instance
     */
    public MobileChatPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        // Initialize PageFactory with AppiumFieldDecorator for mobile
        // Reduced timeout from 10s to 5s for faster element lookup
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    // ==================== Chat Navigation Elements ====================
    
    /** Chat text view on dashboard - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Chat\"]")
    public WebElement ChatclickDashboard;

    /** Chat provider view group - Android */
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Chat\"]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView[2]")
    public WebElement ClickChatProvider;

    // ==================== Chat Message Elements ====================
    
    /** Message input field - Android */
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"Type your message here\"]")
    public WebElement EntertheMessage;

    /** Send chat message button (SVG PathView) - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"GC_WRAPPER\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[3]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
    public WebElement SendChatMsg1;

    /** Navigation bar background for send message - Android */
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='android:id/navigationBarBackground']")
    public WebElement SendChatMsg2;

    /** Chat element count text view - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, 'Chat')]/android.view.ViewGroup[1]/android.widget.TextView")
    public WebElement GetChatELementCount;

    /** Provider name text view - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Grandy Sand\"]")
    public WebElement GetProviderName;

    // ==================== Appointment Request Elements ====================
    
    /** Request appointment button in chat section - Android */
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Request Appointment\"])[1]")
    public WebElement ClickRequestApptChatSection;

    // ==================== Media Attachment Elements ====================
    
    /** Add image/video button (SVG PathView) - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"GC_WRAPPER\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
    public WebElement AddImageVideo;

    /** Image/Video option text view - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"IMAGE/VIDEO\"]")
    public WebElement ClickImageVideoOption;

    // ==================== Camera Elements ====================
    
    /** Camera option text view - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CAMERA\"]")
    public WebElement ClickOnCamera;

    /** Take photo button - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Take photo. Button. Double-tap to take a photo. Double-tap and hold to take burst photos\"]")
    public WebElement ClickOnTakePhoto;

    /** OK button in camera - Android */
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.camera:id/done_button\"]")
    public WebElement ClickOnOkButton;

    // ==================== Document Elements ====================
    
    /** Document option text view - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"DOCUMENT\"]")
    public WebElement ClickOnDocument;

    /** Select document icon - Android */
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.google.android.documentsui:id/icon_thumb\"])[1]")
    public WebElement SelectDocument;

    // ==================== Chat Methods ====================
    
   

   
}
