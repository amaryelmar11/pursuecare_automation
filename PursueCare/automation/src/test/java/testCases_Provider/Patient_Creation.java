package testCases_Provider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import BasePage.RandomData;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.Patient_Creation_Page;



public class Patient_Creation extends Baseclass{

    private String createdPatientEmail;
    private LoginPage lp;
    private DashBoardPage dp;
    private Common_Utils cu;
    private Patient_Creation_Page pc;

    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        dp = new DashBoardPage(driver);
        cu = new Common_Utils(driver);
        pc = new Patient_Creation_Page(driver);
        createdPatientEmail = RandomData.getEmail();

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
        //driver.switchTo().activeElement().sendKeys(Keys.TAB);

        cu.click(pc.add_pat_Timezone);
        cu.click(pc.timezone_EST);
        cu.enterText(pc.add_patient_FirstName, RandomData.getFirstName());
        cu.enterText(pc.add_patient_middlename, RandomData.getMiddleName());
        cu.enterText(pc.add_patient_lastname, RandomData.getLastName());
        cu.click(pc.Gender_drpdwn);
        cu.click(pc.Gender_Male);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

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
        cu.click(pc.RoleSelectDrpDwn);
        cu.click(pc.ProviderRoleSelect);
        cu.click(pc.UseridDrpDwn);
        cu.click(pc.UseridSelectDrpDwn);
        cu.click(pc.ClickSaveBtn);

        cu.click(lp.clickonImageforLogout);
        cu.click(lp.clickonLogout);

        Thread.sleep(2000);
        cu.enterText(lp.emailId, p.getProperty("Admin"));
        cu.enterText(lp.password, p.getProperty("PasswordA"));
        lp.clickLoginbtn();

        Thread.sleep(4000);
        cu.click(dp.ClickOnProvider);
        cu.click(dp.ClcikOnProviderList);
        cu.enterText(dp.SearchBtnProviderList, "Rahul Singh");
        cu.click(dp.VisibleSearchBtnProviderList);
        Thread.sleep(4000);
        cu.click(dp.ActionBtnProviderList);
        cu.click(dp.ConnectedPatientProviderList);
       cu.enterText(dp.SearchPatientConnectedPatientList,createdPatientEmail);
       Thread.sleep(1000);
        String email = cu.getElementText(dp.GetMailId);

       Assert.assertEquals(email, createdPatientEmail);

        cu.click(dp.CloseConnectedPatientListDialogue);

       

    }

    @Test(priority = 6)
    public void DeleteCreatedPatient()
    {
        
        cu.click(dp.PatientClickAdmin);
        cu.click(dp.PatientListClickAdmin);
        cu.enterText(dp.SearchBtnAdminPatinetList, createdPatientEmail);
        cu.click(dp.ClickActionBtnPatintListAdmin);
        cu.click(dp.ClickOnDeletePatient);

    }
}
