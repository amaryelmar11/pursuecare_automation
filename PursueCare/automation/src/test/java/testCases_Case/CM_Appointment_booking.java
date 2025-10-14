package testCases_Case;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.CM_Role;
import PageObjectClass.LoginPage;
import PageObjectClass.DashBoardPage;
import PageObjectClass.ProviderAppointmentsPage;


public class CM_Appointment_booking extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private CM_Role pa;
    private ProviderAppointmentsPage ap;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new CM_Role(driver);   
        ap = new ProviderAppointmentsPage(driver);
    
    }







        @Test(priority = 1)
        public void CM_Appointment_booking_flow() throws InterruptedException
        {
            LoginPage lp = new LoginPage(driver);
            lp.enterEmailId(p.getProperty("case"));
            lp.enterPassword(p.getProperty("case_pass"));
            lp.clickLoginbtn();
            
            DashBoardPage dp = new DashBoardPage(driver);
    
            String dashboardtxt = dp.dashBoardtext();
    
            Assert.assertEquals("Dashboard", dashboardtxt);

            Thread.sleep(8000);
    cu.click(pa.menu_careteam);
    Thread.sleep(3000);
cu.click(pa.menu_appintment);
Thread.sleep(3000);
WebElement todayCell = ap.getTodayAppXpathCM_PAS();
        cu.click(todayCell);
        Thread.sleep(3000);

        cu.click(ap.selectAppointment);

        cu.click(ap.selectAppointmentType);
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

        cu.click(ap.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        cu.click(ap.selectPatient);
        cu.enterText(ap.inputPatientName, p.getProperty("PatientSearch"));
        cu.click(ap.setVisiblePatient);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        // Start time selection
        cu.click(ap.selectStartTime);
        cu.click(ap.selectSetStartTime);

        // End time selection
        cu.click(ap.selectEndTime);
        cu.click(ap.selectAddhour);
        cu.click(ap.selectSetEndTime);

        // Save
        cu.click(ap.selectSaveButton);
cu.logout(lp);

        }


    
    
    }


    

