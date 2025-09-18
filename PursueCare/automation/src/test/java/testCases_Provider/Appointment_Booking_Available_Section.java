package testCases_Provider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;

public class Appointment_Booking_Available_Section extends Baseclass {

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
	public void AppointmentBookingAvailable() throws InterruptedException {
		cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));

		// Navigate to Appointment dashboard
		cu.click(pa.selectAppointmentDash);

		// Click on an available slot for today (skips booked slots and selects next valid one)
		pa.clikcOnAvaliableSection();
		Thread.sleep(1500);

		// Fill required fields in schedule popup
        pa.clickAvailableTimeSlots();
		cu.click(pa.selectAppointment);
		
		cu.click(pa.selectAppointmentType);
        Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");
		cu.click(pa.selectHost);
		new Actions(driver).sendKeys(Keys.ENTER).perform();

		cu.click(pa.selectPatient);
		cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch"));
		cu.click(pa.setVisiblePatient);

		// Save appointment
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
		cu.click(pa.selectSaveButton);

		
	}

	@Test(priority = 2)
	public void doubleBookingAvailableSection() throws InterruptedException
	{
		Thread.sleep(1000);
		pa.clickOnBookedSlot();
		cu.click(pa.selectAppointmentType);
        Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");
		cu.click(pa.selectHost);
		new Actions(driver).sendKeys(Keys.ENTER).perform();

		cu.click(pa.selectPatient);
		cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch1"));
		cu.click(pa.setVisiblePatient);

		// Save appointment
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
		cu.click(pa.selectSaveButton);
	}
}
