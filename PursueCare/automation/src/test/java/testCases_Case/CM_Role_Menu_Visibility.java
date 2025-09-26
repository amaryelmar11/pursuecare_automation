package testCases_Case;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.CM_Role;
import PageObjectClass.LoginPage;

public class CM_Role_Menu_Visibility extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private CM_Role cm;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        cm = new CM_Role(driver);
    }

    @Test(priority = 1)
    public void verifyMenusPresent() throws InterruptedException
    {
        cu.login(lp, p.getProperty("case"), p.getProperty("case_pass"));

      //  Assert.assertEquals(cm.dashboardtext.getText().trim(), "Dashboard");
      Thread.sleep(8000);
      //  Assert.assertTrue(cm.dashboard_menu.isDisplayed(), "Dashboard menu missing");
        Thread.sleep(5000);
        Assert.assertTrue(cm.provider_menu.isDisplayed(), "Provider menu missing");
        Thread.sleep(3000);
        cu.click(cm.provider_menu);
        Thread.sleep(5000);
        Assert.assertTrue(cm.provider_list_menu.isDisplayed(), "Provider list menu missing");
        Assert.assertTrue(cm.provider_availability_menu.isDisplayed(), "Provider availability menu missing");
        Assert.assertTrue(cm.provider_sessionlist_menu.isDisplayed(), "Provider session listmenu missing");
        Thread.sleep(3000);

        Assert.assertTrue(cm.careteam_menu.isDisplayed(), "Care Team menu missing");
       

        cu.click(cm.careteam_menu);
        Thread.sleep(5000);
        Assert.assertTrue(cm.appointment_menu.isDisplayed(), "Appointments menu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.patient_menu.isDisplayed(), "Patients menu missing");
        Thread.sleep(2000);
        cu.click(cm.patient_menu);

        Assert.assertTrue(cm.patient_list.isDisplayed(), "Patients List submenu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.chat_menu.isDisplayed(), "Chat menu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.groupsession_menu.isDisplayed(), "Group Sessions menu missing");
        cu.click(cm.groupsession_menu);
        Thread.sleep(2000);
        Assert.assertTrue(cm.session_list_menu.isDisplayed(), "Session List submenu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.settings_menu.isDisplayed(), "Settings menu missing");
        cu.click(cm.settings_menu);
        Thread.sleep(2000);
        Assert.assertTrue(cm.userlist_menu.isDisplayed(), "Users List submenu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.changepass_menu.isDisplayed(), "Change Password submenu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.assessment_menu.isDisplayed(), "Assessment menu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.timeblock_menu.isDisplayed(), "Time Block menu missing");
        Thread.sleep(2000);
        Assert.assertTrue(cm.resource_center_menu.isDisplayed(), "Resource Center menu missing");
        Assert.assertTrue(cm.reports_menu.isDisplayed(), "Reports menu missing");
        Assert.assertTrue(cm.logout_menu.isDisplayed(), "Logout menu missing");
    }
}
