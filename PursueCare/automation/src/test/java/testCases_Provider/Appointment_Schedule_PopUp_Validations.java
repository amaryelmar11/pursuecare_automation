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
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;

public class Appointment_Schedule_PopUp_Validations extends Baseclass {

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
	public void openScheduleAndValidateDefaultLabels() throws InterruptedException {
		cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));

		cu.click(pa.selectAppointmentDash);
		WebElement todayCell = pa.getTodayAppXpath();
        cu.click(todayCell);
		
		Assert.assertEquals(cu.getElementText(pa.EmptyPASMultipleSelection), "Patient Access Specialist");
		Assert.assertEquals(cu.getElementText(pa.EmptyCMMultipleSelection), "Case Manager");
        
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");  
        cu.click(pa.selectSaveButton);    
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200);");
        Assert.assertEquals(cu.getElementText(pa.PatientValidation), "Patient name is required.");
        cu.click(pa.selectPatient);
        cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch3"));
        cu.click(pa.setVisiblePatient);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        cu.click(pa.selectSaveButton);
        Assert.assertEquals(cu.getElementText(pa.TimeSlotValidation), "Select Available Time Slots");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500);");
        Assert.assertEquals(cu.getElementText(pa.HostValidation), "Host is required.");
        cu.click(pa.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200);");
        Thread.sleep(2000);
        Assert.assertEquals(cu.getElementText(pa.AppointmentTypeValidation), "Appointment type is required.");
	}

	//@Test(priority = 2)
	public void reopenScheduleAndCloseRefresh() throws InterruptedException {
		cu.click(pa.ClickOnScheduleBtn);
		cu.click(pa.ClickOnCloseSchedPopup);
		cu.click(pa.ClickOnRefreshCalendarFilter);
	}
}
