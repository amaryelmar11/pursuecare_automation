package testCases_PAS;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.CM_Role;

public class PAS_Appointment_Booking extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage ap;
   private CM_Role pa;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        ap = new ProviderAppointmentsPage(driver);
        pa = new CM_Role(driver);

    }

    

 
    @Test(priority = 1)
    public void appointmentBooking() throws InterruptedException
    {
        cu.login(lp, p.getProperty("pas"), p.getProperty("pass"));
        Thread.sleep(2000);

        
        

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

   // @Test(priority = 2)
    // public void pasAppointmentDelete() throws InterruptedException
    // {
    //     cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
   
    //     cu.click(pa.clickCareTeamDash);
    //     cu.click(pa.clickAppointments);

    //     cu.click(pa.SelectProviderDropdown);
    //     cu.click(pa.SelectProviderFromDrpdwn);
    //     new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        
    //     Thread.sleep(1000);
    //     cu.click(pa.SelectAppointmentForDeletion);
    //     ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    //     Thread.sleep(2000);
    //     cu.click(pa.DeleteProviderApp);
    //     cu.click(pa.ClickOnDeleteBtnPopup);

    // }

    
}
