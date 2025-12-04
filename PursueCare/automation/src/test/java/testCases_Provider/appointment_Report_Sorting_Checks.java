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
import utilities.TableSortUtils;

public class appointment_Report_Sorting_Checks extends Baseclass{

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
    public void appointment_Report_Sorting_Checks() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider2"), p.getProperty("password2"));
        cu.click(pd.ClickReportsDash);   
        cu.click(pd.ClickAppointmentReportDash);

         // ----------------- COLUMN 1 -----------------
         Thread.sleep(5000);
         cu.clickcolumnheader(1);
         Thread.sleep(2000);
         List<WebElement> col1Asc = cu.getColumnElements(1);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(1);
         Thread.sleep(2000);
         List<WebElement> col1Desc = cu.getColumnElements(1);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Desc, "desc"));

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
*/

         // ----------------- COLUMN 3 -----------------
         Thread.sleep(2000);
         cu.clickcolumnheader(3);
         Thread.sleep(3000);
         List<WebElement> col3Asc = cu.getColumnElements(3);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(3);
         Thread.sleep(3000);
         List<WebElement> col3Desc = cu.getColumnElements(3);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Desc, "desc"));


         // ----------------- COLUMN 4 -----------------
         Thread.sleep(2000);
         cu.clickcolumnheader(4);
         Thread.sleep(2000);
         List<WebElement> col4Asc = cu.getColumnElements(4);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(4);
         Thread.sleep(2000);
         List<WebElement> col4Desc = cu.getColumnElements(4);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Desc, "desc"));


         // ----------------- COLUMN 5 -----------------
         Thread.sleep(2000);
         Thread.sleep(2000);
         List<WebElement> col5Asc = cu.getColumnElements(5);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col5Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(5);
         Thread.sleep(2000);
         List<WebElement> col5Desc = cu.getColumnElements(5);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col5Desc, "desc"));

/*
         // ----------------- COLUMN 6 -----------------
         Thread.sleep(2000);
         cu.clickcolumnheader(6);
         Thread.sleep(2000);
         List<WebElement> col6Asc = cu.getColumnElements(6);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col6Asc, "asc"));

         Thread.sleep(3000);
         cu.clickcolumnheader(6);
         Thread.sleep(3000);
         List<WebElement> col6Desc = cu.getColumnElements(6);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col6Desc, "desc"));    */


         // ----------------- COLUMN 7 -----------------
         Thread.sleep(2000);
         cu.clickcolumnheader(7);
         Thread.sleep(2000);
         List<WebElement> col7Asc = cu.getColumnElements(7);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col7Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(7);
         Thread.sleep(2000);
         List<WebElement> col7Desc = cu.getColumnElements(7);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col7Desc, "desc"));


         // ----------------- COLUMN 8 -----------------
         Thread.sleep(2000);
         cu.clickcolumnheader(8);
         Thread.sleep(2000);
         List<WebElement> col8Asc = cu.getColumnElements(8);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col8Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(8);
         Thread.sleep(2000);
         List<WebElement> col8Desc = cu.getColumnElements(8);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col8Desc, "desc"));


         // ----------------- COLUMN 9 -----------------
         Thread.sleep(2000);
         cu.clickcolumnheader(9);
         Thread.sleep(2000);
         List<WebElement> col9Asc = cu.getColumnElements(9);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col9Asc, "asc"));

         Thread.sleep(2000);
         cu.clickcolumnheader(9);
         Thread.sleep(2000);
         List<WebElement> col9Desc = cu.getColumnElements(9);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col9Desc, "desc"));
        
    }
}
