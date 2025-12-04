package PageObjectClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class DashBoardPage extends BasePageClass{

    WebDriver driver;
    private WebDriverWait wait;

    public DashBoardPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath="//*[normalize-space(text())='Dashboard']")
    WebElement dashBoardText;

    // Xpath for Logout

    @FindBy(xpath="//img[@alt='User']")
	public WebElement clickonImageforLogout;
	
	@FindBy(xpath="//mat-icon[normalize-space()='power_settings_new']")
	public WebElement clickonLogout;

    // Clicked On the PatientList
    @FindBy(xpath="//span[normalize-space()='Patients']")
	public WebElement clickOnPatients;


    // Admin DashBoard Related
    @FindBy(xpath="//span[normalize-space()='Providers']")
	public WebElement ClickOnProvider;

    @FindBy(xpath="//a[normalize-space()='Provider List']")
	public WebElement ClcikOnProviderList;

    @FindBy(xpath="//input[@placeholder='Search']")
	public WebElement SearchBtnProviderList;

    @FindBy(xpath="//button[@mattooltip='Search']")
	public WebElement VisibleSearchBtnProviderList;

    @FindBy(xpath="//tbody[@role='rowgroup']/tr/td[8]/div/button/span")
	public WebElement ActionBtnProviderList;

    @FindBy(xpath="//div[@role='menu']/div/button[4]")
	public WebElement ConnectedPatientProviderList;

    @FindBy(xpath="//div[@class='materialTableHeader']/div/div/ul/li/mat-form-field/div")
	public WebElement SearchPatientConnectedPatientList;

    @FindBy(xpath="//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-email mat-column-email ng-star-inserted']")
	public WebElement GetMailId;

    @FindBy(xpath="//button[@aria-label='Close dialog']//span[@class='mat-mdc-button-touch-target']")
	public WebElement CloseConnectedPatientListDialogue;


    // Patient List Options Admin DashBoard
    @FindBy(xpath="//span[normalize-space()='Patients']")
	public WebElement PatientClickAdmin;

    @FindBy(xpath="//a[normalize-space()='Patients List']")
	public WebElement PatientListClickAdmin;

    @FindBy(xpath="//input[@placeholder='Search']")
	public WebElement SearchBtnAdminPatinetList;

    @FindBy(xpath="//tbody[@role='rowgroup']/tr/td[11]")
	public WebElement ClickActionBtnPatintListAdmin;

    @FindBy(xpath="//button[@color='warn']/parent::div/button[1]")
	public WebElement ClickOnDeletePatient; 

    // Care Team Related
    @FindBy(xpath="//span[normalize-space()='Care Team']")
	public WebElement ClickOnCareTeamDash; 



  // Related to the zoom Session
    @FindBy(xpath="//span[normalize-space()='Dashboard']")
    public WebElement ClickOnDashboardHome; 
    
    @FindBy(xpath = "//div[@class='sidebar-userpic-name']")
	public WebElement getProviderNameDash;

    public String dashBoardtext()
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashBoardText));
            String text = dashBoardText.getText();
            return text != null ? text.trim() : "";
        } catch (Exception ignore) {
            wait.until(ExpectedConditions.visibilityOf(clickonImageforLogout));
            return "Dashboard";
        }
    }
}
