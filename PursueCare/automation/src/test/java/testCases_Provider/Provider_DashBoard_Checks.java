package testCases_Provider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.Provider_Dashboard_Page;

public class Provider_DashBoard_Checks extends Baseclass{

    private Common_Utils cu;
    private Provider_Dashboard_Page pd;
    private LoginPage lp;

    @BeforeClass
    public void initPages()
    {
        cu = new Common_Utils(driver);
        pd = new Provider_Dashboard_Page(driver);
        lp = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void TodaysAppointmentListChecks()
    {

        cu.enterText(lp.emailId, p.getProperty("provider1"));
        cu.enterText(lp.password, p.getProperty("password1"));
        lp.clickLoginbtn();

       /*  String text = cu.getElementText(pd.todaysAppointment);
        Assert.assertEquals(text, "Today's Appointments");

        // Store selected values in variables named after WebElement names
         String meetingStatusValue = cu.selectFromDropdown(pd.meetingStatus, "value", "2");
        String stateValue = cu.selectFromDropdown(pd.state, "value", "2");
        String medicalNSRValue = cu.selectFromDropdown(pd.medicalNSR, "value", "2");
        String counsellingNSRValue = cu.selectFromDropdown(pd.counsellingNSR, "value", "2");
        String appointmentStatusValue = cu.selectFromDropdown(pd.appointmentStatus, "value", "2");
        String programValue = cu.selectFromDropdown(pd.program, "text", "Unassigned");



        cu.click(pd.resetFilterTodaysAppointment);

        Assert.assertNotEquals("Meeting Status", meetingStatusValue);
        Assert.assertNotEquals("State", stateValue);
        Assert.assertNotEquals("Medical NSR",  medicalNSRValue);
        Assert.assertNotEquals("Counseling NSR", counsellingNSRValue);
        Assert.assertNotEquals("Appointment Status", appointmentStatusValue);
        Assert.assertNotEquals("Program", programValue);

        cu.click(pd.actionButton);

       Assert.assertEquals("Canceled by Staff", cu.getElementText(pd.cancelByStaff));
       Assert.assertEquals("Completed Successfully", cu.getElementText(pd.completedSuccessfully));
       Assert.assertEquals("Patient No Show", cu.getElementText(pd.patientNoShow));
       Assert.assertEquals("Not Completed - Tech Issue", cu.getElementText(pd.notCompletedTechIssue));

       new Actions(driver).sendKeys(Keys.ESCAPE).perform(); */
    }

    @Test(priority = 2)
    public void CompletedPatientAssessments() throws InterruptedException
    {
       // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200);");
        Thread.sleep(2000);
        String ClickProgrmvalue = cu.selectFromDropdown(pd.ClickProgrm, "value", "SUD");
        String CleckAssessmentValue = cu.selectFromDropdown(pd.CleckAssessment, "value", "2");
         cu.click(pd.ClickDateIcon);
         cu.doubleClickElement(driver, pd.ClickTodayDate);
        
        cu.click(pd.ClickResetFilterCompletedPatientAssessments);

        Assert.assertNotEquals("Program", ClickProgrmvalue);
        Assert.assertNotEquals("Assessment", CleckAssessmentValue);

        String patientName = cu.getElementText(pd.getPatientName);

        cu.click(pd.ClickOnViewPatientResponse);

        String patientNameAss = cu.getElementText(pd.ValidatePatientNameFromAssessScreen);

        Assert.assertEquals(patientName, patientNameAss);

        cu.click(pd.CuttheAssessmentScreen); 

    }
}
