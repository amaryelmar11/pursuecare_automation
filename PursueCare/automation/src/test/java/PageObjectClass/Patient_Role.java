package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class Patient_Role extends BasePageClass{

        WebDriver driver;

    public Patient_Role(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }
//Menus
    @FindBy(xpath="//h4[normalize-space()='Dashboard']")
    public WebElement dashboardtext;

    @FindBy(xpath="//a[@href='/patient/dashboard']")
    public WebElement dashboardmenu;

    @FindBy(xpath="//h2[normalize-space()='Upcoming Appointments']")
    public WebElement upcomingappointmentsection;
    
    @FindBy(xpath="//h2[normalize-space()='Group Sessions']")
    public WebElement groupsessionsection;
    
    @FindBy(xpath="//h2[normalize-space()='Pending Assessment']")
    public WebElement pendingassessmentsection;

    @FindBy(xpath="//button[@mattooltip='Start Assessment'][1]")
    public WebElement click_on_assessment;
    
    @FindBy(xpath="//span[normalize-space()='Start']")
    public WebElement startassessment;

    @FindBy(xpath="//button[@aria-label='Close dialog']//span[@class='mat-mdc-button-touch-target']")
    public WebElement close_assessment_pop_up;


    @FindBy(xpath="//body//app-root//div[@class='tab-content']//div[@class='tab-content']//li[1]")
    public WebElement click_on_chat;

    

    @FindBy(xpath="//li[@class='clearfix active ng-star-inserted']//div[@class='col-7 p-1']")
    public WebElement providerchat;

    @FindBy(xpath="//textarea[@id='chatmessage']")
    public WebElement clicktypebox;

    


    @FindBy(xpath="//div[@class='profile-usertitle-job']")
    public WebElement patient_text;
    @FindBy(xpath="//button[@class='mat-badge nav-notification-icons mdc-icon-button mat-mdc-icon-button mat-badge-warning mat-unthemed mat-mdc-button-base mat-badge-overlap mat-badge-above mat-badge-after mat-badge-medium']//span[@class='mat-mdc-button-touch-target']")
    public WebElement dashboard_chat;
    
    @FindBy(xpath="//div[@id='mat-select-value-1']")
    public WebElement timezone_dropdown;
    
    @FindBy(xpath="//button[@class='nav-notification-icons mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']//span[@class='mat-mdc-button-touch-target']")
    public WebElement full_Screen;


    @FindBy(xpath="//button[@class='nav-notification-icons mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']//span[@class='mat-mdc-button-touch-target']")
    public WebElement full_screen_zoom_out;

    
    @FindBy(xpath="//body[1]/app-root[1]/app-patient-dashboard[1]/section[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[7]/div[2]/button[1]/span[4]")
    public WebElement upcoming_appointment_zoomclick;
   
    @FindBy(xpath="//img[@alt='User']")
    public WebElement profile_pic_click;
    
    @FindBy(xpath="//span[@class='mr-2 np-style']")
    public WebElement notification_setting_click;

    @FindBy(xpath="//p[2]//label[1]//span[1]//span[1]")
    public WebElement enable_email;
    
    @FindBy(xpath="//p[4]//label[1]//span[1]//span[1]")
    public WebElement enable_sms;

    @FindBy(xpath="//p[@class='ng-star-inserted']//span[@class='slider round']")
    public WebElement enable_push;

    @FindBy(xpath="//span[normalize-space()='Save']")
    public WebElement click_save;
    
    @FindBy(xpath="//textarea[@id='chatmessage']")
    public WebElement type_chat_message;

    @FindBy(xpath=" //button[@class='bg-blue mdc-fab mdc-fab--mini mat-mdc-mini-fab mat-accent mat-mdc-button-base']//span[@class='mat-mdc-button-touch-target']")
    public WebElement send_message;
    
    
    @FindBy(xpath="//span[normalize-space()='T2 provider']")
    public WebElement click_on_providerchat;


    
    

    @FindBy(xpath=" //span[normalize-space()='Logout']")
    public WebElement logout;
   
    

    @FindBy(xpath="//a[@href='/patient/chat']")
    public WebElement chat;

    @FindBy(xpath="//a[normalize-space()='Provider']")
    public WebElement Chat_provider;

    @FindBy(xpath="//a[@class='mat-badge nav-link active mat-badge-overlap mat-badge-above mat-badge-after mat-badge-medium']")
    public WebElement casemanagerchat;
    @FindBy(xpath="//a[@class='mat-badge nav-link mat-badge-overlap mat-badge-above mat-badge-after mat-badge-medium active']")
    public WebElement provider;

    @FindBy(xpath="//a[@href='/patient/pharmacy']")
    public WebElement pharmacy;
  

    @FindBy(xpath="//button[@class='mat-mdc-tooltip-trigger btn-tbl-edit mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base ng-star-inserted']//span[@class='mat-mdc-button-touch-target']")
    public WebElement assessment_next_button;
  


    @FindBy(xpath="//input[@placeholder='Search Provider...']")
    public WebElement providerchat_search_provider;
    
    @FindBy(xpath="//a[@class='menu-toggle ng-star-inserted']")
    public WebElement setting_menu;
    
    @FindBy(xpath="//a[normalize-space()='Change Password']")
    public WebElement changepassmenu;

    @FindBy(xpath="//input[@formcontrolname='currentPassword']")
    public WebElement currentpassword;

    @FindBy(xpath="//input[@formcontrolname='pnpfield']")
    public WebElement newpassword;


    @FindBy(xpath="//button[normalize-space()='Save']")
    public WebElement save;
    

}