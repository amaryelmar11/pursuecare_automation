package testCases_PAS;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.Appointment_Confirmation_Report_Page;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.PAS_Availability_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.Provider_Availability_Page;


public class PAS_Availability extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage pa;
    private PAS_Availability_Page ap;
    private Provider_Availability_Page pd;
    private Appointment_Report_Page ar;
    private DashBoardPage dp;
    private Appointment_Confirmation_Report_Page arc;


    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
        ap = new PAS_Availability_Page(driver);
        pd = new Provider_Availability_Page(driver);
        ar = new Appointment_Report_Page(driver);
        dp = new DashBoardPage(driver);
        arc = new Appointment_Confirmation_Report_Page(driver);
    }

    @Test(priority = 1)
    public void pasAvailability() throws InterruptedException {
        cu.login(lp, p.getProperty("pas"), p.getProperty("pass"));

        cu.click(dp.ClickOnCareTeamDash);
        cu.click(ap.ClickCareTeamAvailability);
        
        cu.click(ap.ClickCareTeamAvailability);
        cu.click(ar.ClickUserRoleFilter);
        cu.click(ar.ClickPASRole);
        cu.enterText(arc.SearchInputbutton, "Saavi");
        Thread.sleep(2000);
        cu.click(ap.EditAvailabilityBtn);
        cu.click(ap.Add_Edit_SlotOption);
        cu.click(ap.ClickDaysCheckbox);
        ap.ClickDaysCheckboxx();
        Thread.sleep(6000);
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        cu.click(ap.AddTimeSlotBtn);
        cu.click(ap.ClickStartTime);
        
        //Input Hour Logic
        cu.click(ap.AddtheInputTimeHour);
        ap.AddtheInputTimeHour.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.AddtheInputTimeHour.sendKeys(Keys.BACK_SPACE);
        cu.enterText(ap.AddtheInputTimeHour, "03");
        String AMPMValue = cu.getElementText(ap.ClickAMPMOption);

        //Input Minute Logic
        cu.click(ap.AddtheInputTimeMinute);
        ap.AddtheInputTimeMinute.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.AddtheInputTimeMinute.sendKeys(Keys.BACK_SPACE);
        cu.enterText(ap.AddtheInputTimeMinute, "00");
       if(AMPMValue.equals("AM"))
       {
        cu.click(ap.ClickAMPMOption);
       }

        cu.click(pa.selectSetStartTime);
       
        cu.click(ap.ClickEndTime);
        cu.click(ap.AddtheInputTimeHour);
        ap.AddtheInputTimeHour.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.AddtheInputTimeHour.sendKeys(Keys.BACK_SPACE);
        cu.enterText(ap.AddtheInputTimeHour, "04");

        cu.click(ap.AddtheInputTimeMinute);
        ap.AddtheInputTimeMinute.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
        ap.AddtheInputTimeMinute.sendKeys(Keys.BACK_SPACE);
        cu.enterText(ap.AddtheInputTimeMinute, "00");
       if(AMPMValue.equals("AM"))
       {
        cu.click(ap.ClickAMPMOption);
       }   
        cu.click(pa.selectSetStartTime);
        cu.click(pd.ClickOnSubmitTimeSlot);
      
    }

}
