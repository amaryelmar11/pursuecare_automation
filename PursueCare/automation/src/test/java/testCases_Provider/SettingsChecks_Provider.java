package testCases_Provider;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import PageObjectClass.Patient_Creation_Page;
import PageObjectClass.Patient_List_Page;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.ProviderChatPage;
import PageObjectClass.Provider_Availability_Page;
import PageObjectClass.Provider_List_Page;
import PageObjectClass.Settings_Page_Provider;
import PageObjectClass.TimeBlockPage;
import utilities.TableSortUtils;

public class SettingsChecks_Provider extends Baseclass{

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
    private Settings_Page_Provider sp;
    private TimeBlockPage tb;
    private Patient_Creation_Page pcp;
    private ProviderChatPage pcc;

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
        sp= new Settings_Page_Provider(driver);
        tb = new TimeBlockPage(driver);
        pcp = new Patient_Creation_Page(driver);
        pcc = new ProviderChatPage(driver);
    }

    @Test(priority = 1)
    public void ChangePasswordFunction() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));
        cu.click(sp.SelectSettingsProviderDash);
        cu.click(sp.ClickChangePassword);
        cu.enterText(sp.EnterCurrentPass, p.getProperty("Password1"));
        cu.enterText(sp.EnterNewPass, p.getProperty("Password1"));
        cu.enterText(sp.EnterConfirmPass, p.getProperty("Password1"));

        cu.click(sp.ClikcSaveBtn);
        Thread.sleep(1000);
        Assert.assertEquals(cu.getElementText(sp.GetChangePassToast), "Password Change Successfully...!!!");

    }

    @Test(priority = 2)
    public void TimeBlockFunction() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));
        cu.click(sp.SelectSettingsProviderDash);
        cu.click(sp.ClickTimeBlockDash);
        cu.click(sp.SelectAddButton);
        cu.click(tb.TypeDrpDwnSelection);
       cu.click(tb.TimeBlockType);
       ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");
       cu.click(tb.OpenStartDateCalendar);
       pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        Thread.sleep(2000);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        cu.click(tb.clickOnEndDateIcon);
        //Thread.sleep(4000); 
        cu.click(sp.ClickStartDateButton);
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        Thread.sleep(2000);
       // cu.click(tb.ProceedBtnClick);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

       cu.click(tb.OpenEndDateCalendar);
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        Thread.sleep(2000);
       // cu.click(tb.ProceedBtnClick);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
       // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");

        cu.click(pa.selectStartTime);
        cu.click(pa.selectSetEndTime);

        // End time selection
        cu.click(pa.selectEndTime);
        cu.click(pa.selectAddhour);
        cu.click(pa.selectSetEndTime);
       // driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", tb.scrollableXpath);
              
        Thread.sleep(2000);
         tb.clickSaveAndHandlePopup();

         
         //Assert.assertEquals(cu.getElementText(sp.UserRoleByDefaultProvider), cu.getElementText(sp.GetLoggedInProviderName));
         cu.enterText(sp.ClickSearchInput, cu.getElementText(sp.GetLoggedInProviderName));

         cu.click(pr.clickAppointmentReportSearch);

         cu.click(sp.ClickEditButton);
         Assert.assertEquals(cu.getElementText(sp.VerifyEditBtn), "Edit Provider Time Block");
         cu.click(pcp.Clos_AddPatient_Tab);

         cu.click(sp.ClickViewButton);
         Assert.assertEquals(cu.getElementText(sp.VerifyViewButton), "View Provider Time Block");
         cu.click(pcp.Clos_AddPatient_Tab);

         cu.click(sp.ClickDeleteButton);
         cu.click(sp.ClickDeleteButtonPopup);
         Thread.sleep(1000);

        // Assert.assertEquals(cu.getElementText(sp.GetChangePassToast), "Time block deleted.");
        // System.out.println(cu.getElementText(sp.GetChangePassToast));
        // Thread.sleep(1000);
         cu.logout(lp);
         Thread.sleep(2000);

    }

    @Test(priority = 3)
    public void CheckChatAvailabilty() throws InterruptedException
    {
        cu.login(lp, p.getProperty("providerChatAvail"), p.getProperty("passChatAvail"));
        cu.click(sp.SelectSettingsProviderDash); 
        cu.click(sp.ClickChatAvilability);
        Assert.assertEquals(cu.getElementText(sp.ValidateEorkingDaysText), "Working Days");
        Assert.assertEquals(cu.getElementText(sp.ValidateAwayMsgTxt), "Away message preferences");
/*
        cu.click(sp.ClickDaysOfWeek);
        sp.clickDayCheckbox("Sunday");
        sp.clickDayCheckbox("Monday");
        sp.clickDayCheckbox("Tuesday");
        sp.clickDayCheckbox("Wednesday");
        sp.clickDayCheckbox("Thursday");
        sp.clickDayCheckbox("Friday");
        sp.clickDayCheckbox("Saturday"); */
//new Actions(driver).sendKeys(Keys.ESCAPE).perform();
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); 

        Thread.sleep(2000);

        cu.click(sp.ClickStartTime);
        cu.click(pa.selectSetEndTime);

        cu.click(sp.ClickEndTime);
        cu.click(pa.selectAddhour);
        cu.click(pa.selectSetEndTime);

        cu.click(sp.ClikcSaveBtn);
        cu.logout(lp);

        cu.login(lp, p.getProperty("patientChatAvail"), p.getProperty("PatientChatAvailpass"));

        cu.click(pcc.clickPatientChatOpt);
        cu.click(pcc.clickProviderPatLogin); 
        cu.enterText(pcc.searchProviderPatLogin, p.getProperty("providerChatAvail"));
        cu.click(pcc.clickOnProviderPatLogin);

       boolean ele = cu.isElementClickable(driver, pcc.clickSendMsgBtn);
        Assert.assertTrue(ele);

    }

    @Test(priority = 4)
    public void CheckChatAvailabilty2() throws InterruptedException
    {
        cu.logout(lp);
        cu.login(lp, p.getProperty("providerChatAvail"), p.getProperty("passChatAvail"));
        cu.click(pcc.ProviderChatOptDash);
        Thread.sleep(2000);
        cu.click(sp.ClickAwayMsg);
        cu.click(sp.ClickYesOnPopup);
        Thread.sleep(1000);
        cu.click(sp.SelectSettingsProviderDash); 
        cu.click(sp.ClickChatAvilability);
        Thread.sleep(2000);
        cu.click(sp.SelectAwayMessageChkbx);
        cu.click(sp.ClikcSaveBtn);
        Thread.sleep(2000);
        cu.logout(lp);
        cu.login(lp, p.getProperty("patientChatAvail"), p.getProperty("PatientChatAvailpass"));
        cu.click(pcc.clickPatientChatOpt);
        cu.click(pcc.clickProviderPatLogin); 
        cu.enterText(pcc.searchProviderPatLogin, p.getProperty("providerChatAvail"));
        cu.click(pcc.clickOnProviderPatLogin);

        //boolean ele = cu.isElementClickable(driver, pcc.clickSendMsgBtn);
        //Assert.assertFalse(ele);

        boolean ele1 = cu.isElementClickable(driver, sp.AwayMsgChatbox);
        Assert.assertTrue(ele1);

        cu.logout(lp);
        cu.login(lp, p.getProperty("providerChatAvail"), p.getProperty("passChatAvail"));
        cu.click(pcc.ProviderChatOptDash);
        Thread.sleep(2000);
        cu.click(sp.ClickAwayMsg);
        cu.click(sp.ClickYesOnPopup);
        Thread.sleep(1000);
        cu.click(sp.SelectSettingsProviderDash); 
        cu.click(sp.ClickChatAvilability);
        Thread.sleep(2000);
        cu.click(sp.SelectAwayMessageChkbx);
        cu.click(sp.ClikcSaveBtn);
        Thread.sleep(2000);
       cu.logout(lp);

    }
    
    @Test(priority = 5)
    public void chatAvilabilityValidation()
    {
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));
        cu.click(sp.SelectSettingsProviderDash); 
        cu.click(sp.ClickChatAvilability);
        cu.click(sp.ClikcSaveBtn);

        Assert.assertEquals(cu.getElementText(sp.WeekDaysValidation), "Please select weekdays");
        Assert.assertEquals(cu.getElementText(sp.StartTimeValidation), "Please enter slot start time.");
        Assert.assertEquals(cu.getElementText(sp.EndTimeValidation), "Please enter slot end time.");


    }

    @Test(priority = 6)
    public void checkResourceCenter() throws InterruptedException
    {
        cu.logout(lp);
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));
        cu.click(sp.SelectSettingsProviderDash); 
        cu.click(sp.ClickResourceCenter);
        cu.click(sp.SelectAddButton);
        cu.enterText(sp.ClickOnName, "Automation Testing");
        cu.click(sp.ClickLinkRadioBtn);
        cu.enterText(sp.InputURL, "https://www.youtube.com/watch?v=lwE04t__Rcc");
        cu.enterText(sp.EnterNotesText, "Automation Testing is very important");
        cu.click(sp.ClikcSaveBtn);

        cu.enterText(sp.SearcBox, "Automation Testing");

        cu.click(sp.ClickEditResourceBtn);
        cu.click(pcp.Clos_AddPatient_Tab);

        cu.click(sp.ClickDeleteResourceBtn);
        cu.click(sp.ClickDeleteButtonPopup);
        cu.click(sp.ClickResetBtn); 


        Thread.sleep(2000);


        List<WebElement> col1Asc = cu.getColumnElements(1);
        boolean col1AscStatus = TableSortUtils.isColumnSortedUniversal(col1Asc, "asc");
        Assert.assertTrue(col1AscStatus);

        // Descending
        cu.clickcolumnheader(1);     
        Thread.sleep(2000);
        List<WebElement> col1Desc = cu.getColumnElements(1);
        boolean col1DescStatus = TableSortUtils.isColumnSortedUniversal(col1Desc, "desc");
        Assert.assertTrue(col1DescStatus);


        // ========== COLUMN 2 ==========

        // Ascending
        Thread.sleep(2000);
        cu.clickcolumnheader(2);
        Thread.sleep(3000);
        List<WebElement> col2Asc = cu.getColumnElements(2);
        System.out.println(col2Asc);
        boolean col2AscStatus = TableSortUtils.isColumnSortedUniversal(col2Asc, "asc");
        Assert.assertTrue(col2AscStatus);

        // Descending
        Thread.sleep(1000);
        cu.clickcolumnheader(2);
        Thread.sleep(2000);
        List<WebElement> col2Desc = cu.getColumnElements(2);
        boolean col2DescStatus = TableSortUtils.isColumnSortedUniversal(col2Desc, "desc");
        Assert.assertTrue(col2DescStatus);


        // ========== COLUMN 3 ==========

        // Ascending
        Thread.sleep(2000);
        cu.clickcolumnheader(3);
        Thread.sleep(2000);
        List<WebElement> col3Asc = cu.getColumnElements(3);
        boolean col3AscStatus = TableSortUtils.isColumnSortedUniversal(col3Asc, "asc");
        Assert.assertTrue(col3AscStatus);

        // Descending
        Thread.sleep(3000);
        cu.clickcolumnheader(3);
        Thread.sleep(2000);
        List<WebElement> col3Desc = cu.getColumnElements(3);
        boolean col3DescStatus = TableSortUtils.isColumnSortedUniversal(col3Desc, "desc");
        Assert.assertTrue(col3DescStatus);




    }


}
