package BasePage;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base Page Class for Mobile Page Objects.
 * This class provides initialization for mobile page objects using PageFactory.
 * 
 * @author Automation Team
 */
public class MobileBasePageClass {

    AppiumDriver driver;

    /**
     * Constructor to initialize the MobileBasePageClass.
     * 
     * @param driver AppiumDriver instance
     */
    public MobileBasePageClass(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

