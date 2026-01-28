package testCases_Provider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import BasePage.RandomData;
import PageObjectClass.Appointment_Report_Page;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.Patient_Creation_Page;
import PageObjectClass.ProviderAppointmentsPage;



public class Patient_Creation extends Baseclass{

    private String createdPatientEmail;
    private LoginPage lp;
    private DashBoardPage dp;
    private Common_Utils cu;
    private Patient_Creation_Page pc;
    private Appointment_Report_Page pr;
    private ProviderAppointmentsPage pa;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        dp = new DashBoardPage(driver);
        cu = new Common_Utils(driver);
        pc = new Patient_Creation_Page(driver);
        createdPatientEmail = RandomData.getEmail();
        pr = new Appointment_Report_Page(driver);
        pa = new ProviderAppointmentsPage(driver);
}
    

    @Test(priority = 1)
    public void Valid_Patient_Creation() throws InterruptedException
    {
        lp.enterEmailId(p.getProperty("provider1"));
        lp.enterPassword(p.getProperty("Password1"));
        lp.clickLoginbtn();

        cu.click(dp.clickOnPatients);
        cu.click(pc.addPatient);

        
       cu.enterText(pc.add_pat_Email, createdPatientEmail);
       Thread.sleep(2000);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);

        cu.click(pc.add_pat_Timezone);
        cu.click(pc.timezone_EST);
        cu.enterText(pc.add_patient_FirstName, RandomData.getFirstName());
        cu.enterText(pc.add_patient_middlename, RandomData.getMiddleName());
        cu.enterText(pc.add_patient_lastname, RandomData.getLastName());
       
      /*  cu.click(pr.ClickonDateRangeFilter);
        Thread.sleep(2000);
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy"); */
        cu.click(pc.add_patient_DateOfBirth);
        cu.enterText(pc.add_patient_DateOfBirth, cu.getRandomDOB());
        cu.click(pc.Gender_drpdwn);
        cu.click(pc.Gender_Male);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        Thread.sleep(2000);
        cu.click(pc.Select_State_DrpDwn);
        pc.selectRandomState();
        cu.enterText(pc.add_patient_Phone, RandomData.getPhone());

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        cu.click(pc.add_patient_InterpreterPref);
        cu.click(pc.add_patient_InterpreterPrefYES);
        cu.click(pc.InterpreterPrefLanguageselection_DrpDwn);
        Thread.sleep(2000);
        cu.click(pc.InterpreterPrefLanguageselection);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");


        cu.click(pc.Save_Patient_Info);
        Thread.sleep(2000);

    }

    @Test(priority = 2)
    public void Check_Patient_Created() throws InterruptedException
    {
        // Use the same email generated in Valid_Patient_Creation
        

        cu.enterText(pc.patient_Search, createdPatientEmail);
        System.out.println(createdPatientEmail);
        Thread.sleep(1000);
        String email = cu.getElementText(pc.Created_Pateint_Email_PatientList);
        Thread.sleep(1000);
        Assert.assertEquals(createdPatientEmail, email);

    }

    @Test(priority = 3)
    public void VerifyingPatientDuplication()
    {
       // String emailToVerify = createdPatientEmail;
        cu.click(pc.addPatient);
        cu.enterText(pc.add_pat_Email, createdPatientEmail);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        String dupText = cu.getElementText(pc.verify_Duplicate_Pateint_Creation);
        Assert.assertEquals(dupText, "This email has been registered already");

        cu.click(pc.Clos_AddPatient_Tab);
    }

    @Test(priority = 4)
    public void ViewEditPatientAssessmentOptions() throws InterruptedException
    {

        cu.click(pc.Click_Pateints_ActionTab);
        Thread.sleep(1000);
         cu.click(pc.Click_View_Patient);
        String viewPatientText = cu.getElementText(pc.viewPatient);
        System.out.print(viewPatientText);
        Assert.assertEquals(viewPatientText, "View Patient");

        cu.click(pc.Clos_AddPatient_Tab);
        Thread.sleep(1000);
        cu.click(pc.Click_Pateints_ActionTab);
        cu.click(pc.Click_Edit_Patient);
        String editPatientText = cu.getElementText(pc.editPatient);
        System.out.print(editPatientText);

        Assert.assertEquals(editPatientText, "Edit Patient");
        cu.click(pc.Clos_AddPatient_Tab);
    }


    @Test(priority = 5)
    public void ConnectedUserToNewPatient() throws InterruptedException
    {
        cu.click(pc.Click_Pateints_ActionTab);
        cu.click(pc.ConnectToUsers);
        Thread.sleep(1000);
        cu.click(pc.RoleSelectDrpDwn);
        cu.click(pc.ProviderRoleSelect);
        cu.click(pc.UseridDrpDwn);
        cu.click(pc.UseridSelectDrpDwn);

        cu.click(pc.ClickSaveBtn);

        cu.click(pc.Click_Pateints_ActionTab);
        cu.click(pc.ClickShowConnectedUsers);
       String text =  cu.getElementText(pc.GetProviderNameFormConnectedUserList);
       Assert.assertEquals(text, "Rahul Singh");
       cu.click(pc.Clos_AddPatient_Tab);
        
       //cu.logout(lp);
       /* cu.click(dp.ClickOnProvider);
        cu.click(dp.ClcikOnProviderList);
        cu.enterText(dp.SearchBtnProviderList, "Rahul Singh");
        //cu.click(dp.VisibleSearchBtnProviderList);
        Thread.sleep(4000);
        cu.click(dp.ActionBtnProviderList);
        cu.click(dp.ConnectedPatientProviderList);
       cu.enterText(dp.SearchPatientConnectedPatientList,createdPatientEmail);
       Thread.sleep(1000);
        String email = cu.getElementText(dp.GetMailId);

       Assert.assertEquals(email, createdPatientEmail);

        cu.click(dp.CloseConnectedPatientListDialogue); */

       

    }

    @Test(priority = 6)
    public void checkpatientListAssessmentOptions() throws InterruptedException
    {
        cu.click(pc.Click_Pateints_ActionTab);
        cu.click(pc.Click_Assessment_Options);

       //Sorting Checks for Assessment Options
    /*     cu.checkColumnSorting(1);
        cu.checkColumnSorting(2);
        cu.checkColumnSorting(3);
        cu.checkColumnSorting(4);
        cu.checkColumnSorting(5);
        cu.checkColumnSorting(6);
        cu.checkColumnSorting(7);
        cu.checkColumnSorting(8); 
*/
        Thread.sleep(2000);
        cu.enterText(pc.SearchBtnAssessmentOptions, "CAGE-AID Questionnaire");
        Thread.sleep(1000);
        cu.click(pc.DeleteParticularAssessmentOption);
        cu.click(dp.ClickOnDeletePatient);
        Thread.sleep(1000);
        cu.click(pc.AddpatientAssessment);
        cu.click(pc.AssessmentNameDrpDwn);
        Thread.sleep(1000);
        cu.click(pc.SelectAssessmentName);
        cu.enterText(pc.InputDaysForAssessment, "0");

        cu.click(pr.ClickonDateRangeFilter);
        Thread.sleep(1000);
        pa.BlockclickDateAfterXDays(driver, 0, "M/d/yyyy");

        cu.click(pa.selectSaveButton);
        cu.click(pc.ClickBackBtnAssessmentOptions);
        //cu.logout(lp);

    }

    @Test(priority = 7)
    public void CheckResetPasswordpatient() throws InterruptedException
    {
       cu.click(pc.Click_Pateints_ActionTab);
       cu.enterText(pc.patient_Search, createdPatientEmail);
       Thread.sleep(1000);
       cu.click(pc.ClickResetPasswordPatient);
       Thread.sleep(1000);
       cu.click(pc.ClickOkBtnResetPasswordPatient);
       String tempPassword = cu.getElementText(pc.GetTempPassword);
       Assert.assertNotNull(tempPassword);
       System.out.println(tempPassword);
       Thread.sleep(2000);
       cu.click(pc.ClickOkBtnResetPasswordPatient);
       cu.logout(lp);
       Thread.sleep(1000);
       cu.click(lp.emailId);
       lp.emailId.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
       lp.emailId.sendKeys(Keys.BACK_SPACE);
       lp.emailId.sendKeys(createdPatientEmail);
       Thread.sleep(1000);
       cu.click(lp.password);
       Thread.sleep(1000);
       lp.password.sendKeys(Keys.chord(Keys.CONTROL, "a")); // CTRL+A to select all
       lp.password.sendKeys(Keys.BACK_SPACE);
       lp.password.sendKeys(tempPassword);
       Thread.sleep(2000);
       lp.Loginbtn.click();
       Thread.sleep(2000);
       cu.enterText(pc.EnterChangePasswordPatient, p.getProperty("PatientNewPass"));
       cu.enterText(pc.EnterConfirmPasswordPatient, p.getProperty("PatientNewPass"));
       cu.click(pc.ClickSaveBtn);
       Thread.sleep(1000);
       //cu.logout(lp);
       lp.emailId.clear();
       lp.password.clear();
       cu.login(lp, createdPatientEmail, p.getProperty("PatientNewPass"));
       cu.click(pc.ClikCAGEAIDQuestionnaire);
       cu.click(pc.ClickStartAssessmentBtn);
       cu.click(pc.ClickYesBtn);
       cu.click(pc.ClickNextBtn);
       Thread.sleep(1000);
       cu.click(pc.ClickYesBtn);
       cu.click(pc.ClickNoBtn);
       Thread.sleep(1000);
       cu.click(pc.ClickYesBtn);
       cu.click(pc.ClickNextBtn);
       Thread.sleep(1000);
       cu.click(pc.ClickYesBtn);
       cu.click(pc.ClickSubmitBtn);

       cu.click(pc.ClickPatientLogoutBtn);
       Thread.sleep(1000);

    }

    @Test(priority = 8)
    public void checkpatientAssessmentOptions() throws InterruptedException
    {
        cu.login(lp, p.getProperty("provider1"), p.getProperty("Password1"));
        cu.click(dp.clickOnPatients);
        Thread.sleep(1000);
        cu.enterText(pc.patient_Search, createdPatientEmail);
        Thread.sleep(1000);
        cu.click(pc.Click_Pateints_ActionTab);
        Thread.sleep(1000);
        cu.click(pc.Click_Assessment_Options);
        Thread.sleep(1000);
        cu.enterText(pc.SearchBtnAssessmentOptions, "CAGE-AID Questionnaire");
        Thread.sleep(2000);
        cu.click(pc.ViewParticularAssessmentOption);
        Thread.sleep(1000);
        cu.click(pc.Clos_AddPatient_Tab);
        Thread.sleep(1000);
        Assert.assertEquals(cu.getElementText(pc.ReviewAssessmentName), cu.getElementText(pc.loggedInProviderName));
        Assert.assertTrue(cu.isDateCurrentDate(cu.getElementText(pc.ReviewAssessmentDate)));
        Assert.assertEquals(cu.getElementText(pc.AssessmentStatus), "completed");
        Thread.sleep(1000);
        cu.logout(lp);
        
    }

    @Test(priority = 9)
    public void DeleteCreatedPatient() throws InterruptedException
    {
        cu.login(lp, p.getProperty("Admin"), p.getProperty("PasswordA"));
        cu.click(dp.PatientClickAdmin);
        cu.click(dp.PatientListClickAdmin);
        Thread.sleep(2000);
        cu.enterText(dp.SearchBtnAdminPatinetList, createdPatientEmail);
        Thread.sleep(2000);
        cu.click(dp.ClickActionBtnPatintListAdmin);
        cu.click(pc.ClickDeletePatientAdmin);
        cu.click(dp.ClickOnDeletePatient);
        Thread.sleep(3000);

    }
}
