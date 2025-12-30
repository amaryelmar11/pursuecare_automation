package BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import PageObjectClass.Mobile.Login_Page_Mobile;

/**
 * Common utility class for Mobile automation.
 * This class provides common methods for mobile test automation including:
 * - Mobile login functionality
 * - Element interaction methods (click, enterText, getElementText)
 * - Wait utilities for mobile elements
 * 
 * @author Automation Team
 */
public class Common_Utils_Mobile extends MobileBasePageClass {

    public AppiumDriver driver;
    public WebDriverWait wait;

    /**
     * Constructor to initialize Common_Utils_Mobile.
     * 
     * @param driver AppiumDriver instance
     */
    public Common_Utils_Mobile(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Clicks on a mobile WebElement with wait and error handling.
     * 
     * @param element WebElement to click
     */
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Retry with scroll if click is intercepted
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e) {
            // Retry if element is stale
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }

    /**
     * Clicks on a mobile element using By locator with wait and error handling.
     * 
     * @param locator By locator to find the element
     */
    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        }
    }

    /**
     * Enters text into a mobile WebElement with wait and error handling.
     * 
     * @param element WebElement to enter text into
     * @param text Text to enter
     */
    public void enterText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Gets text from a mobile WebElement with wait and error handling.
     * 
     * @param element WebElement to get text from
     * @return Text content of the element
     */
    public String getElementText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            String text = element.getText().trim();
            return text;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Performs mobile login using Login_Page_Mobile page object.
     * Similar to the web login method in Common_Utils.
     * 
     * @param loginPage Login_Page_Mobile page object instance
     * @param username Email/username to login
     * @param password Password to login
     */
    public void login(Login_Page_Mobile loginPage, String username, String password) {
        enterText(loginPage.emailId, username);
        enterText(loginPage.password, password);
        click(loginPage.loginButton);
    }
}

