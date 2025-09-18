package testCases_Provider;

import org.openqa.selenium.By;
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

public class RecurringAppBooking_DailyType extends Baseclass {

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
	public void createDailyRecurringAppointment() throws InterruptedException {
        cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));
        cu.click(pa.selectAppointmentDash);
        WebElement todayCell = pa.getTodayAppXpath();
        cu.click(todayCell);
        Thread.sleep(3000);
        cu.click(pa.selectAppointment);

        cu.click(pa.selectAppointmentType);
        
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

        cu.click(pa.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        cu.click(pa.selectPatient);
        cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch3"));
        cu.click(pa.setVisiblePatient);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        pa.selectNextRangeSlot();
       
        cu.click(pa.clickRecurringCheckbox);
        cu.click(pa.clickRepeatDrpDwn);
        cu.click(pa.clickDailyRepeatDayOption);
        cu.click(pa.clickOnEndDateIcon);
        pa.clickDateAfterXDays(driver, 3, "MMMM d, yyyy");
        Thread.sleep(2000);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        cu.click(pa.selectSaveButton);
        Thread.sleep(2000);

        cu.logout(lp);

	}

        @Test(priority = 2)
        public void providerAppointmentDelete() throws InterruptedException
        {
            cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
       
            cu.click(pa.clickCareTeamDash);
            cu.click(pa.clickAppointments);
    
            cu.click(pa.SelectProviderDropdown);
            cu.click(pa.SelectRecProviderFromDrpdwn);
            new Actions(driver).sendKeys(Keys.ESCAPE).perform();
    
            
            Thread.sleep(1000);
            cu.click(pa.SelectAppointmentForDeletion);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);
            cu.click(pa.ClickAllRecurringMeetingChkbx);
            cu.click(pa.DeleteProviderApp);
            Thread.sleep(1000);
           cu.click(pa.ClickOnDeleteBtnPopup);
           Thread.sleep(2000);

        }
}
