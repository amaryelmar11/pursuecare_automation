package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class CM_Role extends BasePageClass{

        WebDriver driver;

    public CM_Role(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }
//Menus
    @FindBy(xpath="//span[normalize-space()='Dashboard']")
    public WebElement dashboard_menu;

    @FindBy(xpath="//h4[normalize-space()='Dashboard']")
    public WebElement dashboardtext;

    @FindBy(xpath="//span[normalize-space()='Providers']")
    public WebElement provider_menu;

    @FindBy(xpath="//a[normalize-space()='Provider List']")
    public WebElement provider_list_menu;

    @FindBy(xpath="//a[normalize-space()='Provider Availability']")
    public WebElement provider_availability_menu;

    @FindBy(xpath="//a[@href='/carecoordinator/providers/providerwiseappointment']")
    public WebElement provider_sessionlist_menu;






    @FindBy(xpath="//span[normalize-space()='Care Team']")
    public WebElement careteam_menu;

    @FindBy(xpath="//a[normalize-space()='Appointments']")
    public WebElement appointment_menu;

    @FindBy(xpath=" //span[normalize-space()='Patients']")
    public WebElement patient_menu;
    
    @FindBy(xpath=" //a[normalize-space()='Patients List']")
    public WebElement patient_list;
    
    @FindBy(xpath=" //a[@href='/carecoordinator/chat']")
    public WebElement chat_menu;

    @FindBy(xpath=" //span[normalize-space()='Group Sessions']")
    public WebElement groupsession_menu;
    

    @FindBy(xpath=" //a[@href='/carecoordinator/groupsession/allSession']")
    public WebElement session_list_menu;
    

    @FindBy(xpath="  //span[normalize-space()='Settings']")
    public WebElement settings_menu;
   
    @FindBy(xpath="  //a[normalize-space()='Users List']")
    public WebElement userlist_menu;

    @FindBy(xpath="  //a[normalize-space()='Change Password']")
    public WebElement changepass_menu;
    
    @FindBy(xpath="  //a[normalize-space()='Assessment']")
    public WebElement assessment_menu;
    
    @FindBy(xpath="  //a[normalize-space()='Time Block']")
    public WebElement timeblock_menu;

    @FindBy(xpath="  //a[normalize-space()='Resource Center']")
    public WebElement resource_center_menu;
    
    @FindBy(xpath="   //span[normalize-space()='Reports']")
    public WebElement reports_menu;
   
    @FindBy(xpath="   //a[@href='/authentication/signout']")
    public WebElement logout_menu;
    


    // Patient Add
    @FindBy(xpath="//button[@aria-label='Add item']//span[@class='mat-mdc-button-touch-target']")
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
}