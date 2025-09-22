package BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import PageObjectClass.LoginPage;

public class Common_Utils extends BasePageClass{

    
public WebDriver driver;
public WebDriverWait wait;

public Common_Utils(WebDriver driver)
 {
    super(driver);
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
 }

 private static final By OVERLAY = By.cssSelector(".ngx-spinner-overlay");

 public void waitForOverlayToDisappear() {
     try {
         // quick wait for presence first (avoids waiting full timeout if not present)
             wait.until(ExpectedConditions.or(
             ExpectedConditions.invisibilityOfElementLocated(OVERLAY),
             ExpectedConditions.numberOfElementsToBe(OVERLAY, 0)
         ));
     } catch (Exception ignored) {
         // overlay might not be present; ignore
     }
 }


 public void click(WebElement element) {
    waitForOverlayToDisappear();
    wait.until(ExpectedConditions.elementToBeClickable(element));
    try {
        element.click();
    } catch (ElementClickInterceptedException e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        waitForOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}

 public void click(By locator) {
    waitForOverlayToDisappear();
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    try {
        element.click();
    } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        waitForOverlayToDisappear();
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
 }

public void enterText(WebElement element, String text) {
    waitForOverlayToDisappear();
    try {
        wait.until(ExpectedConditions.elementToBeClickable(element));
                             // clear existing text
        element.sendKeys(text);               // type new text
    } catch (Exception e) {
        throw e;
    }
}

public String getElementText(WebElement element) {
    waitForOverlayToDisappear();
    try {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText().trim();
        return text;
    } catch (Exception e) {
        throw e;
    }
}



public String selectFromDropdown(WebElement dropdownElement, String selectionType, String value) {
    waitForOverlayToDisappear();
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dropdownElement);
    wait.until(ExpectedConditions.visibilityOf(dropdownElement));

    Select select = new Select(dropdownElement);
    String trimmedValue = value == null ? null : value.trim();

    switch (selectionType.toLowerCase()) {
        case "text":
            try {
                // Try exact first
                select.selectByVisibleText(value);
            } catch (NoSuchElementException e1) {
                // Try trimmed text
                try {
                    select.selectByVisibleText(trimmedValue);
                } catch (NoSuchElementException e2) {
                    // Fallback: iterate and match trimmed
                    List<WebElement> options = select.getOptions();
                    boolean matched = false;
                    for (WebElement option : options) {
                        String optionText = option.getText() == null ? "" : option.getText().trim();
                        if (optionText.equals(trimmedValue)) {
                            option.click();
                            matched = true;
                            break;
                        }
                    }
                    if (!matched) throw e2;
                }
            }
            break;
        case "value":
            select.selectByValue(value);
            break;
        case "index":
            select.selectByIndex(Integer.parseInt(value));
            break;
        default:
            throw new IllegalArgumentException("Invalid selection type: " + selectionType);
    }

    return select.getFirstSelectedOption().getText().trim();
}


public void doubleClickElement(WebDriver driver, WebElement element) {
    Actions actions = new Actions(driver);
    actions.doubleClick(element).perform();
}



public void logout(LoginPage loginPage) {
    click(loginPage.clickonImageforLogout);
    click(loginPage.clickonLogout);
}

public void login(LoginPage loginPage, String username, String password) {
    enterText(loginPage.emailId, username);
    enterText(loginPage.password, password);
    click(loginPage.Loginbtn);
}

public void pressEscape() {
    Actions actions = new Actions(driver);
    actions.sendKeys(Keys.ESCAPE).perform();
}

//Click On Join Zoom Session


public void clickJoinMeetingprovider(String startTime) {
    List<WebElement> rows = driver.findElements(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr"));
    System.out.println(rows.size());

    boolean app = false;
    for (int i = 1; i <= rows.size(); i++) {
        String value = driver
                .findElement(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr[" + i + "]/td[9][1]"))
                .getText();

        if (value.contains(startTime)) {
            driver.findElement(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr[" + i + "]/td[12]"))
                    .click();
            app = true;
            Assert.assertTrue(app);
            break;
        }

    }

}


public void clickJoinMeetingPatient(String startTime) {
    List<WebElement> rows = driver.findElements(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr"));
    System.out.println(rows.size());

    boolean app = false;
    for (int i = 1; i <= rows.size(); i++) {
        String value = driver
                .findElement(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr[" + i + "]/td[4]"))
                .getText();
        System.out.println(value);
        if (value.contains(startTime)) {
            driver.findElement(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr[" + i + "]/td[7]"))
                    .click();
            app = true;
            Assert.assertTrue(app);
            break;
        }

    }

}

}



