package testCases_Provider;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderChatPage;

public class Provider_Chat extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderChatPage pc;
    private WebDriverWait wait;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pc = new ProviderChatPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

}

   @Test(priority = 1)
    public void providerChatWithPatient() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));

        cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        cu.enterText(pc.searchPatientInput, p.getProperty("patient1"));
        cu.click(pc.clickonSearchedPatient);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@formcontrolname='chatmessage']")));
        pc.sendMessage("Hello");
        Thread.sleep(1000);
        pc.sendMessage("How are you?");
       Thread.sleep(1000);
        pc.sendMessage("How you feeling today?");
    }

    @Test(priority = 2)
    public void ProviderMessageReceivedChecks() throws InterruptedException
    {
        
        cu.logout(lp);

        cu.login(lp, p.getProperty("patient1"), p.getProperty("password1"));

        cu.click(pc.clickPatientChatOpt);
        cu.click(pc.clickProviderPatLogin);   
        Assert.assertEquals(cu.getElementText(pc.getPatientMsgBadgeCount), "3");
        //cu.click(pc.clickSendMsgBtn);
        cu.enterText(pc.searchProviderPatLogin, p.getProperty("provider1"));
        cu.click(pc.clickOnProviderPatLogin);
        Thread.sleep(1000);
        pc.sendMessage("Hello");
        Thread.sleep(1000);
        pc.sendMessage("I have one doubt");
        Thread.sleep(1000);
        pc.sendMessage("Are You Free for Chat Today?");

        cu.logout(lp);

        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));

        cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        String msgBadgeCount = cu.getElementText(pc.getPatientMsgBadgeCount);
        Assert.assertEquals(msgBadgeCount, "3");
        cu.enterText(pc.searchPatientInput, p.getProperty("patient1"));

        // To Remove the patient msg badge count for the next test case run
        cu.click(pc.clickonSearchedPatient);
        cu.click(pc.clickPatienttab);
        driver.navigate().refresh();
        
        
    }
 
    @Test(priority = 3)
    public void RecentlyChatPatientOnTop() throws InterruptedException
    {
        //cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));
        //cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        String msgProviderOrPatientInMiddle = cu.getElementText(pc.getmsgProviderOrPatientAtMiddle);
        System.out.println(msgProviderOrPatientInMiddle);
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
