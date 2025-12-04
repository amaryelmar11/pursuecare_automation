package testCases_Provider;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;

public class Remember_Me extends Baseclass{

    @Test(priority = 1)
    public void rememberMee() throws InterruptedException
    {
        LoginPage lp = new LoginPage(driver);
        Common_Utils cu = new Common_Utils(driver);

        lp.enterEmailId(p.getProperty("provider1"));
        lp.enterPassword(p.getProperty("Password1"));
        cu.click(lp.clickRemeberMeChkbx);

        lp.clickLoginbtn();
        
        Thread.sleep(3000);

    }

    @Test(priority = 2)
    public void rememberMeCheckss() throws InterruptedException
    {
        Common_Utils cu = new Common_Utils(driver);
        LoginPage lp = new LoginPage(driver);
        String window1 = driver.getWindowHandle();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        for (String window : windows) {
            if (!window.equals(window1)) {
                driver.switchTo().window(window);
                break;
            }
        }
        
        driver.get(p.getProperty("appURL_QA"));

        Thread.sleep(5000);
        System.out.println(lp.emailId.getAttribute("value"));
        System.out.println(lp.password.getAttribute("value"));
        Assert.assertEquals(p.getProperty("provider1"), lp.emailId.getAttribute("value"));
        Assert.assertEquals(p.getProperty("Password1"), lp.password.getAttribute("value"));
    }
}
