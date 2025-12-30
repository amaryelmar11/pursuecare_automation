package PageObjectClassCM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Dashboard_CM_Page extends BasePageClass {

    WebDriver driver;

    public Dashboard_CM_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    // Basic checks Case Manager Dashboard Page

    @FindBy(xpath = "//span[normalize-space()='Dashboard']")
    public WebElement ClickOnDashboardHome;

    @FindBy(xpath = "//h4[normalize-space()='Dashboard']")
    public WebElement dashBoardText;

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

    // Related to CM My Caseload
    @FindBy(xpath = "//option[normalize-space()='Next Appointment Scheduled']/parent::select")
    public WebElement ClickOnNextAppointmentScheduledDrpDwn;

    @FindBy(xpath = "//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//div[3]//select[1]")
    public WebElement ClickStatusDrpDwn;

    @FindBy(xpath = "//option[normalize-space()='Last tox screen']/parent::select")
    public WebElement ClickLastToxScreenDrpDwn;

    @FindBy(xpath = "//option[normalize-space()='Days in treatment']/parent::select")
    public WebElement ClickDaysInTreatmentDrpDwn;

    @FindBy(xpath = "//mat-label[normalize-space()='Speciality']")
    public WebElement ClickSpecialityDrpDwn;

    @FindBy(xpath = "//span[normalize-space()='PursueCareRx Pharmacist']")
    public WebElement SelectPursueCareRxPharmacistSpeciality;

    @FindBy(xpath = "//h2[normalize-space()='My Caseload']/parent::div/following-sibling::div/div/div/div[8]/a")
    public WebElement ClickResetFilterMyCaseload;

    @FindBy(xpath = "//h2[normalize-space()='My Caseload']/parent::div/ul/li/div/i")
    public WebElement ClickRefreshButton;

    @FindBy(xpath = "//table[@id='mycaseloadlist']/tbody/tr[1]/td[8]/button")
    public WebElement ClickActionButton;

    @FindBy(xpath = "//span[normalize-space()='View Upcoming Appointments']/parent::button/span/span")
    public WebElement GetViewUpcomingAppointmentsText;

    @FindBy(xpath = "//span[normalize-space()='View Patient Assessments']/parent::button/span/span")
    public WebElement GetViewPatientAssessmentsText;

    @FindBy(xpath = "//span[normalize-space()='View Patient Information']/parent::button/span/span")
    public WebElement GetViewPatientInformationText;

    // Todays Appointments List Checks
    @FindBy(xpath = "//table[@id='todaysappointmentslist']/tbody/tr[1]/td[9]/table/td[2]/button")
    public WebElement ActionBtnTodaysAppointment;

    @FindBy(xpath = "//table[@id='todaysappointmentslist']/parent::*/parent::*/parent::div/div[2]/div/div/div[3]/select")
    public WebElement StateFilterDrpDwn;

    @FindBy(xpath = "//table[@id='todaysappointmentslist']/parent::*/parent::*/parent::div/div[2]/div/div/div[4]/select")
    public WebElement MedicalNSRFilterDrpDwn;

    @FindBy(xpath = "//table[@id='todaysappointmentslist']/parent::*/parent::*/parent::div/div[2]/div/div/div[5]/select")
    public WebElement CounsellingNSRFilterDrpDwn;

    @FindBy(xpath = "//table[@id='todaysappointmentslist']/parent::*/parent::*/parent::div/div[2]/div/div/div[6]/select")
    public WebElement AppointmentStatusFilterDrpDwn;

    @FindBy(xpath = "//h2[normalize-space()=\"Today's Appointments\"]/parent::div/following-sibling::div/div/div/div/following-sibling::div[6]/a")
    public WebElement clickResetFilterTodaysAppointments;

    // Scheduling Requests Section
    @FindBy(xpath = "//h2[normalize-space()='Scheduling Requests']")
    public WebElement SchedulingRequestsHeading;

    @FindBy(xpath = "//option[normalize-space()='Patient']/parent::select")
    public WebElement ClickPatientDrpDwn;

    @FindBy(xpath = "//option[normalize-space()='Request Type']/parent::select")
    public WebElement ClickRequestTypeDrpDwn;

    @FindBy(xpath = "//option[normalize-space()='Request Type']/parent::select/parent::div/following-sibling::div/a")
    public WebElement ClickResetFilterSchedulingRequests;

    @FindBy(xpath = "//h2[normalize-space()='Scheduling Requests']/parent::div/ul/li/div/i")
    public WebElement ClickRefreshButtonSchedulingRequests;

    @FindBy(xpath = "//table[@id='schedulingrequestslist']/tbody/tr/td[7]")
    public WebElement ClickActionColumnSchedulingRequests;

    @FindBy(xpath = "//span[normalize-space()='Accept Appointment Request']/parent::span/span")
    public WebElement ClickAcceptAppointmentRequest;

    @FindBy(xpath = "//span[normalize-space()='See Appointment Details']/parent::span/span")
    public WebElement ClickSeeAppointmentDetails;

    // Provider Appointments Section
    @FindBy(xpath = "//h2[normalize-space()='Provider Appointments']")
    public WebElement ProviderAppointmentsHeading;

    @FindBy(xpath = "//table[@id='providerappointmentslist']/tbody/tr[1]/td[9]")
    public WebElement ClickViewButtonProviderAppointments;

    @FindBy(xpath = "//table[@id='providerappointmentslist']/tbody/tr[1]/td[10]")
    public WebElement ClickActionBtnProviderAppointments;

    @FindBy(xpath = "//span[normalize-space()='View Patient']/parent::span/span")
    public WebElement ClickViewPatient;

    @FindBy(xpath = "//span[normalize-space()='Canceled by Staff']/parent::span/span")
    public WebElement GetCanceledByStaffText;

    @FindBy(xpath = "//span[normalize-space()='Completed Successfully']/parent::span/span")
    public WebElement GetCompletedSuccessfullyText;

    @FindBy(xpath = "//span[normalize-space()='Patient No Show']/parent::span/span")
    public WebElement GetPatientNoShowText;

    @FindBy(xpath = "//span[normalize-space()='Not Completed - Tech Issue']/parent::span/span")
    public WebElement GetNotCompletedTechIssueText;

}

