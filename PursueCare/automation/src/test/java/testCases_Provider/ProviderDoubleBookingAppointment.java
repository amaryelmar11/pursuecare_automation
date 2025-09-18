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

public class ProviderDoubleBookingAppointment extends Baseclass{

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
    public void doubleBookingProvider() throws InterruptedException
    {
        cu.login(lp, p.getProperty("providerdoublebook"), p.getProperty("passwordoublebook"));
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
        cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch1"));
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

        

        // Double Booking

        cu.click(todayCell);
        Thread.sleep(2000);
        cu.click(pa.selectAppointment);

        cu.click(pa.selectAppointmentType);
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

        cu.click(pa.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        cu.click(pa.selectPatient);
        cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch2"));
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
        cu.click(pa.selectDoubleBook);
    }
}