package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Provider_List_Page extends BasePageClass{

    WebDriver driver;

    public Provider_List_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }


    @FindBy(xpath = "//span[normalize-space()='Providers']")
	public WebElement ClickProviderDash;
    
    @FindBy(xpath = "//a[normalize-space()='Provider List']")
	public WebElement ClickProviderListDash;

    @FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement SearchFilterBtn;

    @FindBy(xpath = "//label[normalize-space()='Licensed State']")
	public WebElement ClickLicensedStateFilter;

    @FindBy(xpath = "//span[normalize-space()='Connecticut']")
	public WebElement SelectStateConnecticut;

    @FindBy(xpath = "//mat-label[normalize-space()='Insurances']")
	public WebElement ClickInsurancesFilter;

    @FindBy(xpath = "//span[normalize-space()='21St Century Cures Act NJ - Registration']")
	public WebElement SelectInsurance;

    @FindBy(xpath = "//mat-label[normalize-space()='Weekly Schedule']")
	public WebElement ClickWeeklySchedule;

    @FindBy(xpath = "//span[normalize-space()='Sunday']")
	public WebElement SelectDay;

    @FindBy(xpath = "//mat-label[normalize-space()='Speciality']")
	public WebElement ClickSpeciality;

    
    @FindBy(xpath = "//span[normalize-space()='Admission Counselor']")
	public WebElement SelectSpeciality;

      
    @FindBy(xpath = "//mat-label[normalize-space()='Employee Type']")
	public WebElement ClickEmployeeType;

    @FindBy(xpath = "//div[@aria-label='Employee Type']/mat-option[2]/span")
	public WebElement SelectEmployeeType;

    @FindBy(xpath = "//mat-label[normalize-space()='Allow Appointments']")
	public WebElement ClickAllowAppointments;

    @FindBy(xpath = "//span[normalize-space()='Enabled']")
	public WebElement SelectEnabled ;


    // Search REset, Refresh,  Add Provider, Toggle Column Visibility


    @FindBy(xpath = "//button[@aria-label='Apply search']")
	public WebElement ClickSearchFilter;

    @FindBy(xpath = "//button[@aria-label='Clear filters']")
	public WebElement ClickResetFilter;

    @FindBy(xpath = "//button[@aria-label='Refresh']")
	public WebElement ClickRefreshFilter;

    @FindBy(xpath = "//button[@aria-label='Add item']")
	public WebElement ClickAddItemBtn;

    @FindBy(xpath = "//button[@aria-label='Toggle columns']")
	public WebElement ClickTogglecolumnsBtn;


    @FindBy(xpath = "//div[normalize-space()='Add New Provider']")
	public WebElement GetAddPNewProvider;

    @FindBy(xpath = "//mat-icon[normalize-space()='close']")
	public WebElement CloseAddPNewProvider;

    @FindBy(xpath = "//label[normalize-space()='Provider Name']")
	public WebElement ClickProviderName;

    // Schedule Appointment From Provider List

    @FindBy(xpath = "//tbody[@role='rowgroup']/tr/td[8]/div/a")
	public WebElement ClickShowAppointmentPopup;

    @FindBy(xpath = "//div[normalize-space()='Schedule Appointment']")
	public WebElement GetScheduleAppointmentText;

    

}
