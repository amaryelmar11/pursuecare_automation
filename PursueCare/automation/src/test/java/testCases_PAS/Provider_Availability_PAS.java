package testCases_PAS;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.Appointment_Confirmation_Report_Page;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.PAS_Availability_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.Provider_Availability_Page;
import PageObjectClass.Provider_List_Page;

public class Provider_Availability_PAS extends Baseclass{
    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage pa;
    private PAS_Availability_Page ap;
    private Provider_Availability_Page pd;
    private Appointment_Report_Page ar;
    private DashBoardPage dp;
    private Appointment_Confirmation_Report_Page arc;
    private Provider_List_Page pl;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
        ap = new PAS_Availability_Page(driver);
        pd = new Provider_Availability_Page(driver);
        ar = new Appointment_Report_Page(driver);
        dp = new DashBoardPage(driver);
        arc = new Appointment_Confirmation_Report_Page(driver);
        pl = new Provider_List_Page(driver);
    }

    @Test(priority=1)
    public void checkPASPRoviderAvailabilityPage() throws InterruptedException
    {
        cu.login(lp, p.getProperty("pas"), p.getProperty("pass"));

    cu.click(pl.ClickProviderDash);
    cu.click(pd.ClickProviderAvailDash);
    cu.enterText(pd.SearchProviderAvailability, "Jade Wise");
    driver.navigate().refresh();

    cu.click(pd.ViewProviderAvailability);
    
    Assert.assertEquals(cu.getElementText(pd.GetViewProviderAvailText), "Provider Time Slots");
    cu.click(pl.CloseAddPNewProvider);
    Thread.sleep(2000);

    cu.click(pd.EditProviderAvailability);
    Thread.sleep(2000);
    Assert.assertEquals(cu.getElementText(pd.GetAvailabilityScheduleText), "Availability Schedule");
    Thread.sleep(2000);
    cu.click(pl.ClickProviderDash);
    cu.click(pd.ClickProviderAvailDash);
    Thread.sleep(2000);
    cu.click(pd.DeleteProviderAvailability);
    Assert.assertEquals(cu.getElementText(pd.DeleteProviderAvailText), "Delete Provider Availability");
    cu.click(pd.ClickCancelBtn);

    cu.click(pl.ClickRefreshFilter);
    cu.click(pl.ClickAddItemBtn);
    Assert.assertEquals(cu.getElementText(pd.GetAvailabilityScheduleText), "Availability Schedule");

    cu.click(pl.ClickProviderDash);
    cu.click(pd.ClickProviderAvailDash);

    cu.click(pd.ClickExportToExcel);
    
    }
}
