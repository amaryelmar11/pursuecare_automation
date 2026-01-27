package PageObjectClass.Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import BasePage.MobileBasePageClass;
import java.time.Duration;

/**
 * Page Object Class for Mobile Appointment flow.
 * This class contains all the mobile elements and methods related to:
 * - Requesting a new appointment
 * - Selecting slot date and time
 * - Submitting appointment request
 * - Validating success confirmation
 * 
 * Note: Use @AndroidFindBy for Android-specific locators
 *       Use @iOSXCUITFindBy for iOS-specific locators
 *       Use @FindBy for common locators that work on both platforms
 * 
 * @author Automation Team
 */
public class AppointmentflowPage extends MobileBasePageClass {

    AppiumDriver driver;

    /**
     * Constructor to initialize the AppointmentflowPage.
     * 
     * @param driver AppiumDriver instance
     */
    public AppointmentflowPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        // Initialize PageFactory with AppiumFieldDecorator for mobile
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    // ==================== Appointment Flow Elements ====================
    
    /** Request a new appointment text view - Android */
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Request A New Appointment\")")
    public WebElement RequestNewAppt;

    /** Request appointment button - Android */
    @AndroidFindBy(id = "com.pursuecare.patientapp:id/book_appointment_btn")
    public WebElement RequestApptButton;

    /** Slot date (Jan, 20) selection - Android */
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Jan, 20\"]")
    public WebElement ClickOnSlotDate;

    /** First available slot selection - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Available Slots\"]/following-sibling::android.view.ViewGroup[1]")
    public WebElement ClickonSlot;

    /** Request appointment submission - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Request Appointment\"]")
    public WebElement ClickonRequestAppt;

    /** Request sent success message - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Request sent successfully!\"]")
    public WebElement validateRequestSuccessText;

    /** Got it button on success popup - Android */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Got it!\"]")
    public WebElement ClickOnGotItPopup;
}
