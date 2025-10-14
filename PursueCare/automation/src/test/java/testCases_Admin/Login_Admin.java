package testCases_Admin;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;

public class Login_Admin extends Baseclass{

    @Test(priority = 1)
    public void Admin_login() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("admin"));
        lp.enterPassword(p.getProperty("adminpass"));
        lp.clickLoginbtn();
        
        DashBoardPage dp = new DashBoardPage(driver);

        String dashboardtxt = dp.dashBoardtext();

        Assert.assertEquals("Dashboard", dashboardtxt);

    }
}
