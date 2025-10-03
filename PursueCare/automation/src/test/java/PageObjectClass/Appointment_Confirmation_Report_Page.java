package PageObjectClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class Appointment_Confirmation_Report_Page extends BasePageClass{

    WebDriver driver;
    private WebDriverWait wait;

    public Appointment_Confirmation_Report_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath = "//a[normalize-space()='Appointment Confirmation']")
    public WebElement clickAppointmentConfirReport;

    @FindBy(xpath = "//input[@name='Search']")
    public WebElement SearchInputbutton;

    @FindBy(xpath = "//mat-label[normalize-space()='Response']/parent::label/following-sibling::mat-select")
    public WebElement ClickResponseDrpDwn;

    @FindBy(xpath = "//mat-option[normalize-space()='Confirmed by Patient']")
    public WebElement ClickConfirmByPatientCheckbox;

    @FindBy(xpath = "//mat-label[normalize-space()='Meeting Host Type']/parent::label/following-sibling::mat-select")
    public WebElement ClickMeetingHostType;

    @FindBy(xpath = "//mat-option[normalize-space()='Provider']")
    public WebElement ClickProviderRole;

    @FindBy(xpath = "//mat-label[normalize-space()='Meeting Host(s)']/parent::label/following-sibling::mat-select/div/div/span/span")
    public WebElement CheckProviderNameInMeetingHost;

    @FindBy(xpath = "//mat-label[normalize-space()='Connected case manager']/parent::label/following-sibling::mat-select")
    public WebElement ClickConnectedCMDrpDwn;

    @FindBy(xpath = "//mat-option[normalize-space()='Akshay Avhad']")
    public WebElement SelectCMDrpDwn;

    @FindBy(xpath = "//mat-label[normalize-space()='Source']/parent::label/following-sibling::mat-select")
    public WebElement SelectSourceDrpDwn;

    @FindBy(xpath = "//mat-option[normalize-space()='SMS']")
    public WebElement ClickSMSOption;

    
    @FindBy(xpath = "//mat-label[normalize-space()='Entry Status']/parent::label/following-sibling::mat-select")
    public WebElement SelectEntryStatusDrpDwn;

    @FindBy(xpath = "//mat-option[normalize-space()='Checked off']")
    public WebElement ClickCheckedOffOption;

    @FindBy(xpath = "//div[@class='profile-usertitle']/div")
    public WebElement GetProviderNameDetails;


    //  Default Xpath
    @FindBy(xpath = "//mat-label[normalize-space()='Response']")
    public WebElement ResponseDrpDwnDeafaultValue;

    @FindBy(xpath = "//mat-label[normalize-space()='Meeting Host Type']")
    public WebElement MeetingHostDefaultValue;

    @FindBy(xpath = "//mat-label[normalize-space()='Connected case manager']")
    public WebElement ConnectedCMDefaultValue;

    @FindBy(xpath = "//mat-label[normalize-space()='Source']")
    public WebElement SourceDefaultValue;
    
    @FindBy(xpath = "//mat-label[normalize-space()='Entry Status']")
    public WebElement EntryStatusDefaultValue;
   


}
