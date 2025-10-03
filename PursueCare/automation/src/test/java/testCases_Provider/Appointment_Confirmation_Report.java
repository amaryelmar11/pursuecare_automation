package testCases_Provider;

import org.openqa.selenium.Keys;
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
import PageObjectClass.Appointment_Confirmation_Report_Page;

public class Appointment_Confirmation_Report extends Baseclass{


    private LoginPage lp;
	private Common_Utils cu;
	private ProviderAppointmentsPage pa;
	private Provider_Dashboard_Page pd;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;
    private Appointment_Report_Page pr;
    private Appointment_Confirmation_Report_Page ac;
    

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
	}


    @Test
    public void Confirmation_Checks() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider2"), p.getProperty("password2"));
        cu.click(pd.ClickReportsDash);  

        cu.click(ac.clickAppointmentConfirReport);

        cu.enterText(ac.SearchInputbutton, p.getProperty("PatientSearch"));
        cu.click(ac.ClickResponseDrpDwn);
        cu.click(ac.ClickConfirmByPatientCheckbox);
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        cu.click(ac.ClickMeetingHostType);

        cu.click(ac.ClickProviderRole);
        Assert.assertEquals(cu.getElementText(ac.GetProviderNameDetails), cu.getElementText(ac.CheckProviderNameInMeetingHost));
        
        cu.click(pr.ClickonDateRangeFilter);
            
        pa.BlockclickDateAfterXDays(driver, 0, "M/dd/yyyy");
        pa.BlockclickDateAfterXDays(driver, 1, "M/dd/yyyy");
        cu.click(ac.ClickConnectedCMDrpDwn);
        cu.click(ac.SelectCMDrpDwn);
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        cu.click(ac.SelectSourceDrpDwn);
        cu.click(ac.ClickSMSOption);
        cu.click(ac.SelectEntryStatusDrpDwn);
        cu.click(ac.ClickCheckedOffOption);
        cu.click(pr.clickAppointmentReportSearch);
        cu.click(pr.clickResetFilterElement);
        Thread.sleep(2000);
        Assert.assertEquals("Response", cu.getElementText(ac.ResponseDrpDwnDeafaultValue));
        System.out.println(cu.getElementText(ac.ClickResponseDrpDwn));
        Assert.assertEquals("Meeting Host Type", cu.getElementText(ac.MeetingHostDefaultValue));
        Assert.assertEquals("Source", cu.getElementText(ac.SourceDefaultValue));
        Assert.assertEquals("Connected case manager", cu.getElementText(ac.ConnectedCMDefaultValue));


        cu.click(pr.clikconRefresh);
        cu.click(pr.downloadExcelReport);


    }

}
