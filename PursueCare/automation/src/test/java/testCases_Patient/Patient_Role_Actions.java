package testCases_Patient;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.Patient_Role;

public class Patient_Role_Actions extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private Patient_Role pr;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pr = new Patient_Role(driver);
    }

    @Test(priority = 1)
    public void patientRoleMenuNavigation() throws InterruptedException
    {
        cu.login(lp, p.getProperty("Patientaction"), p.getProperty("Patientactionpass"));

        // Verify dashboard visible with a visibility wait via getElementText
        Assert.assertEquals(cu.getElementText(pr.dashboardtext), "Dashboard", "Dashboard header not visible or text mismatch");

        // Optionally open dashboard menu (if collapsible)
        try {
            if (pr.dashboardmenu.isDisplayed()) {
                cu.click(pr.dashboardmenu);
            }
        } catch (Exception ignored) {}

        // Verify key sections present on patient dashboard
        Assert.assertTrue(pr.upcomingappointmentsection.isDisplayed(), "Upcoming Appointments section missing");
        Assert.assertTrue(pr.upcoming_appointment_zoomclick.isDisplayed(), "Upcoming Appointments zoom button is not visible");
        Thread.sleep(3000);
        cu.click(pr.upcoming_appointment_zoomclick);
        Thread.sleep(2000);
        Assert.assertTrue(pr.groupsessionsection.isDisplayed(), "Group Sessions section missing");
        Assert.assertTrue(pr.pendingassessmentsection.isDisplayed(), "Pending Assessment section missing");
        Assert.assertTrue(pr.patient_text.isDisplayed(), "Patient text is missing");
        
        //profile pic click to see notification setting
        cu.click(pr.profile_pic_click);

         Thread.sleep(3000);

       cu.click(pr.notification_setting_click);
         Thread.sleep(3000);
         cu.click(pr.enable_email);
         Thread.sleep(2000);
         cu.click(pr.enable_sms);
         Thread.sleep(2000);
         cu.click(pr.enable_push);
         cu.click(pr.click_save);

      
      //dashboard chat menu
       
      cu.click(pr.full_Screen);
      Thread.sleep(3000);
      //cu.click(pr.full_screen_zoom_out);

      cu.click(pr.dashboard_chat);
      Thread.sleep(3000);
      //cu.click(pr.timezone_dropdown);
      Thread.sleep(3000);
        // Navigate: Pharmacy
        cu.click(pr.pharmacy);
        Thread.sleep(2000);

        // Navigate: Chat
        cu.click(pr.chat);
        Thread.sleep(2000);
        cu.click(pr.click_on_chat);
        Thread.sleep(3000);
        cu.enterText(pr.type_chat_message, "Hello from automation CM");
        cu.click(pr.send_message);
        Thread.sleep(2000);
        
        
        // Switch to Provider tab in chat
       cu.click(pr.Chat_provider);
         Thread.sleep(2000);

       

               // Type and send a chat message
        cu.click(pr.click_on_providerchat);
       // cu.click(pr.clicktypebox);
        cu.enterText(pr.type_chat_message, "Hello from automation Provider");
         cu.click(pr.send_message);
        // cu.waitForOverlayToDisappear();
        Thread.sleep(3000);


        cu.click(pr.dashboardmenu);
Thread.sleep(3000);
 cu.click(pr.click_on_assessment);
 Thread.sleep(3000);
 Assert.assertTrue(pr.startassessment.isDisplayed(), "Start button missing");

 Thread.sleep(3000);
 cu.click(pr.startassessment);
 Thread.sleep(2000);



  cu.click(pr.close_assessment_pop_up);

  cu.click(pr.setting_menu);
  Thread.sleep(3000);
  cu.click(pr.changepassmenu);
   Thread.sleep(2000);
   // Type current and new passwords
   cu.click(pr.currentpassword);
   cu.enterText(pr.currentpassword, p.getProperty("Patientactionpass"));
   cu.click(pr.newpassword);
   cu.enterText(pr.newpassword, p.getProperty("patientNewPass", "Amar@123"));
   cu.click(pr.save);
  
   cu.login(lp, p.getProperty("Patientaction"), p.getProperty("Patientactionpass"));

 Thread.sleep(3000);
 cu.click(pr.logout);





 Thread.sleep(5000);

       

      

    }


}


