package testCases_Mobile;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.MobileBaseClass;
import BasePage.Common_Utils;
import BasePage.Common_Utils_Mobile;
import PageObjectClass.Mobile.Dashboard_Page_Mobile;
import PageObjectClass.Mobile.Login_Page_Mobile;

/**
 * Test class for Mobile Dashboard functionality.
 * This class contains test cases for:
 * - Dashboard page elements verification
 * - Dashboard navigation
 * - Dashboard interactions
 * 
 * Prerequisites:
 * - Appium server should be running (default: http://127.0.0.1:4723)
 * - Android emulator/device or iOS simulator/device should be connected
 * - App should be installed on the device
 * - User should be logged in to access dashboard
 * 
 * TestNG XML Configuration Example:
 * <parameter name="platform" value="android"/>
 * <parameter name="deviceName" value="emulator-5554"/>
 * <parameter name="appPath" value="path/to/app.apk"/>
 * 
 * @author Automation Team
 */
public class Dashboard_Page_Mobile_Test extends MobileBaseClass {

    // Page Object instances
    private Dashboard_Page_Mobile dp;
    private Login_Page_Mobile lp;
    private Common_Utils cu;
    private Common_Utils_Mobile cm;

    /**
     * Initializes all page objects before test execution.
     */
    @BeforeClass
    public void initPages() {
        dp = new Dashboard_Page_Mobile(driver);
        lp = new Login_Page_Mobile(driver);
        cu = new Common_Utils(driver);
        cm = new Common_Utils_Mobile(driver);
    }

    /**
     * Test case to verify dashboard page elements are visible.
     * Steps:
     * 1. Navigate to dashboard (after login)
     * 2. Verify dashboard elements are displayed
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
    @Test(priority = 1)
    public void verifyDashboardPage() throws InterruptedException {
        cm.login(lp, p.getProperty("mobile.patient.email"), p.getProperty("mobile.patient.password"));
        Thread.sleep(1000);
        cm.click(lp.SystemButton2);
        cm.click(lp.ClickGotItButton);
        cm.click(lp.GotItText);
        Assert.assertEquals(cm.getElementText(lp.WelcomeBackText), "Welcome back,");
        Thread.sleep(2000);
        // Verify dashboard elements
        Assert.assertEquals(cm.getElementText(dp.UpcomingAppointments), "Upcoming Appointments");
        Assert.assertEquals(cm.getElementText(dp.OpenTasks), "Open Tasks");
        Assert.assertEquals(cm.getElementText(dp.ContactUs), "Contact Us");
        Assert.assertEquals(cm.getElementText(dp.BookNewAppointment), "Book New Appointment");
    }

    /**
     * Test case to verify dashboard visibility.
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
    @Test(priority = 2)
    public void verifyDashboardVisibility() throws InterruptedException {
        Assert.assertTrue(dp.isDashboardVisible(), "Dashboard should be visible");
    }
}

