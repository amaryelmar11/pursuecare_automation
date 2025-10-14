package PageObjectClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class PAS_Availability_Page extends BasePageClass{

    
    WebDriver driver;

    public PAS_Availability_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//a[normalize-space()='Care Team Availability']")
    public WebElement ClickCareTeamAvailability;

    @FindBy(xpath="//table[@aria-label='Report table']/tbody/tr/td[4]/div/button[2]")
    public WebElement EditAvailabilityBtn;

    @FindBy(xpath="//a[@href='#add_time_slot']")
    public WebElement Add_Edit_SlotOption;

    
    @FindBy(xpath="//mat-icon[normalize-space()='add']")
    public WebElement AddTimeSlotBtn;

    
    @FindBy(xpath="//span[normalize-space()='Please enter slot start time.']/ancestor::mat-error/parent::div/parent::div/preceding-sibling::div/div[2]/div/input")
    public WebElement ClickStartTime;

    
    @FindBy(xpath="//span[normalize-space()='Please enter slot end time.']/ancestor::mat-error/parent::div/parent::div/preceding-sibling::div/div[2]/div/input")
    public WebElement ClickEndTime;

    @FindBy(xpath="//button[@aria-label='Add a hour']/following-sibling::label/input")
    public WebElement AddtheInputTimeHour;

    @FindBy(xpath="//button[@aria-label='Add a minute']/following-sibling::label/input")
    public WebElement AddtheInputTimeMinute;

    
    @FindBy(xpath="//span[normalize-space()='AM']")
    public WebElement ClickAMPMOption;

    @FindBy(xpath="//mat-select[@formcontrolname='weekdays']")
    public WebElement ClickDaysCheckbox;

    public void ClickDaysCheckboxx() {
        List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@aria-multiselectable='true']/mat-option/mat-pseudo-checkbox"));
        for (int i = 1; i <= 8; i++) { // start from 1 to skip Sunday
            checkboxes.get(i).click();
            System.out.print(checkboxes.size());
        }
    }
}

