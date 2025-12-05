package testCases_Provider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LSEHR_Page;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;

public class Provider_Appointment_Booking extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage pa;
    private LSEHR_Page ls;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
        ls = new LSEHR_Page(driver);
    }

    @Test(priority = 1)
    public void appointmentBooking() throws InterruptedException
    {
        cu.login(lp, p.getProperty("providerSinglebook"), p.getProperty("passwordSinglebook"));
        cu.click(pa.selectAppointmentDash);

        // For Getting the window ID
        String mainwindowHandle = driver.getWindowHandle();
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
        cu.click(pa.selectContinueAnywayButton);
        cu.logout(lp);

    }

    public void checkAppointmentInLS()
    {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();

        driver.get(p.getProperty("LSTestEnvUrl"));
        cu.loginLS(ls, p.getProperty("LoginIDLS"), p.getProperty("PasswordLS"));

        cu.click(ls.SearchBtnLS);
        cu.click(ls.ClickInquiriesOption);
        cu.click(ls.ClickFullsearch);

        cu.enterText(ls.ClickSearch, p.getProperty("SearchLS"));

        cu.click(ls.ClickInquiryNameHyperlink);
        cu.click(ls.ClickSchedulingSection);

       
        Assert.assertEquals(cu.getElementText(ls.ValidateAppointmentDate), cu.getTodayDate());

       Assert.assertEquals( cu.getElementText(ls.ValidDayAppointment), cu.getCurrentDay());

       Assert.assertEquals( cu.getElementText(ls.ValidateEventType), "appointment");
       Assert.assertEquals( cu.getElementText(ls.ValidateStatus), "active");

       cu.click(ls.ClickEditBtn);

       Assert.assertNotNull(ls.ClickServiceType);
       Assert.assertNotNull(ls.CLickClientId);
       cu.click(ls.ClickDeleteBtn);

    }

    @Test(priority = 2)
    public void providerAppointmentDelete() throws InterruptedException
    {
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
   
        cu.click(pa.clickCareTeamDash);
        cu.click(pa.clickAppointments);
        Thread.sleep(1000);
        cu.click(pa.SelectProviderDropdown);
        Thread.sleep(2000);
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
