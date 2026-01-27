package testCases_Mobile;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.MobileBaseClass;
import BasePage.Common_Utils;
import BasePage.Common_Utils_Mobile;
import PageObjectClass.Mobile.AppointmentflowPage;
import PageObjectClass.Mobile.Dashboard_Page_Mobile;
import PageObjectClass.Mobile.Login_Page_Mobile;

public class AppointmentReqFlow extends MobileBaseClass {

   // Page Object instances
   private Dashboard_Page_Mobile dp;
   private Login_Page_Mobile lp;
   private Common_Utils cu;
   private Common_Utils_Mobile cm;
   private AppointmentflowPage ap;

   /**
    * Initializes all page objects before test execution.
    */
   @BeforeClass
   public void initPages() {
       dp = new Dashboard_Page_Mobile(driver);
       lp = new Login_Page_Mobile(driver);
       cu = new Common_Utils(driver);
       cm = new Common_Utils_Mobile(driver);
       ap = new AppointmentflowPage(driver);
   }

   @Test(priority = 1)
   public void appointmentReqFlow() throws InterruptedException {
    cm.login(lp, p.getProperty("mobile.patient.usernameReqFlow"), p.getProperty("mobile.patient.passwordReqFlow"));
    cm.click(lp.SystemButton2);
    cm.click(lp.ClickGotItButton);
    cm.click(lp.GotItText);
    cm.scrollDown();
    cm.click(ap.RequestNewAppt);
    cm.click(ap.RequestApptButton);
    cm.click(ap.ClickOnSlotDate);
    cm.click(ap.ClickonSlot);
    cm.click(ap.ClickonRequestAppt);
    cm.click(ap.validateRequestSuccessText);
    cm.click(ap.ClickOnGotItPopup);
   }


}
