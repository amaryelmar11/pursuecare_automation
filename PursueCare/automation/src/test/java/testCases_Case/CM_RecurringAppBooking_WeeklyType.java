package testCases_Case;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.CM_Role;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;

public class CM_RecurringAppBooking_WeeklyType extends Baseclass {

    private LoginPage lp;
    private Common_Utils cu;
    private CM_Role pa;
    private ProviderAppointmentsPage ap;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pa = new CM_Role(driver);
        ap = new ProviderAppointmentsPage(driver);
    }

    @Test(priority = 1)
    public void createWeeklyRecurringAppointment() throws InterruptedException {
        cu.login(lp, p.getProperty("caseManager1"), p.getProperty("caseManager_pass1"));
        cu.click(pa.menu_careteam);
        Thread.sleep(3000);
        cu.click(pa.menu_appintment);
        Thread.sleep(3000);

        WebElement todayCell = ap.getTodayAppXpathCM_PAS();
        cu.click(todayCell);
        Thread.sleep(3000);

        cu.click(ap.selectAppointment);
        cu.click(ap.selectAppointmentType);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

        cu.click(ap.selectHost);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        cu.click(ap.selectPatient);
        cu.enterText(ap.inputPatientName, p.getProperty("PatientSearch"));
        cu.click(ap.setVisiblePatient);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        Thread.sleep(3000);
        ap.selectNextRangeSlot();

        cu.click(ap.clickRecurringCheckbox);
        cu.click(ap.clickRepeatDrpDwn);
        // Select Weekly from repeat options
        cu.click(By.xpath("//span[normalize-space()='Weekly']"));

        // Set end date seven days from today
        cu.click(ap.clickOnEndDateIcon);
        ap.clickDateAfterXDays(driver, 7, "MMMM d, yyyy");
        Thread.sleep(1000);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

        cu.click(ap.selectSaveButton);
        Thread.sleep(1500);

        cu.logout(lp);
    }

    @Test(priority = 2)
    public void cmAppointmentDelete() throws InterruptedException {
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));

        cu.click(pa.menu_careteam);
        cu.click(pa.menu_appintment);

        cu.click(ap.SelectCaseManagerDrpDwn);
        cu.click(ap.SelectCaseManager1FromDrpdwn);
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        Thread.sleep(1000);
        cu.click(ap.SelectAppointmentForDeletion);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);
        cu.click(ap.ClickAllRecurringMeetingChkbx);
        cu.click(ap.DeleteProviderApp);
        Thread.sleep(500);
        cu.click(ap.ClickOnDeleteBtnPopup);
        Thread.sleep(1000);
    }
}

