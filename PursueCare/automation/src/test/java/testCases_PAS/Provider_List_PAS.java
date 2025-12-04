package testCases_PAS;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import PageObjectClass.PAS_Availability_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.Provider_Availability_Page;
import PageObjectClass.Provider_List_Page;

public class Provider_List_PAS extends Baseclass{

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
    public void CheckProviderList() throws InterruptedException
  {
    cu.login(lp, p.getProperty("pas"), p.getProperty("pass"));

    cu.click(pl.ClickProviderDash);
    cu.click(pl.ClickProviderListDash);

    cu.enterText(pl.SearchFilterBtn, "Jade Wise");

    Thread.sleep(2000);
    cu.click(pl.ClickLicensedStateFilter);
    cu.click(pl.SelectStateConnecticut);
    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

    cu.click(pl.ClickInsurancesFilter);
    cu.click(pl.SelectInsurance);
    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

    cu.click(pl.ClickWeeklySchedule);
    cu.click(pl.SelectDay);
    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

    cu.click(pl.ClickSpeciality);
    cu.click(pl.SelectSpeciality);
    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

    cu.click(pl.ClickEmployeeType);
    cu.click(pl.SelectEmployeeType);
    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

    cu.click(pl.ClickAllowAppointments);
    cu.click(pl.SelectEnabled);

    //Assert.assertEquals(cu.getElementText(pl.SearchFilterBtn), "Jade Wise");
    Assert.assertEquals(cu.getElementText(pl.SelectStateConnecticut), "Connecticut");
    Assert.assertEquals(cu.getElementText(pl.SelectInsurance), "21St Century Cures Act NJ - Registration");
    Assert.assertEquals(cu.getElementText(pl.SelectDay), "Sunday");
    Assert.assertEquals(cu.getElementText(pl.SelectSpeciality), "Admission Counselor");
   // Assert.assertEquals(cu.getElementText(pl.SelectEmployeeType), "SW");
    Assert.assertEquals(cu.getElementText(pl.SelectEnabled), "Enabled");

    cu.click(pl.ClickSearchFilter);
    cu.click(pl.ClickResetFilter);

    Assert.assertEquals(cu.getElementText(pl.ClickLicensedStateFilter), "Licensed State");
    Assert.assertEquals(cu.getElementText(pl.ClickInsurancesFilter), "Insurances");
    Assert.assertEquals(cu.getElementText(pl.ClickWeeklySchedule), "Weekly Schedule");
    Assert.assertEquals(cu.getElementText(pl.ClickSpeciality), "Speciality");
    Assert.assertEquals(cu.getElementText(pl.ClickEmployeeType), "Employee Type");
    Assert.assertEquals(cu.getElementText(pl.ClickAllowAppointments), "Allow Appointments");

    cu.click(pl.ClickRefreshFilter);

    cu.click(pl.ClickAddItemBtn);
    Assert.assertEquals(cu.getElementText(pl.GetAddPNewProvider), "Add New Provider");
    cu.click(pl.CloseAddPNewProvider);

    cu.click(pl.ClickTogglecolumnsBtn);
    cu.click(pl.ClickProviderName);
    cu.click(pl.ClickProviderName);
    driver.navigate().refresh();
    Thread.sleep(2000);
   
    

    cu.click(pl.ClickShowAppointmentPopup);
    Assert.assertEquals(cu.getElementText(pl.GetScheduleAppointmentText), "Schedule Appointment");
    cu.click(pl.CloseAddPNewProvider);
  }
}
