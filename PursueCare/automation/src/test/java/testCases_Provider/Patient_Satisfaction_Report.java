package testCases_Provider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.Appointment_Confirmation_Report_Page;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.Patient_Satisfaction_Report_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.ProviderZoomSessionPage;
import PageObjectClass.Provider_Dashboard_Page;

public class Patient_Satisfaction_Report extends Baseclass{

    private LoginPage lp;
	private Common_Utils cu;
	private ProviderAppointmentsPage pa;
	private Provider_Dashboard_Page pd;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;
    private Appointment_Report_Page pr;
    private Appointment_Confirmation_Report_Page ac;
	private Patient_Satisfaction_Report_Page ps;

    

	@BeforeClass
	public void initPages() {
		lp = new LoginPage(driver);
		cu = new Common_Utils(driver);
		pa = new ProviderAppointmentsPage(driver);
		pd = new Provider_Dashboard_Page(driver);
        dp = new DashBoardPage(driver);
        pz = new ProviderZoomSessionPage(driver);
        pr = new Appointment_Report_Page(driver);
        ac = new Appointment_Confirmation_Report_Page(driver);
		ps = new Patient_Satisfaction_Report_Page(driver);

	}

	@Test
	public void patientSatisfactionReport() throws InterruptedException 
	{
		cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));
		cu.click(pd.ClickReportsDash);
		cu.click(ps.clickPatientSatisfactionReport);
		cu.click(pr.clickSelectPatientFilter);
		cu.enterText(pr.SearchPatientInput, p.getProperty("PatientSearch1"));
		cu.click(pr.clickCheckbxPatientSelect);
		driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
		cu.click(pr.clickResetFilterElement);
		
		cu.click(pr.ClickUserRoleFilter);
            cu.click(pr.ClickProviderRole);
			driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
            Assert.assertEquals("Speciality", cu.getElementText(pr.ClickSpecialityFilterProvider));
            Assert.assertEquals("Provider", cu.getElementText(pr.ClickProviderFilter));
            Assert.assertEquals("Patient Response", cu.getElementText(ps.clickPatientResponse));
            Assert.assertEquals("Entry Status", cu.getElementText(ps.clickEntryStatus));
		
			cu.click(pr.ClickUserRoleFilter);
            cu.click(pr.ClickCaseManagerRole);
            Assert.assertEquals("Case Manager", cu.getElementText(pr.clickCaseManager));

			// PAS role

            cu.click(pr.ClickUserRoleFilter);
            cu.click(pr.ClickPASRole);
            Assert.assertEquals("Patient Access Specialist", cu.getElementText(pr.clickPAS));  

			cu.click(pr.ClickonDateRangeFilter);
            
            pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
            pa.BlockclickDateAfterXDays(driver, 1, "M/d/yyyy");
			cu.click(pr.clickAppointmentReportSearch);
			cu.click(pr.clickResetFilterElement);
			Thread.sleep(2000);

			/*cu.click(pr.clicItemsPerPageFilter);
            int actualValue = Integer.parseInt(cu.getElementText(pr.ClickOnFilterRange25).trim());
            Assert.assertEquals(actualValue, 25);*/

            cu.click(pr.clikconRefresh);
			cu.click(pr.downloadExcelReport);
			
			
			
	}

}
