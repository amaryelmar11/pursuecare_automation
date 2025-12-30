package testCases_Case;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderAppointmentsPage;
import PageObjectClass.Provider_Dashboard_Page;
import PageObjectClassCM.Dashboard_CM_Page;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import utilities.TableSortUtils;

public class CaseManager_Dashboard extends Baseclass {

    private Common_Utils cu;
    private DashBoardPage dp;
    private LoginPage lp;
    private WebDriverWait wait;
    private Dashboard_CM_Page dcp;
    private Appointment_Report_Page pr;
    private ProviderAppointmentsPage pa;
    private Provider_Dashboard_Page pd;
    

    @BeforeClass
    public void initPages()
    {
        cu = new Common_Utils(driver);
        dp = new DashBoardPage(driver);
        lp = new LoginPage(driver);
        dcp = new Dashboard_CM_Page(driver);
        pr = new Appointment_Report_Page(driver);
        pa = new ProviderAppointmentsPage(driver);
        pd = new Provider_Dashboard_Page(driver);
    
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void MyCaseload_Checks() throws InterruptedException
    {
        cu.login(lp, p.getProperty("case"), p.getProperty("case_pass"));
        Assert.assertEquals("Dashboard", dp.dashBoardtext());

        String nextAppointmentScheduledValue = cu.selectFromDropdown(dcp.ClickOnNextAppointmentScheduledDrpDwn, "value", "1");
        Assert.assertEquals(nextAppointmentScheduledValue, "Scheduled");

        String statusValue = cu.selectFromDropdown(dcp.ClickStatusDrpDwn, "value", "4");
        System.out.println(statusValue);
        Assert.assertEquals(statusValue, "AR");

       String lastToxScreenValue = cu.selectFromDropdown(dcp.ClickLastToxScreenDrpDwn, "value", "2");
        System.out.println(lastToxScreenValue);
        Assert.assertEquals(lastToxScreenValue, "7 - 13 days ago");

        String daysInTreatmentValue = cu.selectFromDropdown(dcp.ClickDaysInTreatmentDrpDwn, "value", "2");
        System.out.println(daysInTreatmentValue);
        Assert.assertEquals(daysInTreatmentValue, "More than 90 days");

     /*  cu.click(dcp.SelectPursueCareRxPharmacistSpeciality);
       cu.click(dcp.SelectPursueCareRxPharmacistSpeciality);
       new Actions(driver).sendKeys(Keys.ESCAPE).perform();   */

       cu.click(pr.ClickonDateRangeFilter);
            
       pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
       pa.BlockclickDateAfterXDays(driver, 1, "M/d/yyyy");

       cu.click(dcp.ClickResetFilterMyCaseload);

       // Check For Default Values
     
       Assert.assertEquals("Next Appointment Scheduled", cu.getElementText(dcp.ClickOnNextAppointmentScheduledDrpDwn));
       Assert.assertEquals("State", cu.getElementText(dcp.ClickStatusDrpDwn));
       Assert.assertEquals("Last tox screen", cu.getElementText(dcp.ClickLastToxScreenDrpDwn));
       Assert.assertEquals("Days in treatment", cu.getElementText(dcp.ClickDaysInTreatmentDrpDwn));
       Assert.assertEquals("Speciality", cu.getElementText(dcp.ClickSpecialityDrpDwn));
    
       cu.click(dcp.ClickRefreshButton);
       Thread.sleep(3000);

       // Check for Sorting My Caseload 

        cu.checkDashColumnSorting("mycaseloadlist", 1);
        cu.checkDashColumnSorting("mycaseloadlist", 2);
        cu.checkDashColumnSorting("mycaseloadlist", 3);
        cu.checkDashColumnSorting("mycaseloadlist", 4);
        cu.checkDashColumnSorting("mycaseloadlist", 5);
       // cu.checkDashColumnSorting("mycaseloadlist", 6);
        cu.checkDashColumnSorting("mycaseloadlist", 7);

        // Check for Action Button
        cu.click(dcp.ClickActionButton);
        Assert.assertEquals("View Upcoming Appointments", cu.getElementText(dcp.GetViewUpcomingAppointmentsText));
        Assert.assertEquals("View Patient Assessments", cu.getElementText(dcp.GetViewPatientAssessmentsText));
        Assert.assertEquals("View Patient Information", cu.getElementText(dcp.GetViewPatientInformationText));
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();
        

    }

    @Test(priority = 2)
    public void TodaysAppointmentListChecks() throws InterruptedException
    {
        
       // Assert.assertEquals(cu.getElementText(pd.todaysAppointment), "Today's Appointments");

        // Store selected values in variables named after WebElement names
         String meetingStatusValue = cu.selectFromDropdown(pd.meetingStatus, "value", "2");
        String stateValue = cu.selectFromDropdown(dcp.StateFilterDrpDwn, "value", "2");
        String medicalNSRValue = cu.selectFromDropdown(dcp.MedicalNSRFilterDrpDwn, "value", "2");
        String counsellingNSRValue = cu.selectFromDropdown(dcp.CounsellingNSRFilterDrpDwn, "value", "2");
        String appointmentStatusValue = cu.selectFromDropdown(dcp.AppointmentStatusFilterDrpDwn, "value", "2");
        //String programValue = cu.selectFromDropdown(pd.program, "text", "Unassigned");

        Assert.assertNotEquals("Meeting Status", meetingStatusValue);
        Assert.assertNotEquals("State", stateValue);
        Assert.assertNotEquals("Medical NSR",  medicalNSRValue);
        Assert.assertNotEquals("Counseling NSR", counsellingNSRValue);
        Assert.assertNotEquals("Appointment Status", appointmentStatusValue);
        cu.click(dcp.clickResetFilterTodaysAppointments);
       // Assert.assertNotEquals("Program", programValue);

        cu.click(dcp.ActionBtnTodaysAppointment);

      // Assert.assertEquals("Canceled by Staff", cu.getElementText(pd.cancelByStaff));
       //Assert.assertEquals("Completed Successfully", cu.getElementText(pd.completedSuccessfully));
      // Assert.assertEquals("Patient No Show", cu.getElementText(pd.patientNoShow));
     //  Assert.assertEquals("Not Completed - Tech Issue", cu.getElementText(pd.notCompletedTechIssue));
     //  Assert.assertEquals("Edit Group Session", cu.getElementText(pd.editGroupSession));


       if (cu.getElementText(pd.editGroupSession).equals("Edit Group Session")) {
        Assert.assertEquals(cu.getElementText(pd.editGroupSession), "Edit Group Session");
    } else {
        Assert.assertEquals("Canceled by Staff",cu.getElementText(pd.cancelByStaff));
    
        Assert.assertEquals( "Completed Successfully",cu.getElementText(pd.completedSuccessfully));
    
        Assert.assertEquals("Patient No Show",cu.getElementText(pd.patientNoShow));
    
        Assert.assertEquals("Not Completed - Tech Issue",cu.getElementText(pd.notCompletedTechIssue));
    }
    

       new Actions(driver).sendKeys(Keys.ESCAPE).perform(); 

        // Check for Sorting My Today's Appointments List 

        cu.checkDashColumnSorting("todaysappointmentslist", 1);
        cu.checkDashColumnSorting("todaysappointmentslist", 2);
        cu.checkDashColumnSorting("todaysappointmentslist", 3);
        //cu.checkDashColumnSorting("todaysappointmentslist", 4);
       // cu.checkDashColumnSorting("todaysappointmentslist", 5);
        cu.checkDashColumnSorting("todaysappointmentslist", 6);
        cu.checkDashColumnSorting("todaysappointmentslist", 7);

    }

@Test(priority = 3)        
public void checksceduleRequest() throws InterruptedException
{
        Assert.assertEquals("Scheduling Requests", cu.getElementText(dcp.SchedulingRequestsHeading));

        cu.selectFromDropdown(dcp.ClickPatientDrpDwn, "value", "2");
        cu.selectFromDropdown(dcp.ClickRequestTypeDrpDwn, "value", "2");
       
        Assert.assertEquals("Connected Patients", cu.getElementText(dcp.ClickPatientDrpDwn));
        Assert.assertEquals("Cancellation Requests", cu.getElementText(dcp.ClickRequestTypeDrpDwn));
        cu.click(dcp.ClickResetFilterSchedulingRequests);

        Assert.assertEquals("Patients", cu.getElementText(dcp.ClickPatientDrpDwn));
        Assert.assertEquals("Request Type", cu.getElementText(dcp.ClickRequestTypeDrpDwn));

        
        cu.click(dcp.ClickActionButton);
        Assert.assertEquals("View Upcoming Appointments", cu.getElementText(dcp.GetViewUpcomingAppointmentsText));
        Assert.assertEquals("View Patient Assessments", cu.getElementText(dcp.GetViewPatientAssessmentsText));
        Assert.assertEquals("View Patient Information", cu.getElementText(dcp.GetViewPatientInformationText));
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();
}
    

}
