package testCases_Provider;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

public class Appointment_Report_Checks extends Baseclass{

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


    @Test
    public void appointment_Report_Filter_Checks() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider2"), p.getProperty("password2"));
        cu.click(pd.ClickReportsDash);   
            cu.click(pd.ClickAppointmentReportDash);  

            cu.click(pr.clickSelectPatientFilter);
            cu.enterText(pr.SearchPatientInput, p.getProperty("PatientSearch1"));
            cu.click(pr.clickCheckbxPatientSelect);
            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
            cu.click(pr.clickResetFilterElement);
            
            cu.click(pr.ClickUserRoleFilter);
            cu.click(pr.ClickProviderRole);
            Assert.assertEquals("Speciality", cu.getElementText(pr.ClickSpecialityFilterProvider));
            Assert.assertEquals("Provider", cu.getElementText(pr.ClickProviderFilter));
            Assert.assertEquals("Status", cu.getElementText(pr.clickStatusFilterforProvider));
            Assert.assertEquals("Appointment Type", cu.getElementText(pr.clickAppointmentTypeProvider));

            cu.click(pr.clickResetFilterElement);

            // CM role
            
            cu.click(pr.ClickUserRoleFilter);
            cu.click(pr.ClickCaseManagerRole);
            Assert.assertEquals("Case Manager", cu.getElementText(pr.clickCaseManager));

            // PAS role

            cu.click(pr.ClickUserRoleFilter);
            cu.click(pr.ClickPASRole);
            Assert.assertEquals("Patient Access Specialist", cu.getElementText(pr.clickPAS));  

            Thread.sleep(2000);
            cu.click(pr.ClickonDateRangeFilter);
            
            pa.BlockclickDateAfterXDays(driver, 0, "M/dd/yyyy");
            pa.BlockclickDateAfterXDays(driver, 1, "M/dd/yyyy");
            cu.click(pr.clickAppointmentReportSearch);
            Thread.sleep(2000);
            cu.click(pr.clickResetFilterElement);
            cu.click(pr.clicItemsPerPageFilter);
            int actualValue = Integer.parseInt(cu.getElementText(pr.ClickOnFilterRange25).trim());
            Assert.assertEquals(actualValue, 25);

            cu.click(pr.clikconRefresh);

            // Check the sorting Ascending or Descending
            List<WebElement> sort = pr.SortingRowsAppReport;

            //pr.getColumnElements(driver, sort, 8 );
    }
}

    
