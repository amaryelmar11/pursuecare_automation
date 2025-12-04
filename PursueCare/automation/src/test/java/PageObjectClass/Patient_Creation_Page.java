package PageObjectClass;

import java.lang.annotation.Native;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Patient_Creation_Page extends BasePageClass{

        WebDriver driver;

    public Patient_Creation_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//button[@aria-label='Add item']")
    public WebElement addPatient;

    @FindBy(xpath="//input[@formcontrolname='email']")
    public WebElement add_pat_Email;

    @FindBy(xpath="//mat-select[@formcontrolname='timezoneid']")
    public WebElement add_pat_Timezone;

    @FindBy(xpath="//div[@aria-multiselectable='false']/mat-option[3]")
    public WebElement timezone_EST;

    @FindBy(xpath="//input[@formcontrolname='firstname']")
    public WebElement add_patient_FirstName;

    @FindBy(xpath="//input[@formcontrolname='middlename']")
    public WebElement add_patient_middlename;

    @FindBy(xpath="//input[@formcontrolname='lastname']")
    public WebElement add_patient_lastname;

    @FindBy(xpath="//mat-select[@formcontrolname='gender']")
    public WebElement Gender_drpdwn;

    @FindBy(xpath="//mat-option[@value='Male']")
    public WebElement Gender_Male;

    @FindBy(xpath="//mat-select[@formcontrolname='state']")
    public WebElement Select_State_DrpDwn;

    @FindBy(xpath="//input[@formcontrolname='phonenumber']")
    public WebElement add_patient_Phone;

    @FindBy(xpath="//mat-select[@formcontrolname='interpreterPreference']")
    public WebElement add_patient_InterpreterPref;

    @FindBy(xpath="//div[@aria-multiselectable='false']/mat-option[2]")
    public WebElement add_patient_InterpreterPrefYES;

    @FindBy(xpath="//mat-select[@formcontrolname='interpreterLanguage']")
    public WebElement InterpreterPrefLanguageselection_DrpDwn;

    @FindBy(xpath="//div[@aria-multiselectable='false']/mat-option[2]")
    public WebElement InterpreterPrefLanguageselection;

    @FindBy(xpath="//button[@type='onSubmit(){}']")
    public WebElement Save_Patient_Info;

    // New Chnage For Selecting patient Date of Birth
    @FindBy(xpath="//input[@formcontrolname='dob']")
    public WebElement add_patient_DateOfBirth;

    // For Pateint Creation checks
    @FindBy(xpath="//input[@placeholder='Search']")
    public WebElement patient_Search;

    @FindBy(xpath="//tbody[@class='mdc-data-table__content ng-star-inserted']/tr/td[6]")
    public WebElement Created_Pateint_Email_PatientList;

    // Close the Add Patient Tab

    @FindBy(xpath="//button[@aria-label='Close dialog']")
    public WebElement Clos_AddPatient_Tab;

    // For VerifyingPatientDuplication method

    @FindBy(xpath="//strong[normalize-space()='This email has been registered already']")
    public WebElement verify_Duplicate_Pateint_Creation;

    // For View, Edit, Connected Patient TestCases

    @FindBy(xpath="//tbody[@class='mdc-data-table__content ng-star-inserted']/tr/td[11]")
    public WebElement Click_Pateints_ActionTab;

    @FindBy(xpath="//div[@class='mat-mdc-menu-content']/button[4]")
    public WebElement Click_View_Patient;

    @FindBy(xpath="//div[@class='mat-mdc-menu-content']/button[5]")
    public WebElement Click_Edit_Patient;

    //View And Edit Patient texts
    @FindBy(xpath="//div[normalize-space()='View Patient'][1]")
    public WebElement viewPatient;
    
    @FindBy(xpath="//div[normalize-space()='Edit Patient'][1]")
    public WebElement editPatient;

    @FindBy(xpath="//div[@class='mat-mdc-menu-content']/button[9]")
    public WebElement ConnectToUsers;

    @FindBy(xpath="//div[@class='mat-mdc-menu-content']/button[8]")
    public WebElement ClickShowConnectedUsers;

    @FindBy(xpath="//div[@class='dropdown mr-2']/mat-form-field/div[1]/div/div/input")
    public WebElement clickSearchConnectedUsersList;

    @FindBy(xpath="//div[@class='addContainer']/div[2]//tbody/tr/td[6]")
    public WebElement GetProviderNameFormConnectedUserList;

    @FindBy(xpath="//div[@class='mat-mdc-menu-content']/button[6]")
    public WebElement ClickDeletePatientAdmin;
    
    // Connect To User

    @FindBy(xpath="//mat-select[@formcontrolname='userroleid']")
    public WebElement RoleSelectDrpDwn;

    @FindBy(xpath="//div[@aria-multiselectable='false']/mat-option[1]")
    public WebElement ProviderRoleSelect;

    @FindBy(xpath="//mat-select[@formcontrolname='userid']")
    public WebElement UseridDrpDwn;

    @FindBy(xpath="//span[normalize-space()='Rahul Singh']")
    public WebElement UseridSelectDrpDwn;

    @FindBy(xpath="//span[normalize-space()='Save']")
    public WebElement ClickSaveBtn;
    //Method to crete a random number for selecting state

    Random rand = new Random();
    int randomIndex = rand.nextInt(52) + 1;

    public WebElement getRandomStateOption() {
        int randomIndex = rand.nextInt(52) + 1; // 1–52
        String xpath = "//div[@aria-multiselectable='false']/mat-option[" + randomIndex + "]";
        return driver.findElement(By.xpath(xpath));
    }

    public void selectRandomState() {
        getRandomStateOption().click();
    }


}