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
import PageObjectClass.No_Show_Report_Page;
import PageObjectClass.Patient_Satisfaction_Report_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.ProviderZoomSessionPage;
import PageObjectClass.Provider_Dashboard_Page;

public class No_Show_Report extends Baseclass{

    private LoginPage lp;
	private Common_Utils cu;
	private ProviderAppointmentsPage pa;
	private Provider_Dashboard_Page pd;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;
    private Appointment_Report_Page pr;
    private Appointment_Confirmation_Report_Page ac;
	private Patient_Satisfaction_Report_Page ps;
	private No_Show_Report_Page ns;


    

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
		ns = new No_Show_Report_Page(driver);

	}

	@Test
	public void patientSatisfactionReport() throws InterruptedException 
	{
		cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));
		cu.click(pd.ClickReportsDash);
		cu.click(ns.clickNoshowReport);
		cu.enterText(ns.BtnSearch, p.getProperty("PatientSearch1"));
		cu.click(pr.clickAppointmentReportSearch);
		cu.click(pr.clickResetFilterElement);
		Assert.assertEquals("", cu.getElementText(ns.BtnSearch));

		Assert.assertEquals("Medical NSR", cu.getElementText(ns.clickMedicalNSRDrpDwn));
		Assert.assertEquals("Counseling NSR", cu.getElementText(ns.clickCounsellingNSRDrpDwn));
		Assert.assertEquals("State", cu.getElementText(ns.clickStateDrpDwn));
		//Assert.assertEquals("Program", cu.getElementText(ns.clickStateProgramDrpDwn));
		Assert.assertEquals("Last Tox Screen", cu.getElementText(ns.clickToxSxreenDrpDwn));
		Assert.assertEquals("Provider", cu.getElementText(pr.ClickUserRoleFilter));
		Assert.assertEquals("Speciality", cu.getElementText(pr.ClickSpecialityFilterProvider));
		//Assert.assertEquals(cu.getElementText(ns.ProviderNameForValidation), cu.getElementText(pr.ClickProviderFilter));
		
		//Click Medical NSR
		cu.click(ns.clickMedicalNSRDrpDwn);	
		cu.click(ns.SelectLowNSROption);
		cu.click(ns.clickCounsellingNSRDrpDwn);
		cu.click(ns.SelectLowNSROption);
		cu.click(ns.clickStateDrpDwn);
		cu.click(ns.SelectAlabamaStateOption);
		//cu.click(ns.clickStateProgramDrpDwn);
		//cu.click(ns.SelectProgramOption);
		cu.click(ns.clickToxSxreenDrpDwn);
		cu.click(ns.SelectToxScreenOption);
		//cu.click(ns.clickStateProgramDrpDwn);
		//.click(ns.SelectProgramOption);
		cu.click(ns.clickToxSxreenDrpDwn);
		cu.click(ns.SelectToxScreenOption);
		cu.click(pr.ClickonDateRangeFilter);
            
		pa.BlockclickDateAfterXDays(driver, 0, "M/dd/yyyy");
		pa.BlockclickDateAfterXDays(driver, 1, "M/dd/yyyy");
		cu.click(pr.clickAppointmentReportSearch);
		cu.click(pr.clickResetFilterElement);
		Thread.sleep(2000);

		Assert.assertEquals("Medical NSR", cu.getElementText(ns.clickMedicalNSRDrpDwn));
		Assert.assertEquals("Counseling NSR", cu.getElementText(ns.clickCounsellingNSRDrpDwn));
		Assert.assertEquals("State", cu.getElementText(ns.clickStateDrpDwn));
		//Assert.assertEquals("Program", cu.getElementText(ns.clickStateProgramDrpDwn));
		Assert.assertEquals("Last Tox Screen", cu.getElementText(ns.clickToxSxreenDrpDwn));
		Assert.assertEquals("Provider", cu.getElementText(pr.ClickUserRoleFilter));
		Assert.assertEquals("Speciality", cu.getElementText(pr.ClickSpecialityFilterProvider));
		//Assert.assertEquals(cu.getElementText(ns.ProviderNameForValidation), cu.getElementText(pr.ClickProviderFilter));

            

			/*cu.click(pr.clicItemsPerPageFilter);
            int actualValue = Integer.parseInt(cu.getElementText(pr.ClickOnFilterRange25).trim());
            Assert.assertEquals(actualValue, 25);*/

            cu.click(pr.clikconRefresh);
			cu.click(pr.downloadExcelReport);
		
	}

}
