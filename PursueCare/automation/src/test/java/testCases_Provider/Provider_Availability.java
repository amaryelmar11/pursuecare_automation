package testCases_Provider;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.Provider_Availability_Page;

public class Provider_Availability extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private Provider_Availability_Page pa;
    private Provider_Availability_Page ap;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new Provider_Availability_Page(driver);
        ap = new Provider_Availability_Page(driver);
    }

    @Test(priority = 1)
    public void placeholderTest() throws InterruptedException {
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));

        cu.click(ap.ClickAvailabilitySchedule);
        cu.click(ap.ClickOnAddSlotOption);
        cu.click(ap.ClickONStartTimeTimeSlot);
        cu.click(ap.ClickOnSetStartTime);
        cu.click(ap.ClickONEndTimeTimeSlot);
        cu.click(ap.ClickOnAddHourArrow);
        cu.click(ap.ClickOnSetStartTime);
        cu.click(ap.ClickOnSubmitTimeSlot);
        Thread.sleep(1000);
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
}


