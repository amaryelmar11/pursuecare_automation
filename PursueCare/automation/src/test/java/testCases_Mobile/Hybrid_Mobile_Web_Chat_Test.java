package testCases_Mobile;

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
import PageObjectClass.Mobile.MobileChatPage;

/**
 * Hybrid Test class for Chat functionality that interacts with both Mobile and Web.
 * This class demonstrates how to use both mobile and web drivers for chat testing.
 * 
 * Prerequisites:
 * - Appium server should be running (default: http://127.0.0.1:4723)
 * - Android emulator/device or iOS simulator/device should be connected
 * - App should be installed on the device
 * - Web browser should be available
 * 
 * @author Automation Team
 */
public class Hybrid_Mobile_Web_Chat_Test extends HybridBaseClass {

    // Page Object instances
    private LoginPage lp;
    private Login_Page_Mobile mlp;
    private Common_Utils cu;
    private Common_Utils_Mobile cm;
    private ProviderAppointmentsPage pa;
    private DashBoardPage dp;
    private ProviderZoomSessionPage pz;
    private MobileChatPage mcp;
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
        mcp = new MobileChatPage(mobileDriver);
        lp = new LoginPage(driver);
        // Initialize Web page objects using webDriver
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
        dp = new DashBoardPage(driver);
        pz = new ProviderZoomSessionPage(driver);
        pr = new Appointment_Report_Page(driver);
        pd = new Provider_Dashboard_Page(driver);
        
    }

    /**
     * Hybrid test: Mobile login and chat interaction.
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
    @Test(priority = 1)
    public void mobileLoginAndChatTest() throws InterruptedException {
        // Step 1: Perform mobile login
        logger.info("Starting mobile login...");
        cm.login(mlp, p1.getProperty("mobile.patient.chat"), p1.getProperty("mobile.patient.chatpassword"));
        Thread.sleep(1000);
        
        // Handle mobile popups
        cm.click(mlp.SystemButton2);
        cm.click(mlp.ClickGotItButton);
        cm.click(mlp.GotItText);
   
        // Step 2: Navigate to Chat
       
        //cm.click(mcp.ChatclickDashboard);
        cm.TouchAction(mcp.ChatclickDashboard, mobileDriver);
       
        // Step 3: Click on Chat Provider
        cm.click(mcp.ClickChatProvider);
        Thread.sleep(2000);
        
        logger.info("Mobile chat navigation completed");
    }

    /**
     * Hybrid test: Send chat message from mobile.
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
    @Test(priority = 2)
    public void mobileSendChatMessageTest() throws InterruptedException {
        // Step 1: Enter message in chat
        logger.info("Sending chat message...");
        cm.enterText(mcp.EntertheMessage, "Hello, this is a test message from automation");
        Thread.sleep(1000);
        cm.enterText(mcp.EntertheMessage, "I have a doubt");
        Thread.sleep(1000);
        cm.enterText(mcp.EntertheMessage, "Are You Free for Chat Today?");
        Thread.sleep(1000);
        
        // Step 2: Send message
        cm.click(mcp.SendChatMsg1);
        Thread.sleep(2000);
        
        logger.info("Chat message sent successfully");
    }

    /**
     * Hybrid test: Request appointment from chat.
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
   // @Test(priority = 3)
    public void mobileRequestAppointmentFromChatTest() throws InterruptedException {
        // Step 1: Click on Request Appointment in chat section
        logger.info("Requesting appointment from chat...");
        cm.click(mcp.ClickRequestApptChatSection);
        Thread.sleep(2000);
        
        logger.info("Appointment request initiated from chat");
    }

   
}
