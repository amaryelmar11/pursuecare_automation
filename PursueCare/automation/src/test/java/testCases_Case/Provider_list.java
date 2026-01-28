package testCases_Case;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import PageObjectClass.DashBoardPage;
import PageObjectClass.LoginPage;
import PageObjectClass.Patient_Creation_Page;
import PageObjectClass.Provider_List_Page;
import PageObjectClassCM.Provider_list_CM_Page;
import utilities.TableSortUtils;
import PageObjectClass.CM_Role;
import org.testng.annotations.BeforeClass;
import BasePage.Common_Utils;
import BasePage.RandomData;



   

public class Provider_list extends Baseclass{

    private LoginPage lp;
    private DashBoardPage dp;
    private Common_Utils cu;
    private CM_Role pc;
    private TableSortUtils ts;
    private Provider_List_Page pl;
    private Provider_list_CM_Page plc;
    private String providerEmail;
    private Patient_Creation_Page pcc;
    private String firstName;
    private String lastName;


    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        dp = new DashBoardPage(driver);
        cu = new Common_Utils(driver);
        pc = new CM_Role (driver);
        ts = new TableSortUtils();
        pl = new Provider_List_Page(driver);
        plc = new Provider_list_CM_Page(driver);
        firstName = RandomData.getFirstName();
        lastName =  RandomData.getLastName();
        providerEmail = firstName + "@pursuecare.com";
        pcc = new Patient_Creation_Page(driver);
  

}


    @Test(priority = 1)
    public void Provider_list_view_and_filter() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.enterEmailId(p.getProperty("case"));
        lp.enterPassword(p.getProperty("case_pass"));
        lp.clickLoginbtn();

      
        Thread.sleep(3000);
        cu.click(pc.providermenu);
        Thread.sleep(3000);
         cu.click(pc.provider_list_menu);
         Thread.sleep(5000);
       /*   
         cu.click(pc.providersearch);
         cu.enterText(pc.providersearch, "Grandy");
         Thread.sleep(3000);
         cu.click(pc.Clear_filter);
         Thread.sleep(3000);
         cu.click(pc.licensedstate);
         cu.click(pc.Clear_filter);
         Thread.sleep(3000);
         pc.selectRandomState();
         Thread.sleep(3000);
         
         cu.click(pc.Insurances);
         Thread.sleep(3000);
         cu.click(pc.Weekly_Schedule);
         Thread.sleep(3000);
         pc.selectRandomState();
         Thread.sleep(3000);
         cu.click(pc.Speciality);
         Thread.sleep(3000);
         pc.selectRandomState();
         Thread.sleep(3000);
         cu.click(pc.Clear_filter);
         Thread.sleep(3000);
         cu.click(pc.dashboard_menu);
         Thread.sleep(8000);
*/
            // Ascending sort
            List<WebElement> column = cu.getColumnElements(1);
            boolean status = TableSortUtils.isColumnSortedUniversal(column, "asc");
            System.out.println(status);
            Assert.assertTrue(status);
            //Descending order
            Thread.sleep(2000);
            cu.clickcolumnheader(1);
            Thread.sleep(2000);
           // cu.clickcolumnheader(1);
            List<WebElement> column1 = cu.getColumnElements(1);
            boolean status1 = TableSortUtils.isColumnSortedUniversal(column1, "desc");
            Assert.assertTrue(status1);

            cu.checkColumnSorting(4);
            cu.checkColumnSorting(5);
            cu.checkColumnSorting(6);
            cu.checkColumnSorting(7);
            cu.checkColumnSorting(8);
    }

    @Test(priority = 2)
    public void createProvider() throws InterruptedException 
    {
        cu.click(pl.ClickAddItemBtn);
        cu.enterText(plc.firstname, firstName);
        cu.enterText(plc.lastname, lastName);
        cu.enterText(plc.ProviderEmail, providerEmail);
        cu.click(pcc.add_pat_Timezone);
        cu.click(pcc.timezone_EST);
        cu.click(pcc.add_patient_DateOfBirth);
        cu.enterText(pcc.add_patient_DateOfBirth, cu.getRandomDOB());
        cu.enterText(plc.EnterNumber, RandomData.getPhone());
        cu.click(plc.SelectSpeciality);
        cu.click(plc.pursuecareRXSpeciality);
        cu.click(plc.PsychMeditationManager);
        cu.click(plc.TherapyCounseling);
        cu.click(plc.MATDescriber);
        cu.click(plc.AdmissionCouseller);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        cu.click(pl.ClickEmployeeType);
        cu.click(plc.SWEmpType);
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        cu.click(pc.Save_Patient_Info);
        Thread.sleep(5000);
       
    }

    @Test(priority = 3)
    public void verifyProviderCreated() throws InterruptedException
    {
        cu.enterText(plc.SearchFilterBtn, providerEmail);
        String createdProviderName = cu.getElementText(plc.GetCreatedProviderName).trim();
        Assert.assertEquals(createdProviderName, firstName + " " + lastName);
    }

    public void ResetPasswordForProvider() throws InterruptedException
    {
        
    }
}