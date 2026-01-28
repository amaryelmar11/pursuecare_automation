package BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import PageObjectClass.Mobile.Login_Page_Mobile;
import java.util.List;

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
     * Optimized to reduce wait times and handle stale elements properly.
     * 
     * @param element WebElement to click
     */
    public void click(WebElement element) {
        int maxRetries = 2;
        int retryCount = 0;
        
        while (retryCount <= maxRetries) {
            try {
                // Use shorter wait time (5 seconds instead of 20)
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                shortWait.until(ExpectedConditions.elementToBeClickable(element));
                
                // Try to click
                element.click();
                return; // Success, exit method
                
            } catch (StaleElementReferenceException e) {
                // Element is stale, need to re-find it
                retryCount++;
                if (retryCount > maxRetries) {
                    throw new RuntimeException("Element became stale after " + maxRetries + " retries", e);
                }
                // Wait a bit before retrying
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                // Note: For stale elements, the caller should re-find the element
                // This is a limitation when using PageFactory elements
                continue;
                
            } catch (ElementClickInterceptedException e) {
                // Element is not clickable, try scroll and tap
                retryCount++;
                if (retryCount > maxRetries) {
                    // Last resort: use TouchAction to tap at element location
                    try {
                        int x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                        int y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                        
                        if (driver instanceof AndroidDriver) {
                            ((AndroidDriver) driver).executeScript("mobile: tap", 
                                java.util.Map.of("x", x, "y", y));
                        } else {
                            throw new RuntimeException("Element click intercepted and retries exhausted", e);
                        }
                        return;
                    } catch (Exception ex) {
                        throw new RuntimeException("Element click intercepted and all retry methods failed", e);
                    }
                }
                // Wait and retry
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                continue;
                
            } catch (Exception e) {
                // Other exceptions - throw immediately
                throw new RuntimeException("Failed to click element after retries", e);
            }
        }
    }

    /**
     * Clicks on a mobile element using By locator with wait and error handling.
     * This method is more reliable than WebElement click as it re-finds the element.
     * 
     * @param locator By locator to find the element
     */
    public void click(By locator) {
        int maxRetries = 2;
        int retryCount = 0;
        
        while (retryCount <= maxRetries) {
            try {
                // Use shorter wait time (5 seconds instead of 20)
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement element = shortWait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return; // Success, exit method
                
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                if (retryCount > maxRetries) {
                    // Last resort: try tap action for Android
                    try {
                        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                        int x = element.getLocation().getX() + element.getSize().getWidth() / 2;
                        int y = element.getLocation().getY() + element.getSize().getHeight() / 2;
                        
                        if (driver instanceof AndroidDriver) {
                            ((AndroidDriver) driver).executeScript("mobile: tap", 
                                java.util.Map.of("x", x, "y", y));
                            return;
                        }
                    } catch (Exception ex) {
                        // Fall through to throw original exception
                    }
                    throw new RuntimeException("Failed to click element after " + maxRetries + " retries", e);
                }
                // Wait before retry
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                continue;
            }
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

    /**
     * Performs mobile logout using Login_Page_Mobile page object.
     * Clicks on the breadcrumb menu and then clicks Sign Out.
     * 
     * @param loginPage Login_Page_Mobile page object instance
     */
    public void logout(Login_Page_Mobile loginPage) {
        click(loginPage.BreadcrumbMenu);
        click(loginPage.SignOut);
    }

    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }

    public void TouchAction(WebElement element, AppiumDriver driver) {
        AndroidTouchAction touchAction = new AndroidTouchAction((AndroidDriver) driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
    }
}

