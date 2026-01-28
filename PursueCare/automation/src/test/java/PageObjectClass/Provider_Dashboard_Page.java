package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class Provider_Dashboard_Page extends BasePageClass{

    WebDriver driver;

    public Provider_Dashboard_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    // Basic checkes Provider Dashboard Page

    @FindBy(xpath = "//span[normalize-space()='Dashboard']")
    public WebElement ClickOnDashboardHome;

    @FindBy(xpath = "//h4[normalize-space()='Dashboard']")
    public WebElement dashBoardText;

    @FindBy(xpath = "//h4[normalize-space()='Dashboard']")
    public WebElement CarePortalText;

    @FindBy(xpath = "//mat-label[normalize-space()='Select Timezone']")
    public WebElement SelectTimeZoneDashText;

    @FindBy(xpath = "//mat-icon[normalize-space()='fullscreen']")
    public WebElement SelectFullScreenIcon;

    @FindBy(xpath = "//mat-icon[normalize-space()='chat']")
    public WebElement SelectChatIcon;

    @FindBy(xpath = "//img[@alt='Lightning Step']")
    public WebElement LighteningStepNavigation;

    
    @FindBy(xpath = "//img[@alt='PursueCare Logo']")
    public WebElement PursueCareLogo;

    @FindBy(xpath = "//mat-icon[normalize-space()='menu']")
    public WebElement BreadCumbMenuIcon;



   // =======================================================Todays Appointments List Checks=======================================================
    @FindBy(xpath = "//div[@class='row clearfix']//h2[1]")
    public WebElement todaysAppointment;

    @FindBy(xpath = "//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//select[1]")
    public WebElement meetingStatus;

    @FindBy(xpath = "//div[@class='row clearfix']//div[3]//select[1]")
    public WebElement state;

    @FindBy(xpath = "//div[4]//select[1]")
    public WebElement medicalNSR;

    @FindBy(xpath = "//div[@class='row clearfix']//div[5]//select[1]")
    public WebElement counsellingNSR;

    @FindBy(xpath = "//div[6]//select[1]")
    public WebElement appointmentStatus;

    @FindBy(xpath = "//div[7]//select[1]")
    public WebElement program;

    @FindBy(xpath = "//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12']//a[@class='custom-cursor-pointer reset-filter'][normalize-space()='Reset Filter']")
    public WebElement resetFilterTodaysAppointment;
    
    @FindBy(xpath = "//div[@class='table-responsive'][1]//table[1]/tbody/tr/td[11]/table/td[3]/button")
    public WebElement actionButton;
    
    @FindBy(xpath = "//span[contains(text(),'Canceled by Staff')]")
    public WebElement cancelByStaff;

    @FindBy(xpath = "//span[contains(text(),'Completed Successfully')]")
    public WebElement completedSuccessfully;

    @FindBy(xpath = "//span[contains(text(),'Patient No Show')]")
    public WebElement patientNoShow;

    @FindBy(xpath = "//span[contains(text(),'Not Completed - Tech Issue')]")
    public WebElement notCompletedTechIssue;

    @FindBy(xpath = "//span[contains(text(),'Edit Group Session')]")
    public WebElement editGroupSession;

    

    //Completed Patient Assessments
    @FindBy(xpath="/html[1]/body[1]/app-root[1]/app-dashboard[1]/section[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/select[1]")
    public WebElement ClickProgrm;
    
    @FindBy(xpath="//option[normalize-space()='Assessment']/parent::select")
    public WebElement CleckAssessment;
    
    @FindBy(xpath="//button[@aria-label='Open calendar']//span[@class='mat-mdc-button-touch-target']")
    public WebElement ClickDateIcon;
    
    @FindBy(xpath="//span[@class='mat-calendar-body-cell-content mat-focus-indicator mat-calendar-body-today']")
    public WebElement ClickTodayDate;
    
    @FindBy(xpath="//div[@class='col-xl-12 col-lg-12 col-md-12 col-sm-12']//a[@class='custom-cursor-pointer reset-filter'][normalize-space()='Reset Filter']")
    public WebElement ClickResetFilterCompletedPatientAssessments;

    @FindBy(xpath="//tbody/tr[1]/td[5]/table[1]/td[1]/button[1]/mat-icon[1]")
    public WebElement ClickOnViewPatientResponse;
    
    @FindBy(xpath="//tbody/tr[1]/td[1]/span[1]")
    public WebElement getPatientName;
    
    @FindBy(xpath="//div[@class='row label col-md-12 m-l-5 color-black legendcursor']/strong")
    public WebElement ValidatePatientNameFromAssessScreen;
    
    @FindBy(xpath="//button[@mattooltip='Close']")
    public WebElement CuttheAssessmentScreen;

    //TimeZone Related
    @FindBy(xpath="//mat-select[@role='combobox'][1]")
    public WebElement TimeZoneDrpDwn;

    @FindBy(xpath="//span[normalize-space()='MST']")
    public WebElement SelectMSTimeZone;

    @FindBy(xpath="//span[normalize-space()='EST']")
    public WebElement SelectESTimeZone;

    // Get Appointment Time For TimeZone Checks
    @FindBy(xpath="//div[@class='table-responsive'][1]//table[1]/tbody/tr/td[8]")
    public WebElement ProviderAppointmentTime;

   // Click Appointments Reports Page

   @FindBy(xpath="//span[normalize-space()='Reports']")
   public WebElement ClickReportsDash;

   @FindBy(xpath="//a[normalize-space()='Appointment Report']")
   public WebElement ClickAppointmentReportDash;



    public boolean compareTimeRanges(String estRange, String mstRange) {
        // Formatter for parsing input like "1:30 PM"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        // Split ranges
        String[] estParts = estRange.split("-");
        String[] mstParts = mstRange.split("-");

        if (estParts.length != 2 || mstParts.length != 2) {
            throw new IllegalArgumentException("Invalid range format. Expected format: h:mm a-h:mm a");
        }

        // Parse start & end times
        LocalTime estStart = LocalTime.parse(estParts[0].trim(), formatter);
        LocalTime estEnd   = LocalTime.parse(estParts[1].trim(), formatter);
        LocalTime mstStart = LocalTime.parse(mstParts[0].trim(), formatter);
        LocalTime mstEnd   = LocalTime.parse(mstParts[1].trim(), formatter);

        // Assign today's date arbitrarily (date not provided, but required for ZonedDateTime)
        ZonedDateTime estStartZoned = estStart.atDate(java.time.LocalDate.now()).atZone(ZoneId.of("America/New_York"));
        ZonedDateTime estEndZoned   = estEnd.atDate(java.time.LocalDate.now()).atZone(ZoneId.of("America/New_York"));

        ZonedDateTime mstStartZoned = mstStart.atDate(java.time.LocalDate.now()).atZone(ZoneId.of("America/Denver"));
        ZonedDateTime mstEndZoned   = mstEnd.atDate(java.time.LocalDate.now()).atZone(ZoneId.of("America/Denver"));

        // Compare both start & end instants
        return estStartZoned.toInstant().equals(mstStartZoned.toInstant()) &&
               estEndZoned.toInstant().equals(mstEndZoned.toInstant());
    }


}




