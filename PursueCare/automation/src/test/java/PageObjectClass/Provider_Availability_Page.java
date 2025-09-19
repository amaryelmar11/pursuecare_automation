package PageObjectClass;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;

import BasePage.BasePageClass;
import BasePage.Common_Utils;

public class Provider_Availability_Page extends BasePageClass{

    WebDriver driver;
    private Common_Utils cu;
    private Provider_Dashboard_Page pd;
    private LoginPage lp;
    private ProviderChatPage pc;

    public Provider_Availability_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//span[normalize-space()='Availability Schedule']")
    public WebElement ClickAvailabilitySchedule;

    @FindBy(xpath="//ul[@class='schedule-nav-tabs nav-justified']")
    public WebElement ScheduleAvailaibilityUlList;

    @FindBy(xpath="//a[@href='#add_time_slot']")
    public WebElement ClickOnAddSlotOption;

    @FindBy(xpath="//input[@formcontrolname='slotStartTime']")
    public WebElement ClickONStartTimeTimeSlot;

    @FindBy(xpath="//span[normalize-space()='Set']")
    public WebElement ClickOnSetStartTime;

    @FindBy(xpath="//input[@formcontrolname='slotEndTime']")
    public WebElement ClickONEndTimeTimeSlot;

    @FindBy(xpath="//button[@aria-label='Add a hour']//span[@class='owl-dt-control-button-content']//*[name()='svg']")
    public WebElement ClickOnAddHourArrow;

    @FindBy(xpath="//span[normalize-space()='Submit']")
    public WebElement ClickOnSubmitTimeSlot;

    @FindBy(xpath="//mat-checkbox[@formcontrolname='chkboxDayOfWeek']/div/div/input")
    public WebElement ClickDaysOfWeekChkbx;

    @FindBy(xpath="//mat-select[@formcontrolname='weekdays']")
    public WebElement WeekDaysChkBx;

    @FindBy(xpath="//mat-icon[@role='button']")
    public WebElement DeleteslotBtn;

    // Add slot for the multiple days



    public void clickTodayAndTomorrow() {
        // Get today's and tomorrow's day name (e.g., Sunday, Monday)
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE"); // full day name
        String todayDay = today.format(formatter);
        String tomorrowDay = tomorrow.format(formatter);
    
        // Build the XPaths dynamically
        WebElement todayElement = driver.findElement(By.xpath("//span[normalize-space()='" + todayDay + "']"));
        WebElement tomorrowElement = driver.findElement(By.xpath("//span[normalize-space()='" + tomorrowDay + "']"));
    
        // Click both
        todayElement.click();
        //TEsting
        tomorrowElement.click();
    }
    

    public void DeleteMultipleSlots() {
        // Get today's and tomorrow's day names
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE"); // Sunday, Monday...
        String todayDay = today.format(formatter);
        String tomorrowDay = tomorrow.format(formatter);
    
        // Collect all the <li>/a elements
        List<WebElement> daysList = driver.findElements(
            By.xpath("//div[@class='card schedule-widget']/div/div/ul/li/a")
        );
    
        for (WebElement dayElement : daysList) {
            String dayText = dayElement.getText().trim();
    
            if (dayText.equalsIgnoreCase(todayDay) || dayText.equalsIgnoreCase(tomorrowDay)) {
                // Click the day first
                dayElement.click();
    
                // After clicking the day, click on the mat-icon
                WebElement matIcon = driver.findElement(By.xpath("//mat-icon[@role='button']"));
                matIcon.click();
            }
        }
    }
    
}
    

