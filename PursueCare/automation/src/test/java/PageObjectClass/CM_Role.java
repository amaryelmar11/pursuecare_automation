package PageObjectClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
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

    @FindBy(xpath="//mat-option//span[normalize-space(text())='No']")
    public WebElement add_patient_InterpreterPrefNO;



    @FindBy(xpath="//mat-form-field[.//mat-label[normalize-space(text())='Interpreter Language']]//mat-select")
    public WebElement InterpreterPrefLanguageselection_DrpDwn;

    @FindBy(xpath="//div[@aria-multiselectable='false']/mat-option[2]")
    public WebElement InterpreterPrefLanguageselection;

    @FindBy(xpath="//button[@type='onSubmit(){}']")
    public WebElement Save_Patient_Info;

    // Add Patient modal close (X/Close) button
    @FindBy(xpath = "//button[@aria-label='Close dialog']")
    public WebElement Clos_AddPatient_Tab;
  // Connect To User
  @FindBy(xpath="//span[@class='mat-mdc-menu-item-text']//span[normalize-space(text())='Connect to Users']")
  public WebElement ConnectToUsers;

  @FindBy(xpath="//mat-select[@formcontrolname='userroleid']")
  public WebElement RoleSelectDrpDwn;

  @FindBy(xpath="//div[@aria-multiselectable='false']/mat-option[1]")
  public WebElement ProviderRoleSelect;

  @FindBy(xpath="//mat-select[@formcontrolname='userid']")
  public WebElement UseridDrpDwn;

  @FindBy(xpath="//div[@aria-multiselectable='true']//mat-option[18]")
  public WebElement UseridSelectDrpDwn;

  @FindBy(xpath="//span[normalize-space()='Save']")
  public WebElement ClickSaveBtn;


    //Appointment creation 

    @FindBy(xpath="//a[@href='/carecoordinator/dashboard/main']")
    public WebElement cm_dashboard;

    @FindBy(xpath="//div[@class='profile-usertitle-job']")
    public WebElement cm_userrole;
   
    @FindBy(xpath="//span[normalize-space()='Care Team']")
    public WebElement menu_careteam;
    
    @FindBy(xpath="//a[normalize-space()='Appointments']")
    public WebElement menu_appintment;

    @FindBy(xpath="//button[@aria-label='Open calendar']//span[@class='mat-mdc-button-touch-target']")
    
    public WebElement click_calendar;

    // Patients list - search and result email cell (used in Patient_Creation.Check_Patient_Created)
    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement patient_Search;

    @FindBy(xpath = "//tbody[@role='rowgroup']//td[contains(@class,'mat-column-email')]")
    public WebElement Created_Pateint_Email_PatientList;

    // Duplicate patient validation message
    @FindBy(xpath = "//*[contains(normalize-space(text()),'This email has been registered already')]")
    public WebElement verify_Duplicate_Pateint_Creation;

    // Patients list action dropdown and options (used in Patient_Creation)
    @FindBy(xpath = "//tbody[@role='rowgroup']//td[contains(@class,'mat-column-action') or contains(@class,'mat-column-actions')]//button")
    public WebElement Click_Pateints_ActionTab;

    @FindBy(xpath = "//span[contains(text(),'View Patient')]")
    public WebElement Click_View_Patient;

    @FindBy(xpath = "//span[contains(text(),'Edit Patient')]")
    public WebElement Click_Edit_Patient;

    @FindBy(xpath = "//h4[normalize-space()='View Patient']")
    public WebElement viewPatient;

    @FindBy(xpath = "//h4[normalize-space()='Edit Patient']")
    public WebElement editPatient;

    @FindBy(xpath = "//div[@class='editRowModal']//div[normalize-space(text())='Edit Patient']")
    public WebElement editPatientText;


    @FindBy(xpath = "//div[@class='editRowModal']//div[normalize-space(text())='View Patient']")
    public WebElement viewPatientText;


    //Provder Menu

    @FindBy(xpath = "//a[.//span[normalize-space(text())='Providers']]")
    public WebElement providermenu;

    @FindBy(xpath = "//a[normalize-space()='Provider List']")
    public WebElement providerlistmenu;

    @FindBy(xpath = "//mat-form-field[.//mat-label[normalize-space(text())='Search']]//input")
    public WebElement providersearch;

    @FindBy(xpath = " //mat-form-field[.//mat-label[normalize-space(text())='Licensed State']]//mat-select")
    public WebElement licensedstate;
   
    @FindBy(xpath = " //mat-form-field[.//mat-label[normalize-space(text())='Insurances']]//mat-select")
    public WebElement Insurances;

    @FindBy(xpath = " //mat-form-field[.//mat-label[normalize-space(text())='Weekly Schedule']]//mat-select")
    public WebElement Weekly_Schedule;

    @FindBy(xpath = "//mat-form-field[.//mat-label[normalize-space(text())='Speciality']]//mat-select")
    public WebElement Speciality;

    @FindBy(xpath = "//button[@aria-label='Clear filters']//span[@class='mat-mdc-button-touch-target']")
    public WebElement Clear_filter;

    //New Mandatory Field DOB
    @FindBy(xpath = "//button[@aria-label='Open calendar']")
    public WebElement ClickDatePatientCreation;


    public void selectRandomState() {
        List<WebElement> options = driver.findElements(By.xpath("//div[contains(@class,'cdk-overlay-pane')]//mat-option"));
        if (options == null || options.isEmpty()) {
            throw new IllegalStateException("No states found in dropdown");
        }
        int index = new Random().nextInt(options.size());
        options.get(index).click();
    }
    
    public void BlockclickDateAfterXDaysPASCM(WebDriver driver, int daysToAdd, String datePattern) {
        // Step 1: Calculate target date
        LocalDate targetDate = LocalDate.now().plusDays(daysToAdd);
    
        // Step 2: Format using the given pattern (e.g., "MMMM d, yyyy")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String dateToClick = targetDate.format(formatter);
        System.out.println(dateToClick);
        // Step 3: Build dynamic XPath
        String xpath = "//button[@aria-label='" + dateToClick + "']/span";
        System.out.println(xpath);
        // Step 4: Locate and click
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    
        System.out.println("Clicked on date: " + dateToClick);
    }
}