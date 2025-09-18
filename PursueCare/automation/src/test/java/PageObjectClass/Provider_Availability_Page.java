package PageObjectClass;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
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

    public static void clickCurrentDay(WebDriver driver, By ulLocator) {
        // Get today's day name (e.g., MONDAY → "Monday")
        String today = LocalDate.now().getDayOfWeek().toString(); 
        today = today.substring(0, 1) + today.substring(1).toLowerCase(); // Format: "Monday"

        // Find all <li> inside the given <ul>
        WebElement ulElement = driver.findElement(ulLocator);
        java.util.List<WebElement> liElements = ulElement.findElements(By.tagName("li"));

        // Loop through li elements and click the one matching today
        for (WebElement li : liElements) {
            String dayText = li.getText().trim();
            if (dayText.equalsIgnoreCase(today)) {
                li.click();
                System.out.println("Clicked on: " + dayText);
                return;
            }
        }

        throw new RuntimeException("No matching day found for today: " + today);
    }

    public void clickDay(boolean isTomorrow) {
        // Get today or tomorrow
        LocalDate date = LocalDate.now();
        if (isTomorrow) {
            date = date.plusDays(1);
        }
    
        // Get the day name (e.g., Sunday, Monday)
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    
        // Build XPath dynamically
        String xpath = String.format("//span[normalize-space()='%s']", dayName);
        System.out.println("Clicking day: " + dayName + " | XPath: " + xpath);
    
        WebElement dayElement = driver.findElement(By.xpath(xpath));
        dayElement.click();
    }
}
    

