package BasePage;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.openqa.selenium.NoSuchElementException;

import PageObjectClass.LSEHR_Page;
import PageObjectClass.LoginPage;
import utilities.TableSortUtils;

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

public void loginLS(LSEHR_Page LSPage, String username, String password) {
    enterText(LSPage.EnterLsMailID, username);
    enterText(LSPage.EnterLsPassword, password);
    click(LSPage.ClickSignInBtn);
}

public void pressEscape() {
    Actions actions = new Actions(driver);
    actions.sendKeys(Keys.ESCAPE).perform();
}

public boolean isElementClickable(WebDriver driver, WebElement element) {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return true;   // element is clickable
    } catch (Exception e) {
        return false;  // element is NOT clickable
    }
}

//Click On Join Zoom Session


public void clickJoinMeetingprovider(String startTime) {
    List<WebElement> rows = driver.findElements(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr"));
    System.out.println(rows.size());

    boolean app = false;
    for (int i = 1; i <= rows.size(); i++) {
        String value = driver
                .findElement(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr[" + i + "]/td[8][1]"))
                .getText();

        if (value.contains(startTime)) {
            driver.findElement(By.xpath("//div[@class='table-responsive'][1]//table[1]/tbody/tr[" + i + "]/td[11]/table/td/button"))
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

public static boolean isColumnSorted(List<WebElement> columnElements, String order) {
    // Extract text from column elements
    List<String> actualList = columnElements.stream()
            .map(e -> e.getText().trim())
            .filter(s -> !s.isEmpty()) // remove blanks
            .collect(Collectors.toList());

    // Make a copy for sorting
    List<String> sortedList = new ArrayList<>(actualList);

    // Try numeric sorting first, if fails fallback to string sorting
    try {
        List<Double> actualNumbers = actualList.stream()
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        List<Double> sortedNumbers = new ArrayList<>(actualNumbers);

        if (order.equalsIgnoreCase("asc")) {
            Collections.sort(sortedNumbers);
        } else {
            Collections.sort(sortedNumbers, Collections.reverseOrder());
        }
        return actualNumbers.equals(sortedNumbers);

    } catch (NumberFormatException e) {
        // Non-numeric → sort alphabetically
        if (order.equalsIgnoreCase("asc")) {
            Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        } else {
            Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER.reversed());
        }
        return actualList.equals(sortedList);
    }
}

public  String getRandomDOB() {
    // Define year range for realistic DOBs (ages 18–75)
    int startYear = 1950;
    int endYear = 2005;

    // Generate random year, month, and day
    int randomYear = ThreadLocalRandom.current().nextInt(startYear, endYear + 1);
    int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);   // 1–12
    int randomDay = ThreadLocalRandom.current().nextInt(1, 29);     // 1–28 (safe for all months)

    // Set the date
    Calendar calendar = Calendar.getInstance();
    calendar.set(randomYear, randomMonth - 1, randomDay);
    Date randomDate = calendar.getTime();

    // Format as M/d/yyyy (e.g., 3/7/1998)
    SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
    return formatter.format(randomDate);
}

public String getTodayDate() {
    Date today = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
    return formatter.format(today);
}

// Validate if the date string matches the current date
// Example: dateString = "12-20-2025 6:01 AM" -> checks if date part (12-20-2025) matches today
public boolean isDateCurrentDate(String dateString) {
    try {
        if (dateString == null || dateString.trim().isEmpty()) {
            return false;
        }
        
        // Extract date part before the time (split by space and take first part)
        // "12-20-2025 6:01 AM" -> "12-20-2025"
        String datePart = dateString.trim().split("\\s+")[0];
        
        // Try multiple date formats to handle variations
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),      // "12-20-2025"
            DateTimeFormatter.ofPattern("M-dd-yyyy"),       // "1-20-2025"
            DateTimeFormatter.ofPattern("MM-d-yyyy"),       // "12-1-2025"
            DateTimeFormatter.ofPattern("M-d-yyyy")         // "1-1-2025"
        };
        
        LocalDate parsedDate = null;
        
        // Try to parse with different formatters
        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDate = LocalDate.parse(datePart, formatter);
                break;
            } catch (Exception e) {
                // Try next formatter
            }
        }
        
        if (parsedDate == null) {
            System.err.println("Failed to parse date: " + dateString);
            return false;
        }
        
        // Get current date
        LocalDate currentDate = LocalDate.now();
        
        // Compare dates
        boolean isCurrentDate = parsedDate.equals(currentDate);
        
        System.out.println("Date from Element: " + parsedDate);
        System.out.println("Current Date: " + currentDate);
        System.out.println("Is Current Date: " + isCurrentDate);
        
        return isCurrentDate;
        
    } catch (Exception e) {
        System.err.println("Error validating date: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

// Method for sorting logic

public List<WebElement> getColumnElements(int index) {
    String xpath = "//tbody/tr[position()>=1]/td["+index+"]";
    return driver.findElements(By.xpath(xpath));
}



public void clickcolumnheader(int index)
{
    WebElement header = driver.findElement(By.xpath("//thead/tr/th[" + index + "]"));
    header.click();
}

// Common method for column sorting checks (ascending and descending)
// Handles clicking column header, waiting, getting elements, and asserting sort order
public void checkColumnSorting(int columnIndex) throws InterruptedException {
    // Click column header for ascending sort
    Thread.sleep(2000);
    clickcolumnheader(columnIndex);
    Thread.sleep(3000);
    List<WebElement> colAsc = getColumnElements(columnIndex);
    Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(colAsc, "asc"), 
        "Column " + columnIndex + " is not sorted in ascending order");

    // Click column header again for descending sort
    Thread.sleep(2000);
    clickcolumnheader(columnIndex);
    Thread.sleep(3000);
    List<WebElement> colDesc = getColumnElements(columnIndex);
    Assert.assertTrue(TableSortUtils.isColumnSortedUniversal(colDesc, "desc"), 
        "Column " + columnIndex + " is not sorted in descending order");
}

// Dashboard Table Sorting Logic

public List<WebElement> getDashColumnElements(String tableId, int index) {
    String xpath = "//table[@id='" + tableId + "']/tbody/tr[position()>=1]/td[" + index + "]";
    return driver.findElements(By.xpath(xpath));
}

public void clickDashTableColumnHeader(String tableId, int index)
{
    String xpath = "//table[@id='" + tableId + "']/thead/tr/th[" + index + "]";
    click(By.xpath(xpath));
}

public String getCurrentDay() {
    return java.time.LocalDate.now()
            .getDayOfWeek()
            .toString()
            .substring(0,1).toUpperCase() 
            + java.time.LocalDate.now()
                .getDayOfWeek()
                .toString()
                .substring(1).toLowerCase();
}

// Get day index for schedule navigation (Sunday=1, Monday=2, ..., Saturday=7)
public int getCurrentDayIndex() {
    java.time.DayOfWeek dayOfWeek = java.time.LocalDate.now().getDayOfWeek();
    // DayOfWeek enum: MONDAY=1, TUESDAY=2, ..., SUNDAY=7
    // We need: Sunday=1, Monday=2, ..., Saturday=7
    int dayValue = dayOfWeek.getValue(); // Monday=1, Tuesday=2, ..., Sunday=7
    // Convert: Monday(1) -> 2, Tuesday(2) -> 3, ..., Sunday(7) -> 1
    return dayValue == 7 ? 1 : dayValue + 1;
}

// Get schedule navigation element for current day
public WebElement getScheduleNavCurrentDay() {
    int index = getCurrentDayIndex();
    String xpath = "//div[@class='schedule-nav']/ul/li[" + index + "]/a";
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    return driver.findElement(By.xpath(xpath));
}

// Click schedule navigation element for current day
public void clickScheduleNavCurrentDay() {
    WebElement element = getScheduleNavCurrentDay();
    click(element);
}

// Check if the passed time range is between 07:00 AM to 08:00 AM
// Example: timeRange = "07:00 AM - 08:00 AM" returns true
// Returns true if the time range falls within 07:00 AM to 08:00 AM
public boolean isTimeRangeInCurrentHour(String timeRange) {
    try {
        // Parse the time range (e.g., "07:00 AM - 08:00 AM")
        String[] parts = timeRange.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid time range format. Expected: 'HH:MM AM/PM - HH:MM AM/PM'");
        }
        
        String startTimeStr = parts[0].trim();
        String endTimeStr = parts[1].trim();
        
        // Parse start and end times from the range
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime rangeStart = LocalTime.parse(startTimeStr, formatter);
        LocalTime rangeEnd = LocalTime.parse(endTimeStr, formatter);
        
        // Define the expected time range 07:00 AM to 08:00 AM
        LocalTime expectedStart = LocalTime.of(7, 0); // 07:00 AM
        LocalTime expectedEnd = LocalTime.of(8, 0);   // 08:00 AM
        
        // Check if the passed time range is between 07:00 AM and 08:00 AM
        // Range start should be >= 07:00 AM AND range end should be <= 08:00 AM
        boolean isBetweenRange = !rangeStart.isBefore(expectedStart) && !rangeEnd.isAfter(expectedEnd);
        
        System.out.println("Time Range from Element: " + timeRange);
        System.out.println("Parsed Start Time: " + rangeStart);
        System.out.println("Parsed End Time: " + rangeEnd);
        System.out.println("Expected Range: " + expectedStart + " - " + expectedEnd);
        System.out.println("Is Time Range between 07:00 AM - 08:00 AM: " + isBetweenRange);
        
        return isBetweenRange;
        
    } catch (Exception e) {
        System.err.println("Error checking time range: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

// Check if the passed time range falls within the current hour (in EST)
// Example: timeRange = "09:00 AM - 10:00 AM", current time = 9:30 AM EST
// Returns true if the time range (09:00 AM - 10:00 AM) is within the current hour (9:00 AM - 10:00 AM)
public boolean isTimeRangeInCurrentHournew(String timeRange) {
    try {
        if (timeRange == null || timeRange.trim().isEmpty()) {
            return false;
        }

        String[] parts = timeRange.split("-");
        if (parts.length != 2) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        LocalTime rangeStart = LocalTime.parse(parts[0].trim(), formatter);
        LocalTime rangeEnd   = LocalTime.parse(parts[1].trim(), formatter);

        ZoneId estZone = ZoneId.of("America/New_York");
        LocalTime now = ZonedDateTime.now(estZone).toLocalTime();

        LocalTime hourStart = now.truncatedTo(ChronoUnit.HOURS);
        LocalTime hourEnd   = hourStart.plusHours(1);

        boolean overlaps =
                rangeStart.isBefore(hourEnd) &&
                rangeEnd.isAfter(hourStart);

        System.out.println("Time Range: " + rangeStart + " - " + rangeEnd);
        System.out.println("Current Hour: " + hourStart + " - " + hourEnd);
        System.out.println("Overlaps Current Hour: " + overlaps);

        return overlaps;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


}



