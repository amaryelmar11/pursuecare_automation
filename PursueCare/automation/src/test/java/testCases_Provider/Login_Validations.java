package testCases_Provider;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.LoginPage;

public class Login_Validations extends Baseclass{
    

    @Test(priority = 3)
    public void incorrectMailId()
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId("jad@pursuecare.com");
        lp.enterPassword(p.getProperty("Password1"));
        lp.clickLoginbtn();

        String val1 = lp.Login_Validations();

        Assert.assertEquals("Invalid Username And Password", val1);
    }

    @Test(priority=2)
    public void incorrectPassword() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("provider1"));
        lp.enterPassword("123456");
        lp.clickLoginbtn();
        Thread.sleep(2000);
        String val1 = lp.Login_Validations();

        Assert.assertEquals("Invalid Username And Password", val1);
    }

    @Test(priority=1)
    public void emptyUsernamePassword() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId("");
        lp.enterPassword("");
        lp.clickLoginbtn();
        Thread.sleep(2000);
        String val1 = lp.Login_Val_emptyUserPass();

        Assert.assertEquals("Email address and Password not valid !", val1);
    }

}
