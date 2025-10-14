package testCases_Patient;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;

public class Login_Patient extends Baseclass{

    @Test(priority = 1)
    public void Patient_login() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("patientID"));
        lp.enterPassword(p.getProperty("patientpass"));
        lp.clickLoginbtn();
        
        DashBoardPage dp = new DashBoardPage(driver);

        String dashboardtxt = dp.dashBoardtext();

        Assert.assertEquals("Dashboard", dashboardtxt);

    }
}
