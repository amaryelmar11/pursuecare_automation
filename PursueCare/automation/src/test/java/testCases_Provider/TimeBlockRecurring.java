package testCases_Provider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.TimeBlockPage;

public class TimeBlockRecurring extends Baseclass {

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
    public void createRecurringTimeBlock() throws InterruptedException {
        cu.login(lp, p.getProperty("ProviderFilter"), p.getProperty("PasswordFilter"));
        cu.click(pa.selectAppointmentDash);

        WebElement todayCell = pa.getTodayAppXpath();
        cu.click(todayCell);
        Thread.sleep(2000);

        // Select Time Block category and type
        cu.click(tb.CategorySelection);
        cu.click(tb.TimeBlockSelection);
        cu.click(tb.TypeDrpDwnSelection);
        cu.click(tb.TimeBlockType);

        // Set dates
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");
        cu.click(tb.OpenStartDateCalendar);
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        Thread.sleep(1000);
        cu.pressEscape();
        cu.click(tb.clickOnEndDateIcon);
        cu.click(tb.OpenEndDateCalendar);
        pa.BlockclickDateAfterXDays(driver, 3, "M/d/yyyy");
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(
       // By.xpath("//mat-datepicker-content"))); // locator for Angular Material calendar popup
        cu.pressEscape();
        Thread.sleep(3000);
        cu.click(tb.ProceedBtnClick);
        cu.pressEscape();
        // All day to avoid time pickers
      //  cu.click(tb.clickAllDayCheckbox);
      cu.click(pa.selectStartTime);
      cu.pressEscape();
      cu.click(pa.selectSetStartTime);

      // End time selection
      cu.click(pa.selectEndTime);
      cu.click(pa.selectAddhour);
      cu.click(pa.selectSetEndTime);
        // Scroll inside the dialog and save
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", tb.scrollableXpath);
        Thread.sleep(500);
        tb.clickSaveAndHandlePopup();
    }

    @Test(priority = 2)
    public void deleteRecurringTimeBlock() throws InterruptedException {
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
        // Reuse provider deletion flow elements from ProviderAppointmentsPage via TimeBlockPage helper if needed
        cu.click(pa.clickCareTeamDash);
        cu.click(pa.clickAppointments);
        cu.click(pa.SelectProviderDropdown);
        cu.click(pa.SelectProviderFilterChk1);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        Thread.sleep(1000);
        cu.click(tb .TimeBlockSelect);
        Thread.sleep(2000);
        cu.click(tb .EntireSeriesRecRadioBtn);
        cu.click(tb .ClickOkAfterSave);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", tb.scrollableXpath);
        cu.click(pa.DeleteProviderApp);
        cu.click(pa.ClickOnDeleteBtnPopup);
        Thread.sleep(3000);

    }
}
