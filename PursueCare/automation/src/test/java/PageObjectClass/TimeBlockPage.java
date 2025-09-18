package PageObjectClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;
import BasePage.Common_Utils;

public class TimeBlockPage extends BasePageClass {

	WebDriver driver;
	private ProviderAppointmentsPage pa;
	private Common_Utils cu;

	public TimeBlockPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		pa = new ProviderAppointmentsPage(driver);
		cu = new Common_Utils(driver);
	}



	// Category selection (Appointment type dropdown on popup)
	@FindBy(xpath = "//mat-select[@formcontrolname='appointmenttType']")
	public WebElement CategorySelection;

	// Choose "Time Block" category
	@FindBy(xpath = "//span[normalize-space()='Time Block']")
	public WebElement TimeBlockSelection;

	// Type dropdown and value (e.g., Vacation (PTO/UTO))
	@FindBy(xpath = "//mat-label[normalize-space()='Type']")
	public WebElement TypeDrpDwnSelection;

	@FindBy(xpath = "//span[normalize-space()='Vacation (PTO/UTO)']")
	public WebElement TimeBlockType;

	// Date pickers
	@FindBy(xpath = "//button[@aria-label='Open calendar'][1]")
	public WebElement OpenStartDateCalendar;

	@FindBy(xpath = "//mat-label[normalize-space()='End Date']/ancestor::div[2]/div[2]/mat-datepicker-toggle/button")
	public WebElement OpenEndDateCalendar;

	@FindBy(xpath = "//mat-label[normalize-space()='End Date']")
	public WebElement clickOnEndDateIcon;

	
	@FindBy(xpath = "//mat-label[normalize-space()='End Date']")
	public WebElement PopUpReference;

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='isallday']/div/div/input")
	public WebElement clickAllDayCheckbox;

	@FindBy(xpath = "//div[@class='mat-mdc-dialog-content mdc-dialog__content']")
	public WebElement scrollableXpath;

	@FindBy(xpath = "//span[normalize-space()='Procced']")
	public WebElement ProceedBtnClick;

	//Time Block Deletion

	@FindBy(xpath = "//td[contains(@class, 'fc-day-today fc-daygrid-day')]/div/div[2]/div/a/div[2]")
	public WebElement TimeBlockSelect;

	// Recurring Block Addition related

	@FindBy(xpath = "//span[normalize-space()='Ok']")
	public WebElement ClickOkAfterSave;

	@FindBy(xpath = "//mat-radio-group[@role='radiogroup']/mat-radio-button[2]")
	public WebElement EntireSeriesRecRadioBtn;


	


	
	// Method For the Save Btn CLick Handlation

	public void clickSaveAndHandlePopup() {
       

		cu.click(pa.selectSaveButton);
		By ProceedBtnClick = By.xpath("//span[normalize-space()='Procced']");
       // long startTime = System.currentTimeMillis();

        try {
            WebElement yesElement = new WebDriverWait(driver, Duration.ofSeconds(3)) // short wait
                    .until(ExpectedConditions.presenceOfElementLocated((By) ProceedBtnClick));
					cu.click(ProceedBtnClick);
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}

