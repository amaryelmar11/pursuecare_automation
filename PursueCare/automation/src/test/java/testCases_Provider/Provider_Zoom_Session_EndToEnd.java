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
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.ProviderZoomSessionPage;
import PageObjectClass.Provider_Dashboard_Page;

public class Provider_Zoom_Session_EndToEnd extends Baseclass{

    private LoginPage lp;
	private Common_Utils cu;
	private ProviderAppointmentsPage pa;
	private Provider_Dashboard_Page pd;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;
    private Appointment_Report_Page pr;

    

	@BeforeClass
	public void initPages() {
		lp = new LoginPage(driver);
		cu = new Common_Utils(driver);
		pa = new ProviderAppointmentsPage(driver);
		pd = new Provider_Dashboard_Page(driver);
        dp = new DashBoardPage(driver);
        pz = new ProviderZoomSessionPage(driver);
        pr = new Appointment_Report_Page(driver);
	}

    @Test(priority = 1)
    public void EndToEndZoom() throws InterruptedException
    {
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
		cu.enterText(pa.inputPatientName, p.getProperty("PatientSearch1"));
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

     
       cu.click(pz.clickOnJoinSession);
	   Thread.sleep(3000);
	   cu.click(pz.clickonNotNowPatient);

       for (String handle : allwindowHandles) {
        if (handle.equals(mainwindowHandle)) {
            driver.switchTo().window(handle);
            break;
        }
    }

             cu.logout(lp);
             cu.login(lp, p.getProperty("PatientSearch1"), p.getProperty("patientPassword1"));
            Thread.sleep(5000);

            cu.clickJoinMeetingPatient(time);
            Thread.sleep(5000);
            String patientZoomWindow = driver.getWindowHandle();
            //cu.click(pz.clickOnJoinSession);
            Thread.sleep(3000);
           Set<String> windowHandles2 = driver.getWindowHandles();
            for(String handle1 : windowHandles2) 
            {
                if(!handle1.equals(mainwindowHandle) && !handle1.equals(patientZoomWindow)) 
                {
                    driver.switchTo().window(handle1);
                    break;
                }
            }

            cu.click(pz.clickonLeaveOption);
            cu.click(pz.clickOnEndSession);

            cu.selectFromDropdown(pz.StatusSelection, "text", "Completed Successfully");
            Thread.sleep(2000);
            cu.click(pz.SubmitResponse);
            Thread.sleep(8000);

            driver.switchTo().window(mainwindowHandle);

            cu.logout(lp);

            cu.login(lp, p.getProperty("provider2"), p.getProperty("password2"));

            cu.click(pd.ClickReportsDash);   
            cu.click(pd.ClickAppointmentReportDash);  

            cu.click(pr.clickSelectPatientFilter);
            cu.enterText(pr.SearchPatientInput, p.getProperty("PatientSearch1"));
            cu.click(pr.clickCheckbxPatientSelect);
            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
            cu.click(pr.clickAppointmentReportSearch);
            Thread.sleep(2000);
            Assert.assertEquals("Completed Successfully", cu.getElementText(pr.AppointmentCompletionTxtZoomCheck));

    }
}

