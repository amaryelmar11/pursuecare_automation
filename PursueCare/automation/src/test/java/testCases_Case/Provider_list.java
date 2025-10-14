package testCases_Case;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.CM_Role;
import org.testng.annotations.BeforeClass;
import BasePage.Common_Utils;



   

public class Provider_list extends Baseclass{

    private LoginPage lp;
    private DashBoardPage dp;
    private Common_Utils cu;
    private CM_Role pc;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        dp = new DashBoardPage(driver);
        cu = new Common_Utils(driver);
        pc = new CM_Role (driver);
  

}


    @Test(priority = 1)
    public void Provider_list_view_and_filter() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("case"));
        lp.enterPassword(p.getProperty("case_pass"));
        lp.clickLoginbtn();

      
        Thread.sleep(3000);
        cu.click(pc.providermenu);
        Thread.sleep(3000);
         cu.click(pc.provider_list_menu);
         Thread.sleep(5000);
         cu.click(pc.providersearch);
         cu.enterText(pc.providersearch, "Grandy");
         Thread.sleep(3000);
         cu.click(pc.Clear_filter);
         Thread.sleep(3000);
         cu.click(pc.licensedstate);
         cu.click(pc.Clear_filter);
         Thread.sleep(3000);
         pc.selectRandomState();
         Thread.sleep(3000);
         
         cu.click(pc.Insurances);
         Thread.sleep(3000);
         cu.click(pc.Weekly_Schedule);
         Thread.sleep(3000);
         pc.selectRandomState();
         Thread.sleep(3000);
         cu.click(pc.Speciality);
         Thread.sleep(3000);
         pc.selectRandomState();
         Thread.sleep(3000);
         cu.click(pc.Clear_filter);
         Thread.sleep(3000);
         cu.click(pc.dashboard_menu);
         Thread.sleep(8000);

    }
}