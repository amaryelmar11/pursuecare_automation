package PageObjectClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class Appointment_Report_Page extends BasePageClass{

    WebDriver driver;
    private WebDriverWait wait;

    public Appointment_Report_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    


    @FindBy(xpath = "//mat-label[normalize-space()='Select patient(s)']")
    public WebElement clickSelectPatientFilter;

    @FindBy(xpath = "//input[@placeholder='Type to search patients...']")
    public WebElement SearchPatientInput;

    @FindBy(xpath = "//mat-pseudo-checkbox[@class='mat-pseudo-checkbox mat-mdc-option-pseudo-checkbox mat-pseudo-checkbox-full ng-star-inserted']")
    public WebElement clickCheckbxPatientSelect;


    // Search, Reset, Refresh

    @FindBy(xpath = "//mat-icon[normalize-space()='search']")
    public WebElement clickAppointmentReportSearch;

    @FindBy(xpath = "//button[@mattooltip='Clear']")
    public WebElement clickResetFilterElement;

    @FindBy(xpath = "//mat-icon[normalize-space()='refresh']")
    public WebElement clikconRefresh;

    @FindBy(xpath = "//button[@mattooltip='Download the report in its current state, including all filters and sorting, as an Excel spreadsheet.']")
    public WebElement downloadExcelReport;



    @FindBy(xpath = "//tbody[@role='rowgroup']/tr[1]/td[7]")
    public WebElement AppointmentCompletionTxtZoomCheck;

    @FindBy(xpath = "//mat-label[normalize-space()='User Role']/parent::*/parent::div/mat-select")
    public WebElement ClickUserRoleFilter;


    //User role Provider

    @FindBy(xpath = "//span[normalize-space()='Provider']")
    public WebElement ClickProviderRole;

    @FindBy(xpath = "//mat-label[normalize-space()='Speciality']")
    public WebElement ClickSpecialityFilterProvider;

    @FindBy(xpath = "//mat-label[normalize-space()='Provider']")
    public WebElement ClickProviderFilter;

    @FindBy(xpath = "//mat-label[normalize-space()='Status']")
    public WebElement clickStatusFilterforProvider;

    @FindBy(xpath = "//mat-label[normalize-space()='Appointment Type']")
    public WebElement clickAppointmentTypeProvider;

    //Role Type CaseManager
    
    @FindBy(xpath = "//span[normalize-space()='Case Manager']")
    public WebElement ClickCaseManagerRole;

    @FindBy(xpath = "//mat-label[normalize-space()='Case Manager']")
    public WebElement clickCaseManager;

    //Role Type PAS

    @FindBy(xpath = "//span[normalize-space()='Patient Access Specialist']")
    public WebElement ClickPASRole;

    @FindBy(xpath = "//mat-label[normalize-space()='Patient Access Specialist']")
    public WebElement clickPAS;


    @FindBy(xpath = "//button[@aria-label='Open calendar'][1]")
    public WebElement ClickonDateRangeFilter;


    // Click Items Per Page
    @FindBy(xpath = "//div[normalize-space()='Items per page:']/parent::div/mat-form-field/div/div/div[2]/mat-select")
    public WebElement clicItemsPerPageFilter;

    @FindBy(xpath = "//span[@class='mdc-list-item__primary-text'][normalize-space()='25']")
    public WebElement ClickOnFilterRange25;

    //Generic Xpath for column Sorting
    @FindBy(xpath = "//tbody[@role='rowgroup']/tr")
    public List<WebElement> SortingRowsAppReport;


    // Method to Pass the rows data to the Sorting Method
/* 
    public static List<WebElement> getColumnElements(List<WebElement> rows, int colIndex) {
       
        List<WebElement> columnElements = new ArrayList<>();

        for (int i = 1; i <= rows.size(); i++) {
            WebElement cell = driver.findElement(By.xpath(baseXpath + "/tr[" + i + "]/td[" + colIndex + "]"));
            columnElements.add(cell);
        }
        return columnElements;
    } */
}

