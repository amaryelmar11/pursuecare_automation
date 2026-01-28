package PageObjectClassCM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Provider_list_CM_Page extends BasePageClass{

    WebDriver driver;

    public Provider_list_CM_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

     // Basic checks Case Manager Dashboard Page

     @FindBy(xpath = "//input[@name='Search']")
     public WebElement SearchFilterBtn;

    @FindBy(xpath = "//button[@aria-label='Add item']")
    public WebElement AddProviderBtn;

    @FindBy(xpath = "//input[@formcontrolname='firstname']")
    public WebElement firstname;

    @FindBy(xpath = "//input[@formcontrolname='lastname']")
    public WebElement lastname;

    @FindBy(xpath = "//input[@formcontrolname='provideremail']")
    public WebElement ProviderEmail;

    @FindBy(xpath = "//input[@formcontrolname='phonenumber']")
    public WebElement EnterNumber;

    @FindBy(xpath = "//mat-select[@formcontrolname='jobdescription']")
    public WebElement SelectSpeciality;

    @FindBy(xpath = "//span[normalize-space()='PursueCareRx Pharmacist']/preceding-sibling::mat-pseudo-checkbox")
    public WebElement pursuecareRXSpeciality;

    @FindBy(xpath = "//span[normalize-space()='Psych Medication Manager']/preceding-sibling::mat-pseudo-checkbox")
    public WebElement PsychMeditationManager;

    @FindBy(xpath = "//span[normalize-space()='Therapy/Counseling']/preceding-sibling::mat-pseudo-checkbox")
    public WebElement TherapyCounseling;

    @FindBy(xpath = "//span[normalize-space()='MAT Prescriber']/preceding-sibling::mat-pseudo-checkbox")
    public WebElement MATDescriber;

    @FindBy(xpath = "//span[normalize-space()='Admission Counselor']/preceding-sibling::mat-pseudo-checkbox")
    public WebElement AdmissionCouseller;

    @FindBy(xpath = "//mat-select[@formcontrolname='employeetype']")
    public WebElement SelectEmployeeTypeDrpDwn;

    @FindBy(xpath = "//span[normalize-space()='SW']/parent::mat-option/span")
    public WebElement SWEmpType;

    @FindBy(xpath = "//span[normalize-space()='1099']/parent::mat-option/span")
    public WebElement EmpType1099;

    @FindBy(xpath = "//span[normalize-space()='W2']/parent::mat-option/span")
    public WebElement W2EmpType;

    @FindBy(xpath = "//tbody/tr/td[1]/div/span/span/span")
    public WebElement GetCreatedProviderName;

    @FindBy(xpath = "//tbody/tr/td[8]/div/button")
    public WebElement ClickActionBtn;
     
    @FindBy(xpath = "//div[@class='mat-mdc-menu-content']/button[1]/span/span")
    public WebElement ClickResetPasswordBtn;

    @FindBy(xpath = "//span[normalize-space()='Ok']")
    public WebElement ClickOkBtn;

}
