package testCases_Provider;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.Provider_Dashboard_Page;
import utilities.TableSortUtils;

public class Provider_DashBoard_Checks extends Baseclass{

    private Common_Utils cu;
    private Provider_Dashboard_Page pd;
    private LoginPage lp;
    private WebDriverWait wait;

    @BeforeClass
    public void initPages()
    {
        cu = new Common_Utils(driver);
        pd = new Provider_Dashboard_Page(driver);
        lp = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void Basic_Checks_Dashboard() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));

        // Check Dashboard Text
        wait.until(ExpectedConditions.visibilityOf(pd.dashBoardText));
        Assert.assertEquals(cu.getElementText(pd.dashBoardText), "Dashboard");
        Assert.assertTrue(pd.dashBoardText.isDisplayed(), "Dashboard text is not visible");
        Assert.assertTrue(pd.dashBoardText.isEnabled(), "Dashboard text is not accessible");

        // Check Select Timezone Text
        wait.until(ExpectedConditions.visibilityOf(pd.SelectTimeZoneDashText));
        Assert.assertEquals(cu.getElementText(pd.SelectTimeZoneDashText), "Select Timezone");
        Assert.assertTrue(pd.SelectTimeZoneDashText.isDisplayed(), "Select Timezone text is not visible");
        Assert.assertTrue(pd.SelectTimeZoneDashText.isEnabled(), "Select Timezone text is not accessible");

        // Check Full Screen Icon
        wait.until(ExpectedConditions.visibilityOf(pd.SelectFullScreenIcon));
        cu.click(pd.SelectFullScreenIcon);
        Thread.sleep(1000);
        cu.click(pd.SelectFullScreenIcon);
        Assert.assertTrue(pd.SelectFullScreenIcon.isDisplayed());
        Assert.assertTrue(pd.SelectFullScreenIcon.isEnabled());

        // Check Chat Icon
        wait.until(ExpectedConditions.visibilityOf(pd.SelectChatIcon));
        Assert.assertTrue(pd.SelectChatIcon.isDisplayed(), "Chat Icon is not visible");
        Assert.assertTrue(pd.SelectChatIcon.isEnabled(), "Chat Icon is not accessible");

        // Check Lightning Step Navigation Logo
        wait.until(ExpectedConditions.visibilityOf(pd.LighteningStepNavigation));
        Assert.assertTrue(pd.LighteningStepNavigation.isDisplayed(), "Lightning Step Navigation is not visible");
        Assert.assertTrue(pd.LighteningStepNavigation.isEnabled(), "Lightning Step Navigation is not accessible");

        // Check PursueCare Logo
        wait.until(ExpectedConditions.visibilityOf(pd.PursueCareLogo));
        Assert.assertTrue(pd.PursueCareLogo.isDisplayed(), "PursueCare Logo is not visible");
        Assert.assertTrue(pd.PursueCareLogo.isEnabled(), "PursueCare Logo is not accessible");

        // Check Breadcrumb Menu Icon
        wait.until(ExpectedConditions.visibilityOf(pd.BreadCumbMenuIcon));
        Assert.assertTrue(pd.BreadCumbMenuIcon.isDisplayed(), "Breadcrumb Menu Icon is not visible");
        Assert.assertTrue(pd.BreadCumbMenuIcon.isEnabled(), "Breadcrumb Menu Icon is not accessible");
    }

    @Test(priority = 2)
    public void TodaysAppointmentListChecks()
    {
        
        Assert.assertEquals(cu.getElementText(pd.todaysAppointment), "Today's Appointments");

        // Store selected values in variables named after WebElement names
         String meetingStatusValue = cu.selectFromDropdown(pd.meetingStatus, "value", "2");
        String stateValue = cu.selectFromDropdown(pd.state, "value", "2");
        String medicalNSRValue = cu.selectFromDropdown(pd.medicalNSR, "value", "2");
        String counsellingNSRValue = cu.selectFromDropdown(pd.counsellingNSR, "value", "2");
        String appointmentStatusValue = cu.selectFromDropdown(pd.appointmentStatus, "value", "2");
        //String programValue = cu.selectFromDropdown(pd.program, "text", "Unassigned");



        cu.click(pd.resetFilterTodaysAppointment);

        Assert.assertNotEquals("Meeting Status", meetingStatusValue);
        Assert.assertNotEquals("State", stateValue);
        Assert.assertNotEquals("Medical NSR",  medicalNSRValue);
        Assert.assertNotEquals("Counseling NSR", counsellingNSRValue);
        Assert.assertNotEquals("Appointment Status", appointmentStatusValue);
       // Assert.assertNotEquals("Program", programValue);

        cu.click(pd.actionButton);

       Assert.assertEquals("Canceled by Staff", cu.getElementText(pd.cancelByStaff));
       Assert.assertEquals("Completed Successfully", cu.getElementText(pd.completedSuccessfully));
       Assert.assertEquals("Patient No Show", cu.getElementText(pd.patientNoShow));
       Assert.assertEquals("Not Completed - Tech Issue", cu.getElementText(pd.notCompletedTechIssue));

       new Actions(driver).sendKeys(Keys.ESCAPE).perform(); 
    }

    @Test(priority = 3)
    public void TodaysAppointmentListSortingChecks() throws InterruptedException
    {/*
        // ----------------- COLUMN 1 -----------------
        Thread.sleep(5000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 1);
        Thread.sleep(2000);
        List<WebElement> col1Asc = cu.getDashColumnElements("todaysappointmentslist", 1);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 1);
        Thread.sleep(2000);
        List<WebElement> col1Desc = cu.getDashColumnElements("todaysappointmentslist", 1);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Desc, "desc"));
    */
        // ----------------- COLUMN 2 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 2);
        Thread.sleep(2000);
        List<WebElement> col2Asc = cu.getDashColumnElements("todaysappointmentslist", 2);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col2Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 2);
        Thread.sleep(2000);
        List<WebElement> col2Desc = cu.getDashColumnElements("todaysappointmentslist", 2);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col2Desc, "desc"));
    /*
        // ----------------- COLUMN 3 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 3);
        Thread.sleep(2000);
        List<WebElement> col3Asc = cu.getDashColumnElements("todaysappointmentslist", 3);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 3);
        Thread.sleep(2000);
        List<WebElement> col3Desc = cu.getDashColumnElements("todaysappointmentslist", 3);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Desc, "desc"));
    */
        // ----------------- COLUMN 4 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 4);
        Thread.sleep(2000);
        List<WebElement> col4Asc = cu.getDashColumnElements("todaysappointmentslist", 4);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 4);
        Thread.sleep(2000);
        List<WebElement> col4Desc = cu.getDashColumnElements("todaysappointmentslist", 4);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Desc, "desc"));

        // ----------------- COLUMN 5 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 5);
        Thread.sleep(2000);
        List<WebElement> col5Asc = cu.getDashColumnElements("todaysappointmentslist", 5);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col5Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 5);
        Thread.sleep(2000);
        List<WebElement> col5Desc = cu.getDashColumnElements("todaysappointmentslist", 5);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col5Desc, "desc"));

        // ----------------- COLUMN 6 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 6);
        Thread.sleep(2000);
        List<WebElement> col6Asc = cu.getDashColumnElements("todaysappointmentslist", 6);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col6Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 6);
        Thread.sleep(2000);
        List<WebElement> col6Desc = cu.getDashColumnElements("todaysappointmentslist", 6);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col6Desc, "desc"));

        // ----------------- COLUMN 7 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 7);
        Thread.sleep(2000);
        List<WebElement> col7Asc = cu.getDashColumnElements("todaysappointmentslist", 7);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col7Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 7);
        Thread.sleep(2000);
        List<WebElement> col7Desc = cu.getDashColumnElements("todaysappointmentslist", 7);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col7Desc, "desc"));

        /*
        // ----------------- COLUMN 8 -----------------
        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 8);
        Thread.sleep(2000);
        List<WebElement> col8Asc = cu.getDashColumnElements("todaysappointmentslist", 8);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col8Asc, "asc"));

        Thread.sleep(2000);
        cu.clickDashTableColumnHeader("todaysappointmentslist", 8);
        Thread.sleep(2000);
        List<WebElement> col8Desc = cu.getDashColumnElements("todaysappointmentslist", 8);
        Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col8Desc, "desc"));
    */
         // ----------------- COLUMN 9 -----------------
         Thread.sleep(2000);
         cu.clickDashTableColumnHeader("todaysappointmentslist", 10);
         Thread.sleep(2000);
         List<WebElement> col9Asc = cu.getDashColumnElements("todaysappointmentslist", 10);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col9Asc, "asc"));
 
         Thread.sleep(2000);
         cu.clickDashTableColumnHeader("todaysappointmentslist", 10);
         Thread.sleep(2000);
         List<WebElement> col9Desc = cu.getDashColumnElements("todaysappointmentslist", 10);
         Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col9Desc, "desc"));
    }

   


    @Test(priority = 4)
    public void CompletedPatientAssessments() throws InterruptedException
    {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");
        Thread.sleep(2000);
       // String ClickProgrmvalue = cu.selectFromDropdown(pd.ClickProgrm, "value", "SUD");
        String CleckAssessmentValue = cu.selectFromDropdown(pd.CleckAssessment, "value", "2");
         cu.click(pd.ClickDateIcon);
         cu.doubleClickElement(driver, pd.ClickTodayDate);
        
        cu.click(pd.ClickResetFilterCompletedPatientAssessments);

        //Assert.assertNotEquals("Program", ClickProgrmvalue);
        Assert.assertNotEquals("Assessment", CleckAssessmentValue);

        String patientName = cu.getElementText(pd.getPatientName);

        cu.click(pd.ClickOnViewPatientResponse);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//div[contains(@class,'row label col-md-12 m-l-5 color-black legendcursor')]/strong")));
        String patientNameAss = cu.getElementText(pd.ValidatePatientNameFromAssessScreen);
            System.out.println(patientName + " " + patientNameAss);
            Assert.assertEquals(patientName, patientNameAss);
            cu.click(pd.CuttheAssessmentScreen); 
        }

        @Test(priority = 5)
        public void sortingPatientAssessmentChecks() throws InterruptedException
        {
            // ----------------- COLUMN 1 -----------------
            Thread.sleep(5000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 1);
            Thread.sleep(2000);
            List<WebElement> col1Asc = cu.getDashColumnElements("completedpatientassessmentslist", 1);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Asc, "asc"));
    
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 1);
            Thread.sleep(2000);
            List<WebElement> col1Desc = cu.getDashColumnElements("completedpatientassessmentslist", 1);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col1Desc, "desc"));
    
            // ----------------- COLUMN 2 -----------------
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 2);
            Thread.sleep(2000);
            List<WebElement> col2Asc = cu.getDashColumnElements("completedpatientassessmentslist", 2);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col2Asc, "asc"));
    
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 2);
            Thread.sleep(2000);
            List<WebElement> col2Desc = cu.getDashColumnElements("completedpatientassessmentslist", 2);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col2Desc, "desc"));
    
            // ----------------- COLUMN 3 -----------------
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 3);
            Thread.sleep(2000);
            List<WebElement> col3Asc = cu.getDashColumnElements("completedpatientassessmentslist", 3);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Asc, "asc"));
    
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 3);
            Thread.sleep(2000);
            List<WebElement> col3Desc = cu.getDashColumnElements("completedpatientassessmentslist", 3);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col3Desc, "desc"));
    
            // ----------------- COLUMN 4 -----------------
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 4);
            Thread.sleep(2000);
            List<WebElement> col4Asc = cu.getDashColumnElements("completedpatientassessmentslist", 4);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Asc, "asc"));
    
            Thread.sleep(2000);
            cu.clickDashTableColumnHeader("completedpatientassessmentslist", 4);
            Thread.sleep(2000);
            List<WebElement> col4Desc = cu.getDashColumnElements("completedpatientassessmentslist", 4);
            Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(col4Desc, "desc"));
        }
    }
