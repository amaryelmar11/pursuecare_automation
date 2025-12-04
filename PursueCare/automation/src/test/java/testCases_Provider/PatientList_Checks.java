package testCases_Provider;

import java.util.List;

import org.openqa.selenium.WebElement;
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
import PageObjectClass.Patient_List_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.Provider_Availability_Page;
import PageObjectClass.Provider_List_Page;
import utilities.TableSortUtils;

public class PatientList_Checks extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage pa;
    private PAS_Availability_Page ap;
    private Provider_Availability_Page pd;
    private Appointment_Report_Page ar;
    private DashBoardPage dp;
    private Appointment_Confirmation_Report_Page arc;
    private Provider_List_Page pl;
    private Appointment_Report_Page pr;
    private Patient_List_Page pc;

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
        pr = new Appointment_Report_Page(driver);
        pc = new Patient_List_Page(driver);
    }

    @Test(priority = 1)
    public void checkPatientList() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));
       
        cu.click(dp.clickOnPatients);
        Thread.sleep(2000);
        cu.click(pr.ClickonDateRangeFilter); 
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");

        cu.click(pc.SelectAppointmentRequests);
        cu.click(pc.SelectEnableOption);

        Assert.assertEquals(cu.getElementText(pc.SelectEnableOption), "Enabled");

        cu.click(pl.ClickResetFilter);
        Assert.assertEquals(cu.getElementText(pc.GetAppReqAllText), "All");

        // Sorting Of the columns checks

       // ----------------- COLUMN 1 -----------------
        Thread.sleep(2000);
        cu.clickcolumnheader(1);
        Thread.sleep(2000);
        List<WebElement> col1Asc = cu.getColumnElements(1);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(1);
        Thread.sleep(2000);
        List<WebElement> col1Desc = cu.getColumnElements(1);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Desc, "desc"));

        cu.click(pl.ClickResetFilter);

/*
        // ----------------- COLUMN 2 -----------------
        Thread.sleep(2000);
        cu.clickcolumnheader(2);
        Thread.sleep(2000);
        List<WebElement> col2Asc = cu.getColumnElements(2);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col2Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(2);
        Thread.sleep(2000);
        List<WebElement> col2Desc = cu.getColumnElements(2);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col2Desc, "desc"));

        cu.click(pl.ClickResetFilter); 


        // ----------------- COLUMN 3 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(3);
        Thread.sleep(2000);
        List<WebElement> col3Asc = cu.getColumnElements(3);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(3);
        Thread.sleep(3000);
        List<WebElement> col3Desc = cu.getColumnElements(3);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Desc, "desc"));

        cu.click(pl.ClickResetFilter); */


        // ----------------- COLUMN 4 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(4);
        Thread.sleep(2000);
        List<WebElement> col4Asc = cu.getColumnElements(4);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(4);
        Thread.sleep(3000);
        List<WebElement> col4Desc = cu.getColumnElements(4);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Desc, "desc"));

        cu.click(pl.ClickResetFilter);


        // ----------------- COLUMN 5 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(5);
        Thread.sleep(2000);
        List<WebElement> col5Asc = cu.getColumnElements(5);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col5Asc, "asc"));

        Thread.sleep(3000);
        cu.clickcolumnheader(5);
        Thread.sleep(2000);
        List<WebElement> col5Desc = cu.getColumnElements(5);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col5Desc, "desc"));

        cu.click(pl.ClickResetFilter);

/*        // ----------------- COLUMN 6 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(6);
        Thread.sleep(2000);
        List<WebElement> col6Asc = cu.getColumnElements(6);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col6Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(6);
        Thread.sleep(2000);
        List<WebElement> col6Desc = cu.getColumnElements(6);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col6Desc, "desc"));

        cu.click(pl.ClickResetFilter); */



        // ----------------- COLUMN 7 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(7);
        Thread.sleep(2000);
        List<WebElement> col7Asc = cu.getColumnElements(7);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col7Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(7);
        Thread.sleep(3000);
        List<WebElement> col7Desc = cu.getColumnElements(7);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col7Desc, "desc"));

        cu.click(pl.ClickResetFilter);


        // ----------------- COLUMN 8 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(8);
        Thread.sleep(2000);
        List<WebElement> col8Asc = cu.getColumnElements(8);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col8Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(8);
        Thread.sleep(3000);
        List<WebElement> col8Desc = cu.getColumnElements(8);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col8Desc, "desc"));

        cu.click(pl.ClickResetFilter);

/*
        // ----------------- COLUMN 9 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(9);
        Thread.sleep(2000);
        List<WebElement> col9Asc = cu.getColumnElements(9);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col9Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(9);
        Thread.sleep(3000);
        List<WebElement> col9Desc = cu.getColumnElements(9);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col9Desc, "desc"));

        cu.click(pl.ClickResetFilter); */

/*
        // ----------------- COLUMN 10 -----------------
        Thread.sleep(5000);
        cu.clickcolumnheader(10);
        Thread.sleep(2000);
        List<WebElement> col10Asc = cu.getColumnElements(10);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col10Asc, "asc"));

        Thread.sleep(2000);
        cu.clickcolumnheader(10);
        Thread.sleep(3000);
        List<WebElement> col10Desc = cu.getColumnElements(10);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col10Desc, "desc"));

        cu.click(pl.ClickResetFilter); */

    }

}
