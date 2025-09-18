package BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageClass {

    WebDriver driver;

    public BasePageClass(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
