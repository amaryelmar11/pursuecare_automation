package PageObjectClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class Patient_Satisfaction_Report_Page extends BasePageClass{

    WebDriver driver;
    private WebDriverWait wait;

    public Patient_Satisfaction_Report_Page(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath = "//a[@class='client-menu-font ml-menu'][normalize-space()='Patient Satisfaction Report']")
    public WebElement clickPatientSatisfactionReport;

    @FindBy(xpath = "//mat-label[normalize-space()='Patient Response']")
    public WebElement clickPatientResponse;

    @FindBy(xpath = "//span[normalize-space()='Poor']")
    public WebElement selectPoorOption;

    
    @FindBy(xpath = "//mat-label[normalize-space()='Entry Status']")
    public WebElement clickEntryStatus;






}
