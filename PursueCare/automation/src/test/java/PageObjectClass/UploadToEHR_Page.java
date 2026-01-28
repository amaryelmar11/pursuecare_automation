package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;
import BasePage.Common_Utils;
import BasePage.RandomData;

public class UploadToEHR_Page extends BasePageClass {

    WebDriver driver;
    
    

    public UploadToEHR_Page(WebDriver driver) {
        super(driver);
        this.driver = driver;}    
   
   
        @FindBy(xpath = "//a[normalize-space()='Appointment Confirmation']")
        public WebElement clickAppointmentConfirReport;

    }
