package testCases_Mobile;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.HybridBaseClass;
import BasePage.Common_Utils;
import BasePage.Common_Utils_Mobile;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.ProviderZoomSessionPage;
import PageObjectClass.Provider_Dashboard_Page;
import PageObjectClass.Mobile.Login_Page_Mobile;
import PageObjectClass.Mobile.Mobile_Zoom_Page;

/**
 * Hybrid Test class that interacts with both Mobile and Web.
 * This class demonstrates how to use both mobile and web drivers in the same test.
 * 
 * Prerequisites:
 * - Appium server should be running (default: http://127.0.0.1:4723)
 * - Android emulator/device or iOS simulator/device should be connected
 * - App should be installed on the device
 * - Web browser should be available
 * 
 * @author Automation Team
 */
public class Hybrid_Mobile_Web_Test extends HybridBaseClass {

    // Page Object instances
    private LoginPage lp;
    private Login_Page_Mobile mlp;
    private Common_Utils cu;
    private Common_Utils_Mobile cm;
    private ProviderAppointmentsPage pa;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;
    private Mobile_Zoom_Page mz;
    private Appointment_Report_Page pr;
    private Provider_Dashboard_Page pd;
    private String mainwindowHandle;
    

    /**
     * Initializes all page objects before test execution.
     */
    @BeforeClass
    public void initPages() {
        // Initialize Mobile page objects using mobileDriver
        mlp = new Login_Page_Mobile(mobileDriver);
        cm = new Common_Utils_Mobile(mobileDriver);
        lp = new LoginPage(driver);
        // Initialize Web page objects using webDriver
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
        dp = new DashBoardPage(driver);
        pz = new ProviderZoomSessionPage(driver);
        mz = new Mobile_Zoom_Page(mobileDriver);
        pr = new Appointment_Report_Page(driver);
        pd = new Provider_Dashboard_Page(driver);
        
    }

    /**
     * Example hybrid test: Login on mobile, then verify something on web.
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */

    @Test(priority = 1)
    public void WebLogin_Appointment_Booking_Test() throws InterruptedException 
    {

       // cu.login(lp, p.getProperty("ProviderZoom"), p.getProperty("PassZoom"));
       cu.login(lp, p.getProperty("zoomsessionchecks"), p.getProperty("passwordzoomsessionchecks"));

       // Open Appointments and open today's slot
        cu.click(pa.selectAppointmentDash);
       cu.click(pa.getTodayAppXpath());
       Thread.sleep(1500);

       // Select appointment details
       cu.click(pa.selectAppointment);
       cu.click(pa.selectAppointmentType);
       ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

       cu.click(pa.selectHost);
       new Actions(driver).sendKeys(Keys.ENTER).perform();

       cu.click(pa.selectPatient);
       cu.enterText(pa.inputPatientName, p.getProperty("patientZoomEndToEnd"));
       cu.click(pa.setVisiblePatient);
       ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

       // Start/End time
       cu.click(pa.selectStartTime);
       cu.click(pa.selectSetStartTime);
       cu.click(pa.selectEndTime);
       cu.click(pa.selectAddhour);
       cu.click(pa.selectSetEndTime);
       String time = cu.getElementText(pa.selectStartTime);
       System.out.println(time);
       // Save meeting
       cu.click(pa.selectSaveButton);
       Thread.sleep(6000);
       cu.click(dp.ClickOnDashboardHome);
       Thread.sleep(3000);
       String name = cu.getElementText(dp.getProviderNameDash);
       cu.getElementText(dp.getProviderNameDash);
       mainwindowHandle = driver.getWindowHandle();
       System.out.println(mainwindowHandle);
       cu.clickJoinMeetingprovider(time);
       Thread.sleep(6000);
       
       Set<String> allwindowHandles = driver.getWindowHandles();	
       System.out.println(allwindowHandles);
       for (String handle : allwindowHandles) {
           if (!handle.equals(mainwindowHandle)) {
               driver.switchTo().window(handle);
               break;
           }
       }

       String providerZoomWindow = driver.getWindowHandle();

    
      cu.click(pz.clickOnJoinSession);
      Thread.sleep(3000);
      cu.click(pz.clickonNotNowPatient);
    }
      

    @Test(priority = 2)
      public void mobileLoginAndJoinZoomSession() throws InterruptedException {
      //Mobile Login and Join Zoom Session
   
        // Step 1: Perform mobile login
        logger.info("Starting mobile login...");
        cm.login(mlp, p1.getProperty("mobile.patient.email1"), p1.getProperty("mobile.patient.password1"));
        Thread.sleep(1000);
        
        // Handle mobile popups
        cm.click(mlp.SystemButton2);
        cm.click(mlp.ClickGotItButton);
        cm.click(mlp.GotItText);

        mz.clickZoomSessionPathView();
        Thread.sleep(5000);
    }

      @Test(priority = 3)
        public void webLoginAndEndZoomSession() throws InterruptedException {
       // End Zoom Session
        cu.click(pz.clickonLeaveOption);
        cu.click(pz.clickOnEndSession);

        cu.selectFromDropdown(pz.StatusSelection, "text", "Completed Successfully");
        Thread.sleep(2000);
        cu.click(pz.SubmitResponse);
        Thread.sleep(8000);

        driver.switchTo().window(mainwindowHandle);

        cu.logout(lp);

        cu.login(lp, p.getProperty("provider2"), p.getProperty("password2"));

        cu.click(pd.ClickReportsDash);   
        cu.click(pd.ClickAppointmentReportDash);  

        cu.click(pr.clickSelectPatientFilter);
        cu.enterText(pr.SearchPatientInput, p.getProperty("patientZoomEndToEnd"));
        cu.click(pr.clickCheckbxPatientSelect);
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        cu.click(pr.clickAppointmentReportSearch);
        Thread.sleep(2000);
        Assert.assertEquals("Completed Successfully", cu.getElementText(pr.AppointmentCompletionTxtZoomCheck));

        cu.logout(lp);
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));

        cu.click(pa.clickCareTeamDash);
        cu.click(pa.clickAppointments);

        cu.click(pa.SelectProviderDropdown);
        cu.click(pa.SelectProviderSessionChecksFromDrpdwn);
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        
        Thread.sleep(1000);
        cu.click(pa.SelectAppointmentForDeletion);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        cu.click(pa.DeleteProviderApp);
        cu.click(pa.ClickOnDeleteBtnPopup);
        Thread.sleep(5000);
        
    }

   
    //@Test(priority = 3)
    public void hybridLogoutTest() throws InterruptedException {
        // Step 1: Mobile logout
        logger.info("Performing mobile logout...");
        cm.logout(mlp);
        logger.info("Mobile logout successful");
        
        // Step 2: Web operations if needed
        logger.info("Web operations completed");
        
        Thread.sleep(1000);
    }
}

