package testCases_PAS;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;

public class Login_PAS extends Baseclass{

 
    @Test(testName="PAS User Login")
    public void Patient_access_specialist_login() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("pas"));
        lp.enterPassword(p.getProperty("pass"));
        lp.clickLoginbtn();
        
        DashBoardPage dp = new DashBoardPage(driver);

        String dashboardtxt = dp.dashBoardtext();

        Assert.assertEquals("Dashboard", dashboardtxt);

    }
}
