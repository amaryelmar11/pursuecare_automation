package testCases_Provider;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;

public class Forgot_Password_Provider extends Baseclass{

    @Test
    public void forgotPassword()
    {
        Common_Utils cu = new Common_Utils(driver);
        LoginPage lp = new LoginPage(driver);

        cu.click(lp.Forgot_Password);

        cu.enterText(lp.email_FgtPwd, p.getProperty("provider1"));

        cu.click(lp.ContinueBtn);

        String val = cu.getElementText(lp.FgtPwdMailSent);

        Assert.assertEquals("Please check your inbox / spam, we have sent you reset password link.", val);
    }
}
