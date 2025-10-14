package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Upload_To_EHR_Page extends BasePageClass{

    WebDriver driver;

    public Upload_To_EHR_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//a[normalize-space()='Upload To EHR']")
	public WebElement ClickUploadToEHR;

    @FindBy(xpath="//input[@aria-label='Search box']")
	public WebElement ClickSearchInput;

    
    @FindBy(xpath="//mat-label[normalize-space()='State']")
	public WebElement ClickStateDrpDwn;

    @FindBy(xpath="//span[normalize-space()='AK']")
	public WebElement SelectState;

    @FindBy(xpath="//mat-label[normalize-space()='Registration Date']")
	public WebElement ClickRegistrationDateDrpDwn;

    @FindBy(xpath="//mat-label[normalize-space()='Status']")
	public WebElement clicStatusDrpDwn;

    @FindBy(xpath="//span[normalize-space()='New Patient']")
	public WebElement SelectStatus;

    @FindBy(xpath="//span[normalize-space()='User action required']")
	public WebElement SelectStatusUserAction;

    @FindBy(xpath="//button[@mattooltip='Clear']")
	public WebElement ClickResetFilterBtn;

    @FindBy(xpath="//button[@mattooltip='Refresh']")
	public WebElement ClickRefreshFilterBtn;

    @FindBy(xpath="//button[@type='submit']")
	public WebElement ClickUploadToEHRBtn;

    @FindBy(xpath="//tbody[@role='rowgroup']/tr[5]/td[3]//mat-checkbox")
	public WebElement clickCheckboxUpload;

    @FindBy(xpath="//tbody[@role='rowgroup']/tr[5]/td[8]")
	public WebElement GetUploadedPatientEmail;

    @FindBy(xpath="//tbody[@role='rowgroup']/tr/td[1]")
	public WebElement GetMRNOfUploadedPatient;

    @FindBy(xpath="//tbody[@role='rowgroup']/tr/td[12]")
	public WebElement ClickActionsTab;

    @FindBy(xpath="//a[normalize-space()='Edit Patient Details']")
	public WebElement ClickEditPatientDetails;

    @FindBy(xpath="//div[@class='editRowModal']/div/div")
	public WebElement ValidateEditPatientText;

    @FindBy(xpath="//div[@class='modal-about']/div")
	public WebElement ValidateMismatchFiledText;

    
    @FindBy(xpath="//a[normalize-space()='View Mismatched Fields']")
	public WebElement ClickMismatchField;




}
