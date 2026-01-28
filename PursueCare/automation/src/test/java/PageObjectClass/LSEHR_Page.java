package PageObjectClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class LSEHR_Page extends BasePageClass{


    WebDriver driver;
    private WebDriverWait wait;

    public LSEHR_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath = "//input[@id='email-input']")
    public WebElement EnterLsMailID;

    @FindBy(xpath = "//input[@id='password-input']")
    public WebElement EnterLsPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement ClickSignInBtn;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement SearchBtnLS;

    @FindBy(xpath = "//a[@href='#global_search_results_inquiries']")
    public WebElement ClickInquiriesOption;

    @FindBy(xpath = "//a[@id='fullSearchTable']")
    public WebElement ClickFullsearch;

    //Table Page LSEHR

    @FindBy(xpath = "//div[@id='tblInquiry_filter']/label/input")
    public WebElement ClickSearch;

    @FindBy(xpath = "//tbody/tr/td[1]/a")
    public WebElement ClickInquiryNameHyperlink;

    @FindBy(xpath = "//a[normalize-space()='Scheduling']")
    public WebElement ClickSchedulingSection;

    // Validating appointments

    @FindBy(xpath = "//table[@id='tblcalevents']/tbody/tr[1]/td[1]")
    public WebElement ValidateAppointmentDate;

    @FindBy(xpath = "//table[@id='tblcalevents']/tbody/tr[1]/td[2]")
    public WebElement ValidDayAppointment;

    @FindBy(xpath = "//table[@id='tblcalevents']/tbody/tr[1]/td[6]")
    public WebElement ValidateEventType;

    @FindBy(xpath = "//table[@id='tblcalevents']/tbody/tr[1]/td[7]")
    public WebElement ValidateStatus;

    @FindBy(xpath = "//table[@id='tblcalevents']/tbody/tr[1]/td[8]/a")
    public WebElement ClickEditBtn;

    @FindBy(xpath = "//div[@id='service_edit_section']/div/span/span/span/span")
    public WebElement ClickServiceType;

    @FindBy(xpath = "//div[@id='appointment_edit_section']/div/span/span/span/span[1]")
    public WebElement CLickClientId;

    @FindBy(xpath = "//span[normalize-space()='delete']")
    public WebElement ClickDeleteBtn;

    @FindBy(xpath = "//select[@name='event_status']")
    public WebElement ClickStatusDpDwn;

    @FindBy(xpath = "//a[normalize-space()='Save Revision']")
    public WebElement ClickSaveRevisionBtn;


}
