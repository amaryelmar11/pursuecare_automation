package PageObjectClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BasePageClass;

public class ProviderAppointmentsPage extends BasePageClass{

    WebDriver driver;

    public ProviderAppointmentsPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }


    @FindBy(xpath = "//span[normalize-space()='Appointment']")
	public WebElement selectAppointmentDash;

    @FindBy(xpath = "//mat-select[@formcontrolname='athenaApptType']")
	public WebElement selectAppointment;

	@FindBy(xpath = "//span[normalize-space()='FOLLOW UP 15 | Therapy']")
	public WebElement selectAppointmentType;

	@FindBy(xpath = "//mat-label[normalize-space()='Provider']")
	public WebElement selectProvider;

	@FindBy(xpath = "//span[normalize-space()='Jade Wise']")
	public WebElement selectProviderCheckbox;

	@FindBy(xpath = "//mat-label[normalize-space()='Host']")
	public WebElement selectHost;

   

	@FindBy(xpath = "//mat-select[@formcontrolname='patientNameControl']")
	public WebElement selectPatient;

	@FindBy(xpath = "//input[@placeholder='Search patients...']")
	public WebElement inputPatientName;

	@FindBy(xpath = "//div[@class='cdk-overlay-pane']/div/mat-option[2]")
	public WebElement setVisiblePatient;

	@FindBy(xpath = "//input[@placeholder='Slot start Time']")
	public WebElement selectStartTime;

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[4]/div[1]/owl-date-time-container[1]/div[2]/div[1]/button[2]/span[1]")
	public WebElement selectSetStartTime;

	@FindBy(xpath = "//input[@placeholder='Slot end Time']")
	public WebElement selectEndTime;

	@FindBy(xpath = "//button[@aria-label='Add a minute']//span[@class='owl-dt-control-button-content']")
	public WebElement selectStartTimeInRange11Pmto12Am;

	@FindBy(xpath = "//button[@aria-label='Add a hour']//span[@class='owl-dt-control-button-content']")
	public WebElement selectAddhour;

	@FindBy(xpath = "//span[normalize-space()='Set']")
	public WebElement selectSetEndTime;

	@FindBy(xpath = "//span[normalize-space()='Save']") //// span[normalize-space()='Save']
	public WebElement selectSaveButton;

	@FindBy(xpath = "//span[normalize-space()='Continue anyway']") //// span[normalize-space()='Save']
	public WebElement selectContinueAnywayButton;


	// Delete Provider Appointments

	@FindBy(xpath = "//td[contains(@class, 'fc-day-today fc-daygrid-day')]/div/div[2]/div/a/div")
	public WebElement SelectAppointmentForDeletion;

	@FindBy(xpath = "//mat-icon[normalize-space()='delete']/ancestor::button")
	public WebElement DeleteProviderApp;

	@FindBy(xpath = "//span[normalize-space()='Delete']")
	public WebElement ClickOnDeleteBtnPopup;

	@FindBy(xpath = "//a[normalize-space()='Appointments']")
    public WebElement clickAppointments;
    
	@FindBy(xpath="//span[normalize-space()='Care Team']")
	public WebElement clickCareTeamDash;
	
	@FindBy(xpath="//mat-label[normalize-space()='Select Provider']")
	public WebElement SelectProviderDropdown;
	
	@FindBy(xpath="//span[normalize-space()='Sid Automation4']")
	public WebElement SelectProviderFromDrpdwn;

	@FindBy(xpath="//span[normalize-space()='automation zoom']")
	public WebElement SelectProviderZoomEndToEndFromDrpdwn;

	@FindBy(xpath="//span[normalize-space()='automation zoom1']")
	public WebElement SelectProviderSessionChecksFromDrpdwn;

	@FindBy(xpath="//span[normalize-space()='sid Automation5']")
	public WebElement SelectProviderTimeBlockFromDrpdwn;	

	// Calendar Filter Related Xpaths
	@FindBy(xpath = "//div[@class='icon-button-demo']/button")
	public WebElement ClickOnScheduleBtn;

	@FindBy(xpath = "//span[normalize-space()='sid Automation5']")
	public WebElement SelectProviderFilterChk1;

	@FindBy(xpath = "//span[normalize-space()='Grandy Sand']")
	public WebElement SelectProviderFilterChk2;

	@FindBy(xpath = "//mat-select[@formcontrolname='providerlist']/div/div/span/span")
	public WebElement GetProviderName;

	@FindBy(xpath = "//div[contains(@class, 'd-flex align-content-start flex-wrap header-buttons-left')][1]/div[2]/mat-form-field/div/div/div/mat-select")
	public WebElement SelectPASDrpDwn;

	@FindBy(xpath = "//span[normalize-space()='Saavi intake C']")
	public WebElement SelectPASFilterChk1;

	@FindBy(xpath = "//span[normalize-space()='Sharlin Choiu']")
	public WebElement SelectPASFilterChk2;

	@FindBy(xpath = "//mat-select[@formcontrolname='intakelist']/div/div/span/span")
	public WebElement GetIntakename;

	@FindBy(xpath = "//div[contains(@class, 'd-flex align-content-start flex-wrap header-buttons-left')][1]/div[3]/mat-form-field/div/div/div/mat-select")
	public WebElement SelectCMDrpDwn;

	@FindBy(xpath = "//span[normalize-space()='Akshay Avhad']")
	public WebElement SelectCMFilterChk1;

	@FindBy(xpath = "//span[normalize-space()='Amar Care']")
	public WebElement SelectCMFilterChk2;

	@FindBy(xpath = "//mat-select[@formcontrolname='cclist']/div/div/span/span")
	public WebElement GetCMName;

	@FindBy(xpath = "//mat-icon[normalize-space()='refresh']")
	public WebElement ClickOnRefreshCalendarFilter;

	@FindBy(xpath = "//button[@aria-label='Close dialog']")
	public WebElement ClickOnCloseSchedPopup;

	@FindBy(xpath = "//mat-label[normalize-space()='Patient Access Specialist']")
	public WebElement EmptyPASMultipleSelection;

	@FindBy(xpath = "//mat-label[normalize-space()='Case Manager']")
	public WebElement EmptyCMMultipleSelection;

	@FindBy(xpath = "//mat-label[normalize-space()='Provider']")
	public WebElement EmptyProviderMultipleSelection;

	// Availabe Section Calendar Checks
	@FindBy(xpath = "//span[normalize-space()='Available']")
	public WebElement AvailableSectionClick;

	// Recurring CheckBox
	@FindBy(xpath = "//label[normalize-space()='Recurring Meeting']")
    public WebElement clickRecurringCheckbox;

	@FindBy(xpath = "//mat-select[@formcontrolname='meetingtypeid']")
    public WebElement clickRepeatDrpDwn;

	@FindBy(xpath = "//span[normalize-space()='Daily']")
    public WebElement clickDailyRepeatDayOption;

	@FindBy(xpath = "//input[@formcontrolname='recurringenddate']")
    public WebElement clickOnEndDateIcon;

	@FindBy(xpath="//span[normalize-space()='SidAutomation Account']")
	public WebElement SelectRecProviderFromDrpdwn;

	@FindBy(xpath="//span[normalize-space()='automation daily']")
	public WebElement SelectRec1ProviderFromDrpdwn;

	@FindBy(xpath="//span[normalize-space()='provider weekly']")
	public WebElement SelectRec2ProviderFromDrpdwn;


	@FindBy(xpath="//label[normalize-space()='Delete All Recurring Meetings']")
	public WebElement ClickAllRecurringMeetingChkbx;

	// Double booking appointment related bcs need to click on schedule appointment second time

	@FindBy(xpath="//span[normalize-space()='Schedule Appointment']")
	public WebElement ClickScheduleAppointmentCalendarBtn;

	@FindBy(xpath="//mat-label[normalize-space()='Appointment Date']")
	public WebElement ClickAppointmentDate;


	//PAS & CM Appointment Booking Xpaths
	@FindBy(xpath="//a[normalize-space()='Appointments']")
	public WebElement clickAppointmentsPASCM;
	@FindBy(xpath="//span[@class='mat-mdc-select-placeholder mat-mdc-select-min-line ng-tns-c1771602899-59 ng-star-inserted']")
	public WebElement clickpatientsearchASCM;

	

	//Schedule Popup Validations

	@FindBy(xpath="//strong[normalize-space()='Patient name is required.']")
	public WebElement PatientValidation;
	
	@FindBy(xpath="//strong[normalize-space()='Host is required.']")
	public WebElement HostValidation;
	
	
	@FindBy(xpath="//strong[normalize-space()='Select Available Time Slots']")
	public WebElement TimeSlotValidation;

	@FindBy(xpath="//strong[normalize-space()='Appointment type is required.']")
	public WebElement AppointmentTypeValidation;



		//Double Booking
		@FindBy(xpath = "//span[normalize-space()='Book Appointment']")
		public WebElement selectDoubleBook;

    public WebElement getTodayAppXpath() {
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));
		String xpath = "//td[normalize-space()='" + currentDate + "' and contains(@class, 'fc-day-today')]";
		return driver.findElement(By.xpath(xpath));
	}

	//today xpath CM & PAS
	public WebElement getTodayAppXpathCM_PAS() {

	
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
		String xpath = "//td[@data-date='" + currentDate + "' and contains(@class, 'fc-day-today')]";
	
		return driver.findElement(By.xpath(xpath));
	
	}
	
	 


	// Clicking on the available Section Of the Calendar

	public void clikcOnAvaliableSection() {
		List<WebElement> elements = driver.findElements(By.xpath(
				"//td[contains(@class, 'fc-day-today')]//div/div/div"));
	
		LocalTime now = LocalTime.now();
		System.out.println("Current time: " + now);
	
		for (WebElement element : elements) {
			try {
				String text = element.getText().trim();
				System.out.println("Slot text: " + text);
	
				// Skip booked slots
				if (text.toLowerCase().contains("booked")) {
					System.out.println("Skipping booked slot.");
					continue;
				}
	
				if (text.toLowerCase().contains("available")) {
					// Remove "Available"
					String timeText = text.replaceAll("(?i)Available", "").trim();
	
					// Extract start time from "7:15 AM - 7:30 AM"
					String startTimeStr = timeText.split("-")[0].trim();
	
					// Clean up: keep only characters that form a valid time
					startTimeStr = startTimeStr.replaceAll("[^0-9:AMPamp ]", "").trim();
					System.out.println("Cleaned start time: " + startTimeStr);
	
					// Parse into LocalTime
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
					LocalTime slotStartTime = LocalTime.parse(startTimeStr, formatter);
	
					// Compare with current system time
					if (!slotStartTime.isBefore(now)) {
						System.out.println("Clicking slot at: " + slotStartTime);
						try {
							element.click();
						} catch (Exception e) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
						}
						return; // exit after successful click
					} else {
						System.out.println("Skipping past slot: " + slotStartTime);
					}
				}
			} catch (Exception e) {
				System.out.println("Error processing slot: " + e.getMessage());
			}
		}
	
		System.out.println("No valid available slots found today.");
	}



	// Recurring Appointment Booking

	public void selectNextRangeSlot() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("h:mm a");

        List<WebElement> slots = driver.findElements(By.xpath("//div[@role='presentation']/div"));
        for (WebElement slot : slots) {
            String text = slot.getText(); // "7:00 AM - 7:30 AM"
            String[] parts = text.split("-");
            LocalTime end = LocalTime.parse(parts[1].trim(), fmt);

            if (!end.isBefore(now)) {
				System.out.println("Clicked on slot: " + text);
                slot.click();				
                return;
            }
        }
        throw new IllegalStateException("No slots available after now");
    }

public void clickDateAfterXDays(WebDriver driver, int daysToAdd, String datePattern) {
    // Step 1: Calculate target date
    LocalDate targetDate = LocalDate.now().plusDays(daysToAdd);

    // Step 2: Format using the given pattern (e.g., "MMMM d, yyyy")
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
    String dateToClick = targetDate.format(formatter);

    // Step 3: Build dynamic XPath
    String xpath = "//td[@aria-label='" + dateToClick + "']";

    // Step 4: Locate and click
    WebElement element = driver.findElement(By.xpath(xpath));
    element.click();

    System.out.println("Clicked on date: " + dateToClick);
}

public void BlockclickDateAfterXDays(WebDriver driver, int daysToAdd, String datePattern) {
    // Step 1: Calculate target date
    LocalDate targetDate = LocalDate.now().plusDays(daysToAdd);

    // Step 2: Format using the given pattern (e.g., "MMMM d, yyyy")
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
    String dateToClick = targetDate.format(formatter);
	System.out.println(dateToClick);
    // Step 3: Build dynamic XPath
    String xpath = "//button[@aria-label='" + dateToClick + "']";
	System.out.println(xpath);
    // Step 4: Locate and click
    WebElement element = driver.findElement(By.xpath(xpath));
    element.click();

    System.out.println("Clicked on date: " + dateToClick);
}
	
	

	public void clickThirdDayAfterToday() {
        List<WebElement> days = driver.findElements(By.xpath("//tbody[@class='owl-dt-calendar-body']/tr/td"));
        System.out.println("Total days: " + days.size());

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String formattedDate = today.format(formatter);
        System.out.println("Today's formatted date: " + formattedDate);

        int todayIndex = -1;

        for (int i = 1, tr = 1, td = 1; i <= days.size(); i++, td++) {
            if (td > 7) {
                td = 1;
                tr++;
            }

            WebElement day = driver.findElement(By.xpath("//tbody[@class='owl-dt-calendar-body']/tr[" + tr + "]/td[" + td + "]"));
            String ariaLabel = day.getAttribute("aria-label");
            System.out.println("Checking: " + ariaLabel);

            if (formattedDate.equals(ariaLabel)) {
                todayIndex = i;
                System.out.println("Found today at index: " + i);
                break;
            }
        }

        if (todayIndex == -1) {
            throw new RuntimeException("Cannot find today.");
        }

        int thirdDayIndex = todayIndex + 3;
        if (thirdDayIndex > days.size()) {
            throw new RuntimeException("3rd day after today not available in calendar.");
        }

        // Calculate tr and td for 3rd day
        int targetTr = (thirdDayIndex - 1) / 7 + 1;
        int targetTd = (thirdDayIndex - 1) % 7 + 1;

        WebElement targetDay = driver.findElement(By.xpath("//tbody[@class='owl-dt-calendar-body']/tr[" + targetTr + "]/td[" + targetTd + "]"));
        if (targetDay.isEnabled()) {
            targetDay.click();
        } else {
            throw new RuntimeException("Target date is not clickable.");
        }
    }

	public void clickAvailableTimeSlots()
	{
		List<WebElement> slots = driver.findElements(By.xpath("//td[contains(@class,'fc-day-today')]//div/div/div"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a"); 
    LocalTime now = LocalTime.now();

    for (WebElement slot : slots) {
        String text = slot.getText().trim();
        System.out.println("Slot text: " + text);

        if (text.contains("Available") && text.contains("-")) {
            try {
                // Extract the end time part after '-'
                String[] parts = text.split("-");
                String endTimeStr = parts[1].trim(); // e.g. "7:15 AM"

                LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

                if (endTime.isAfter(now)) {
                    System.out.println("Clicking slot: " + text);
                    slot.click();
                    break; // stop after clicking first valid slot
                }
            } catch (Exception e) {
                System.out.println("Could not parse time for slot: " + text);
            }
        }
    }
	}



public void clickOnBookedSlot() {
    // Adjust locator if needed for your slot structure
    List<WebElement> slots = driver.findElements(By.xpath("//td[contains(@class,'fc-day-today')]//div/div/div"));

    for (WebElement slot : slots) {
        String text = slot.getText().trim();
        System.out.println("Slot text: " + text);

        if (text.contains("Booked")) {
            System.out.println("Clicking booked slot: " + text);
            slot.click();
            break; // stop after clicking first booked slot
        }
    }
}

}
