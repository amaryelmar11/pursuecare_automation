package testCases_Provider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.LoginPage;
import PageObjectClass.Patient_Creation_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.Provider_Availability_Page;
import PageObjectClass.Provider_Dashboard_Page;

public class Provider_Availability extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private Provider_Availability_Page pa;
    private Provider_Availability_Page ap;
    private Provider_Dashboard_Page pd;
    private ProviderAppointmentsPage app;
    private Patient_Creation_Page pc;
    private Appointment_Report_Page pr;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new Provider_Availability_Page(driver);
        ap = new Provider_Availability_Page(driver);
        pd = new Provider_Dashboard_Page(driver);
        app = new ProviderAppointmentsPage(driver);
        pc = new Patient_Creation_Page(driver);
        pr = new Appointment_Report_Page(driver);
    }

    
    @Test(priority = 1)
    public void filterProvider() throws InterruptedException
    {
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));
        cu.click(ap.ClickAvailabilitySchedule);
        Assert.assertEquals(cu.getElementText(ap.GetProviderNameSelected), cu.getElementText(ap.LoggedInProviderName));
        
    }

    @Test(priority = 2)
    public void placeholderTest() throws InterruptedException {
  
        cu.clickScheduleNavCurrentDay();
        cu.click(ap.ClickOnAddSlotOption);
        cu.click(ap.ClickONStartTimeTimeSlot);
        ap.EnterStartHourValue.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.EnterStartHourValue.sendKeys(Keys.BACK_SPACE); // Backspace to delete
        cu.enterText(ap.EnterStartHourValue, "07");
        ap.EnterStartMinuteValue.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.EnterStartMinuteValue.sendKeys(Keys.BACK_SPACE); // Backspace to delete
        cu.enterText(ap.EnterStartMinuteValue, "00");
        cu.click(ap.ClickOnSetStartTime);
        cu.click(ap.ClickONEndTimeTimeSlot);
        //cu.click(ap.ClickOnAddHourArrow);
        ap.EnterStartHourValue.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.EnterStartHourValue.sendKeys(Keys.BACK_SPACE); // Backspace to delete
        cu.enterText(ap.EnterStartHourValue, "08");
        ap.EnterStartMinuteValue.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.EnterStartMinuteValue.sendKeys(Keys.BACK_SPACE); // Backspace to delete
        cu.enterText(ap.EnterStartMinuteValue, "00");
        cu.click(ap.ClickOnSetStartTime);
        cu.click(ap.ClickOnSubmitTimeSlot);
        Thread.sleep(1000);

        cu.click(app.selectAppointmentDash);
        Thread.sleep(2000);
        WebElement todayCell = app.getTodayAppXpath();
        cu.click(todayCell);

        
        cu.click(app.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        System.out.println(cu.getElementText(ap.GetAddedSlotText));
        boolean status = cu.isTimeRangeInCurrentHour(cu.getElementText(ap.GetAddedSlotText));
        Assert.assertTrue(status);

        cu.click(pc.Clos_AddPatient_Tab);
        cu.click(ap.ClickAvailabilitySchedule);
        cu.clickScheduleNavCurrentDay();
        cu.click(ap.DeleteslotBtn);
        Thread.sleep(4000);

    }

   // @Test(priority = 2)
    public void AddSloForMultipleDay() throws InterruptedException
    {
        driver.navigate().refresh();
        Thread.sleep(2000);

        cu.click(ap.ClickOnAddSlotOption);
        cu.click(ap.ClickDaysOfWeekChkbx);
        cu.click(ap.WeekDaysChkBx);
        ap.clickTodayAndTomorrow();
        cu.click(ap.ClickOnSubmitTimeSlot);
        ap.DeleteMultipleSlots();

    }

    @Test(priority = 3)
    public void Addaddhocslots() throws InterruptedException
    {
       cu.click(ap.ClickCreateAdhocSlots);

       cu.click(pr.ClickonDateRangeFilter);
       app.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
       cu.click(ap.ClickONStartTimeTimeSlot);
       cu.click(ap.ClickOnSetStartTime);
       cu.click(ap.ClickONEndTimeTimeSlot);
       cu.click(app.selectAddhour);
       cu.click(app.selectSetEndTime);
       cu.click(app.selectSaveButton);

       cu.click(app.selectAppointmentDash);
        Thread.sleep(2000);
        WebElement todayCell = app.getTodayAppXpath();
        cu.click(todayCell);

        
        cu.click(app.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        System.out.println(cu.getElementText(ap.GetAddedSlotText));
        boolean status = cu.isTimeRangeInCurrentHournew(cu.getElementText(ap.GetAddedSlotText));
        Assert.assertTrue(status);

        cu.click(pc.Clos_AddPatient_Tab);
        cu.click(ap.ClickAvailabilitySchedule);
        cu.click(ap.ViewAllAdhocSlots);
        cu.click(ap.DeleteAdhocslots);
        cu.click(pc.Clos_AddPatient_Tab);
        Thread.sleep(500);
       
    }
}


