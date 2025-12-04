package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Patient_List_Page extends BasePageClass{

    WebDriver driver;

    public Patient_List_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//mat-label[normalize-space()='Appointment Requests']")
    public WebElement SelectAppointmentRequests;

    @FindBy(xpath="//span[normalize-space()='Enabled']")
    public WebElement SelectEnableOption;

    
    @FindBy(xpath="//span[normalize-space()='All']")
    public WebElement GetAppReqAllText;
}
