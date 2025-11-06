package testCases_Provider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;

public class CalendarFiltersValidations extends Baseclass {

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
    public void CheckingwithOneSelection() throws InterruptedException {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));

        cu.click(pa.selectAppointmentDash);
         Thread.sleep(2000);
        cu.click(pa.SelectPASDrpDwn);
        cu.click(pa.SelectPASFilterChk1);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(pa.SelectCMDrpDwn);
        cu.click(pa.SelectCMFilterChk1);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(pa.ClickOnScheduleBtn);

        String Value = cu.getElementText(pa.GetProviderName);
        Assert.assertEquals(Value, "Jade Wise");

        String Value1 = cu.getElementText(pa.GetIntakename);
        Assert.assertEquals(Value1, "Saavi intake C");

        String Value2 = cu.getElementText(pa.GetCMName);
        Assert.assertEquals(Value2, "Akshay Avhad");

        cu.click(pa.ClickOnCloseSchedPopup);
        cu.click(pa.ClickOnRefreshCalendarFilter); 

    }

    @Test(priority = 2)
    public void CheckingWithDoubleSelection() throws InterruptedException {
        

        cu.click(pa.SelectProviderDropdown);
        cu.click(pa.SelectProviderFilterChk2);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        // Intake: select two
        cu.click(pa.SelectPASDrpDwn);
        cu.click(pa.SelectPASFilterChk1);
        cu.click(pa.SelectPASFilterChk2);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        // Care Coordinator: select two
        cu.click(pa.SelectCMDrpDwn);
        cu.click(pa.SelectCMFilterChk1);
        cu.click(pa.SelectCMFilterChk2);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(pa.ClickOnScheduleBtn);

        String Provider = cu.getElementText(pa.EmptyProviderMultipleSelection);
        Assert.assertEquals(Provider, "Provider" );

        String intakeSelected = cu.getElementText(pa.EmptyCMMultipleSelection);
        Assert.assertEquals(intakeSelected, "Case Manager" );

        String cmSelected = cu.getElementText(pa.EmptyPASMultipleSelection);
        Assert.assertEquals(cmSelected, "Patient Access Specialist" );
        System.out.println(Provider + " " + intakeSelected + " " + cmSelected);
        cu.click(pa.ClickOnCloseSchedPopup);
        cu.click(pa.ClickOnRefreshCalendarFilter); 
    }

    @Test(priority = 3)
    public void AvailableSectionFilterChecks() throws InterruptedException {
        // Assumes user is on Appointments dashboard
        cu.click(pa.AvailableSectionClick);
        cu.click(pa.SelectPASDrpDwn);
        cu.click(pa.SelectPASFilterChk1);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(pa.SelectCMDrpDwn);
        cu.click(pa.SelectCMFilterChk1);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(pa.ClickOnScheduleBtn);

        String Value = cu.getElementText(pa.GetProviderName);
        Assert.assertEquals(Value, "Jade Wise");

        String Value1 = cu.getElementText(pa.GetIntakename);
        Assert.assertEquals(Value1, "Saavi intake C");

        String Value2 = cu.getElementText(pa.GetCMName);
        Assert.assertEquals(Value2, "Akshay Avhad");

        cu.click(pa.ClickOnCloseSchedPopup);
        cu.click(pa.ClickOnRefreshCalendarFilter); 

        cu.click(pa.SelectProviderDropdown);
        cu.click(pa.SelectProviderFilterChk2);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        // Intake: select two
        cu.click(pa.SelectPASDrpDwn);
        cu.click(pa.SelectPASFilterChk1);
        cu.click(pa.SelectPASFilterChk2);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        // Care Coordinator: select two
        cu.click(pa.SelectCMDrpDwn);
        cu.click(pa.SelectCMFilterChk1);
        cu.click(pa.SelectCMFilterChk2);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(pa.ClickOnScheduleBtn);

        String Provider = cu.getElementText(pa.EmptyProviderMultipleSelection);
        Assert.assertEquals(Provider, "Provider" );

        String intakeSelected = cu.getElementText(pa.EmptyCMMultipleSelection);
        Assert.assertEquals(intakeSelected, "Case Manager" );

        String cmSelected = cu.getElementText(pa.EmptyPASMultipleSelection);
        Assert.assertEquals(cmSelected, "Patient Access Specialist" );
        System.out.println(Provider + " " + intakeSelected + " " + cmSelected);
        cu.click(pa.ClickOnCloseSchedPopup);
        cu.click(pa.ClickOnRefreshCalendarFilter); 

    }
}
