package testCases_Provider;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.ProviderZoomSessionPage;
import PageObjectClass.Provider_Dashboard_Page;

public class Provider_ZoomSession_Checks extends Baseclass {

	private LoginPage lp;
	private Common_Utils cu;
	private ProviderAppointmentsPage pa;
	private Provider_Dashboard_Page pd;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;

    

	@BeforeClass
	public void initPages() {
		lp = new LoginPage(driver);
		cu = new Common_Utils(driver);
		pa = new ProviderAppointmentsPage(driver);
		pd = new Provider_Dashboard_Page(driver);
        dp = new DashBoardPage(driver);
        pz = new ProviderZoomSessionPage(driver);
	}

	// Schedules a provider appointment (meeting) similar to other flows
	@Test(priority = 1)
	public void providerCanScheduleMeeting() throws InterruptedException {
		cu.login(lp, p.getProperty("provider2"), p.getProperty("password2"));

		// Open Appointments and open today's slot
	 	cu.click(pa.selectAppointmentDash);
		cu.click(pa.getTodayAppXpath());
		Thread.sleep(1500);

		// Select appointment details
		cu.click(pa.selectAppointment);
		cu.click(pa.selectAppointmentType);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

		cu.click(pa.selectHost);
		new Actions(driver).sendKeys(Keys.ENTER).perform();

		cu.click(pa.selectPatient);
		cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch3"));
		cu.click(pa.setVisiblePatient);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

		// Start/End time
		cu.click(pa.selectStartTime);
		cu.click(pa.selectSetStartTime);
		cu.click(pa.selectEndTime);
		cu.click(pa.selectAddhour);
		cu.click(pa.selectSetEndTime);
        String time = cu.getElementText(pa.selectStartTime);
		System.out.println(time);
		// Save meeting
		cu.click(pa.selectSaveButton);
		Thread.sleep(6000);
        cu.click(dp.ClickOnDashboardHome);
		Thread.sleep(3000);
        String name = cu.getElementText(dp.getProviderNameDash);
        cu.getElementText(dp.getProviderNameDash);
        String mainwindowHandle = driver.getWindowHandle();
		System.out.println(mainwindowHandle);
        cu.clickJoinMeetingprovider(time);
		Thread.sleep(6000);
        Set<String> allwindowHandles = driver.getWindowHandles();	
        System.out.println(allwindowHandles);
        for (String handle : allwindowHandles) {
            if (!handle.equals(mainwindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        String providerZoomWindow = driver.getWindowHandle();

       cu.click(pz.clickOnMicIcon);
       cu.click(pz.clickOnVideoIcon);
       cu.click(pz.OpenBackgroungImages);
       cu.click(pz.ClickOnBackgroundImage);
       cu.click(pz.ClickOnBackgroundImage);

       cu.click(pz.ClickOnCutBackImages);
       cu.click(pz.clickOnTestSpeaker);
       cu.click(pz.clickOnTestSpeaker2);
       cu.click(pz.clickOnJoinSession);
	   Thread.sleep(3000);
	   cu.click(pz.clickonNotNowPatient);

	   cu.selectFromDropdown(pz.ClickInterpreterLang, "text", "American Sign Language" );
	   cu.click(pz.ClickCallInterpreterBtn);
	 	 Thread.sleep(2000);
	   String toastText = cu.getElementText(pz.CallInterpreterToast);
	   Thread.sleep(4000);
	  // Assert.assertEquals("toastText", "Accepted Request processed successfully");
	   cu.click(pz.clickonLeaveOption);

       cu.click(pz.clickOnLeaveSession);
      
	}

	
}
