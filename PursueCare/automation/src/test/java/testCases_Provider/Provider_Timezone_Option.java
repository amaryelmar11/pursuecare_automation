package testCases_Provider;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderChatPage;
import PageObjectClass.Provider_Dashboard_Page;

public class Provider_Timezone_Option extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private DashBoardPage dp;
    private Provider_Dashboard_Page pd;
    private ProviderChatPage pc;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        dp = new DashBoardPage(driver);
        pd = new Provider_Dashboard_Page(driver);
        pc = new ProviderChatPage(driver);
    }

    @Test
    public void ProviderTimeZoneChecks() throws InterruptedException
    {
        cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));
        String ESTtime = cu.getElementText(pd.ProviderAppointmentTime);
        cu.click(pd.TimeZoneDrpDwn);
        cu.click(pd.SelectMSTimeZone);
        Thread.sleep(2000);
        String MSTtime = cu.getElementText(pd.ProviderAppointmentTime);
        
        boolean status = pd.compareTimeRanges(ESTtime, MSTtime);
System.out.println(ESTtime +""+ MSTtime);
        Assert.assertTrue(status);
        cu.click(pd.TimeZoneDrpDwn);

        cu.click(pd.SelectESTimeZone);

        Thread.sleep(2000);

    }
}
