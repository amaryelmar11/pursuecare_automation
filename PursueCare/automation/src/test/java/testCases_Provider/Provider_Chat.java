package testCases_Provider;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.Assert;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderChatPage;

public class Provider_Chat extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderChatPage pc;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pc = new ProviderChatPage(driver);

}

   @Test(priority = 1)
    public void providerChatWithPatient()
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));

        cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        cu.enterText(pc.searchPatientInput, p.getProperty("patient1"));
        cu.click(pc.clickonSearchedPatient);
        pc.sendMessage("Hello");
       // pc.sendMessage("How are you?");
       // pc.sendMessage("How you feeling today?");
    }

    @Test(priority = 2)
    public void ProviderMessageReceivedChecks()
    {
        
        cu.logout(lp);

        cu.login(lp, p.getProperty("patient1"), p.getProperty("password1"));

        cu.click(pc.clickPatientChatOpt);
        cu.click(pc.clickProviderPatLogin);
        cu.enterText(pc.searchProviderPatLogin, p.getProperty("SearchProvider"));
        cu.click(pc.clickOnProviderPatLogin);
        //cu.click(pc.clickSendMsgBtn);
        pc.sendMessage("Hello");
        pc.sendMessage("I have one doubt");
        pc.sendMessage("Are You Free for Chat Today?");

        cu.logout(lp);

        cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));

        cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        String msgBadgeCount = cu.getElementText(pc.getPatientMsgBadgeCount);
        Assert.assertEquals(msgBadgeCount, "2");
        
    }
 
    @Test(priority = 3)
    public void RecentlyChatPatientOnTop() throws InterruptedException
    {
        cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));
        cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        String msgProviderOrPatientInMiddle = cu.getElementText(pc.getmsgProviderOrPatientAtMiddle);
        cu.click(pc.msgProviderOrPatientInMiddle);
        cu.click(pc.clickSendMsgBtn);
        pc.sendMessage("Hello");
        //pc.sendMessage("I have one doubt");

        Thread.sleep(3000);
       // cu.click(pc.msgProviderOrPatientAtTop);
        String msgProviderOrPatientAtTop  = cu.getElementText(pc.getmsgProviderOrPatientInTop);
        Assert.assertEquals(msgProviderOrPatientInMiddle, msgProviderOrPatientAtTop);
    }
}
