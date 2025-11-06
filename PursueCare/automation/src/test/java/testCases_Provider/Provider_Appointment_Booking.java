package testCases_Provider;

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

public class Provider_Appointment_Booking extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage pa;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
    }

    @Test(priority = 1)
    public void appointmentBooking() throws InterruptedException
    {
        cu.login(lp, p.getProperty("providerSinglebook"), p.getProperty("passwordSinglebook"));
        cu.click(pa.selectAppointmentDash);
        WebElement todayCell = pa.getTodayAppXpath();
        cu.click(todayCell);
        Thread.sleep(2000);
        cu.click(pa.selectAppointment);

        cu.click(pa.selectAppointmentType);
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

        cu.click(pa.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        cu.click(pa.selectPatient);
        cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch"));
        cu.click(pa.setVisiblePatient);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        // Start time selection
        cu.click(pa.selectStartTime);
        cu.click(pa.selectSetStartTime);

        // End time selection
        cu.click(pa.selectEndTime);
        cu.click(pa.selectAddhour);
        cu.click(pa.selectSetEndTime);

        // Save
        cu.click(pa.selectSaveButton);

        cu.logout(lp);
    }

    @Test(priority = 2)
    public void providerAppointmentDelete() throws InterruptedException
    {
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
   
        cu.click(pa.clickCareTeamDash);
        cu.click(pa.clickAppointments);

        cu.click(pa.SelectProviderDropdown);
        cu.click(pa.SelectProviderFromDrpdwn);
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        
        Thread.sleep(1000);
        cu.click(pa.SelectAppointmentForDeletion);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        cu.click(pa.DeleteProviderApp);
        cu.click(pa.ClickOnDeleteBtnPopup);
        Thread.sleep(5000);

    }

    
}
