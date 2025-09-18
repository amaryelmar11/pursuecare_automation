package testCases_Provider;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;

public class Login_Provider extends Baseclass{

    @Test(priority = 1)
    public void correctdetails() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("provider1"));
        lp.enterPassword(p.getProperty("Password1"));
        lp.clickLoginbtn();
        
        DashBoardPage dp = new DashBoardPage(driver);

        String dashboardtxt = dp.dashBoardtext();

        Assert.assertEquals("Dashboard", dashboardtxt);

    }
}
