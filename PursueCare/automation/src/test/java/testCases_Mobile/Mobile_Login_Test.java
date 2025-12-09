package testCases_Mobile;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.MobileBaseClass;
import BasePage.Common_Utils;
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
    private Login_Page_Mobile loginPage;
    private Common_Utils cu;

    /**
     * Initializes all page objects before test execution.
     */
    @BeforeClass
    public void initPages() {
        loginPage = new Login_Page_Mobile(driver);
        cu = new Common_Utils(driver);
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
    public void testValidLogin() throws InterruptedException {
        // Step 1-3: Perform login
        loginPage.login(p.getProperty("provider1"), p.getProperty("Password1"));
        
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
    @Test(priority = 2)
    public void testInvalidLogin() throws InterruptedException {
        // Step 1-3: Perform login with invalid credentials
        loginPage.login("invalid@email.com", "wrongpassword");
        
        Thread.sleep(2000); // Wait for error message
        
        // Step 4: Verify error message
        String errorMessage = loginPage.getLoginValidationMessage();
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
    @Test(priority = 3)
    public void testEmptyCredentials() throws InterruptedException {
        // Step 1-3: Try to login with empty fields
        loginPage.clickLoginButton();
        
        Thread.sleep(1000); // Wait for validation
        
        // Step 4: Verify validation message
        // Note: Adjust based on your app's validation behavior
        // Assert.assertTrue(loginPage.isValidationMessageVisible(), "Validation message should be displayed");
    }
}
