package testCases_Provider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;

public class Remember_Me extends Baseclass{

    @Test(priority = 1)
    public void rememberMe() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        Common_Utils cu = new Common_Utils(driver);

        lp.enterEmailId(p.getProperty("provider1"));
        lp.enterPassword(p.getProperty("password1"));
        cu.click(lp.clickRemeberMeChkbx);

        lp.clickLoginbtn();

        Thread.sleep(3000);

    }

    //@Test(priority = 2)
    public void rememberMeChecks()
    {
        Common_Utils cu = new Common_Utils(driver);
        LoginPage lp = new LoginPage(driver);

        Assert.assertEquals(p.getProperty("provider1"), cu.getElementText(lp.emailId));

        Assert.assertEquals(p.getProperty("password1"), cu.getElementText(lp.password));

    }
}
