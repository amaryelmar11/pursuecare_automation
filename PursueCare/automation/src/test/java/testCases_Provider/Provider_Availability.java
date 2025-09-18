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
    public void placeholderTest() {
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));

        cu.click(ap.ClickAvailabilitySchedule);
        cu.click(ap.ClickOnAddSlotOption);
        cu.click(ap.ClickONStartTimeTimeSlot);
        cu.click(ap.ClickOnSetStartTime);
        cu.click(ap.ClickOnAddHourArrow);
        cu.click(ap.ClickOnSetStartTime);
        cu.click(ap.ClickOnSubmitTimeSlot);
        cu.click(ap.DeleteslotBtn);
    }

   // @Test(priority = 2)
    public void DeleteAddedSlot()
    {


    }
}


