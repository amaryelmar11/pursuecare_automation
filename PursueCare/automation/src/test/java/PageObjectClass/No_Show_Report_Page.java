package PageObjectClass;

import java.lang.annotation.Native;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class No_Show_Report_Page extends BasePageClass{

        WebDriver driver;

    public No_Show_Report_Page(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//a[normalize-space()='No Show Report']")
    public WebElement clickNoshowReport;

    @FindBy(xpath="//input[@placeholder='Search']")
    public WebElement BtnSearch;

    @FindBy(xpath="//mat-label[normalize-space()='Medical NSR']")
    public WebElement clickMedicalNSRDrpDwn;

    @FindBy(xpath="//span[normalize-space()='Low (Less 30%)']")
    public WebElement SelectLowNSROption;

    @FindBy(xpath="//mat-label[normalize-space()='Counseling NSR']")
    public WebElement clickCounsellingNSRDrpDwn;

    @FindBy(xpath="//mat-label[normalize-space()='State']")
    public WebElement clickStateDrpDwn;

    @FindBy(xpath="//span[normalize-space()='Alabama']")
    public WebElement SelectAlabamaStateOption;

    @FindBy(xpath="//mat-label[normalize-space()='Program']")
    public WebElement clickStateProgramDrpDwn;

    @FindBy(xpath="//span[normalize-space()='SUD']")
    public WebElement SelectProgramOption;

    @FindBy(xpath="//mat-label[normalize-space()='Last Tox Screen']")
    public WebElement clickToxSxreenDrpDwn;

    @FindBy(xpath="//span[normalize-space()='0 - 6 days ago']")
    public WebElement SelectToxScreenOption;

    //Get Provider Name For Validation
    @FindBy(xpath="//div[@class='profile-usertitle']/div[1]")
    public WebElement ProviderNameForValidation;
 

    


    


}