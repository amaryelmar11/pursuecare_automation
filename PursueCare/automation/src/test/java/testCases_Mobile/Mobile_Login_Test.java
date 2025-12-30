package testCases_Mobile;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.MobileBaseClass;
import BasePage.Common_Utils;
import BasePage.Common_Utils_Mobile;
import PageObjectClass.Mobile.Login_Page_Mobile;

/**
 * Test class for Mobile Login functionality.
 * This class contains test cases for:
 * - Mobile app login validation
 * - Login with valid credentials
 * - Login with invalid credentials
 * 
 * Prerequisites:
 * - Appium server should be running (default: http://127.0.0.1:4723)
 * - Android emulator/device or iOS simulator/device should be connected
 * - App should be installed on the device
 * 
 * TestNG XML Configuration Example:
 * <parameter name="platform" value="android"/>
 * <parameter name="deviceName" value="emulator-5554"/>
 * <parameter name="appPath" value="path/to/app.apk"/>
 * 
 * @author Automation Team
 */
public class Mobile_Login_Test extends MobileBaseClass {

    // Page Object instances
    private Login_Page_Mobile lp;
    private Common_Utils cu;
    private Common_Utils_Mobile cm;

    /**
     * Initializes all page objects before test execution.
     */
    @BeforeClass
    public void initPages() {
        lp = new Login_Page_Mobile(driver);
        cu = new Common_Utils(driver);
        cm = new Common_Utils_Mobile(driver);
    }

    /**
     * Test case to verify successful login with valid credentials.
     * Steps:
     * 1. Enter valid email
     * 2. Enter valid password
     * 3. Click login button
     * 4. Verify successful login (navigate to dashboard or verify element)
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */

    @Test(priority = 1)
    public void verifyLoginPage() throws InterruptedException {
        Assert.assertEquals(cm.getElementText(lp.pursuecaretext), "Welcome to PursueCare");
        Assert.assertEquals(cm.getElementText(lp.LetsStartedText), "Let's get started!");
        Assert.assertEquals(cm.getElementText(lp.ForgotPassword), "Forgot Password ?");
        Assert.assertEquals(lp.SignUpText.getText(), "Sign Up");
        Assert.assertEquals(cm.getElementText(lp.VersionText), "Version - 2.7.3");
        Assert.assertEquals(cm.getElementText(lp.AboutUsText), "About Us");
        Assert.assertEquals(cm.getElementText(lp.ContactUsText), "Contact Us");
        Assert.assertEquals(cm.getElementText(lp.ChangeLanguage), "English");
      
    }

    @Test(priority = 2)
    public void verifyEmptyLoginValidation() throws InterruptedException {
        cm.click(lp.loginButton);
        Assert.assertEquals(cm.getElementText(lp.EmptyemailValidation), "Email is required");
        Assert.assertEquals(cm.getElementText(lp.EmptyPasswordValidation), "Password is required");
    }

    @Test(priority = 3)
    public void testValidLogin() throws InterruptedException {
        // Step 1-3: Perform login
        cm.login(lp, p.getProperty("mobile.patient.email"), p.getProperty("mobile.patient.password"));
        Thread.sleep(1000);
        cm.click(lp.SystemButton2);
        cm.click(lp.ClickGotItButton);
        cm.click(lp.GotItText);
        //Assert.assertEquals(cm.getElementText(lp.WelcomeBackText), "Welcome back,");
        // Step 4: Add verification logic here
        // Example: Verify dashboard element is visible
        Thread.sleep(2000); // Wait for navigation
        
        // Assert.assertTrue(dashboardPage.isDashboardVisible(), "Login failed - Dashboard not visible");
    }

    /**
     * Test case to verify login with invalid credentials.
     * Steps:
     * 1. Enter invalid email
     * 2. Enter invalid password
     * 3. Click login button
     * 4. Verify error message is displayed
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
    //@Test(priority = 2)
    public void testInvalidLogin() throws InterruptedException {
        // Step 1-3: Perform login with invalid credentials
        lp.login("invalid@email.com", "wrongpassword");
        
        Thread.sleep(2000); // Wait for error message
        
        // Step 4: Verify error message
        String errorMessage = lp.getLoginValidationMessage();
        Assert.assertNotNull(errorMessage, "Error message should be displayed");
        Assert.assertTrue(errorMessage.contains("Invalid") || errorMessage.contains("incorrect"), 
            "Error message should indicate invalid credentials");
    }

    /**
     * Test case to verify login with empty credentials.
     * Steps:
     * 1. Leave email empty
     * 2. Leave password empty
     * 3. Click login button
     * 4. Verify validation message is displayed
     * 
     * @throws InterruptedException if thread sleep is interrupted
     */
   // @Test(priority = 3)
    public void testEmptyCredentials() throws InterruptedException {
        // Step 1-3: Try to login with empty fields
        lp.clickLoginButton();
        
        Thread.sleep(1000); // Wait for validation
        
        // Step 4: Verify validation message
        // Note: Adjust based on your app's validation behavior
        // Assert.assertTrue(loginPage.isValidationMessageVisible(), "Validation message should be displayed");
    }
}
