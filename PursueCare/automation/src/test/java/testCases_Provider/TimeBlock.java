package testCases_Provider;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.TimeBlockPage;

public class TimeBlock extends Baseclass {

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderAppointmentsPage pa;
    private TimeBlockPage tb;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new ProviderAppointmentsPage(driver);
        tb = new TimeBlockPage(driver);
    }

    @Test(priority = 1)
    public void createTimeBlockForProvider() throws InterruptedException {
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));
        
        cu.click(pa.selectAppointmentDash);
        WebElement todayCell = pa.getTodayAppXpath();
        cu.click(todayCell);
        Thread.sleep(3000);
       cu.click(tb.CategorySelection);
       cu.click(tb.TimeBlockSelection);
       cu.click(tb.TypeDrpDwnSelection);
       cu.click(tb.TimeBlockType);
       ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");
       cu.click(tb.OpenStartDateCalendar);
       pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        Thread.sleep(2000);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        cu.click(tb.clickOnEndDateIcon);
        //Thread.sleep(4000);
       cu.click(tb.OpenEndDateCalendar);
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        Thread.sleep(2000);
       // cu.click(tb.ProceedBtnClick);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
       // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");

        cu.click(pa.selectStartTime);
        cu.click(pa.selectSetStartTime);

        // End time selection
        cu.click(pa.selectEndTime);
        cu.click(pa.selectAddhour);
        cu.click(pa.selectSetEndTime);
       // driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", tb.scrollableXpath);
              
        Thread.sleep(2000);
         tb.clickSaveAndHandlePopup();
          Thread.sleep(5000);
          cu.logout(lp);

    }

   // @Test(priority = 2)
    public void TimeBlockDelete() throws InterruptedException
    {
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
   
        cu.click(pa.clickCareTeamDash);
        cu.click(pa.clickAppointments);

        cu.click(pa.SelectProviderDropdown);
        cu.click(pa.SelectProviderFilterChk1);
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        
        Thread.sleep(1000);
        cu.click(tb.TimeBlockSelect);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", tb.scrollableXpath);
        Thread.sleep(2000);
        cu.click(pa.DeleteProviderApp);
        Thread.sleep(2000);
        cu.click(pa.ClickOnDeleteBtnPopup);
        Thread.sleep(2000);

    }
}
