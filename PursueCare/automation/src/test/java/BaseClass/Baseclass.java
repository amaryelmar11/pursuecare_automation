package BaseClass;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Baseclass {

    public static WebDriver driver;
    public  Properties p;
    public static org.apache.logging.log4j.Logger logger;
    

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String br) throws IOException
    {
        logger = LogManager.getLogger(this.getClass());
        
        FileReader file = new FileReader("./src//test//resources//config.properties");

        p = new Properties();
        p.load(file);

        switch (br.toLowerCase())
         {
            case "chrome": driver = new ChromeDriver();
                break;
            case "firefox": driver = new FirefoxDriver();
                break;
            case "edge": driver = new EdgeDriver();
                break;
        
            default: System.out.println("Invalid browser");
                return;
        }

        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.get(p.getProperty("appURL_QA"));
    }


    @AfterClass
    public void tearDown()
    {
        driver.close();
    }

    public String captureScreen(String sname) throws IOException
    {
        String timestamp = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss").format(LocalDateTime.now());
        Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "screenshot");
        Files.createDirectories(screenshotsDir);

        String fileName = sname + "_" + timestamp + ".png";
        Path target = screenshotsDir.resolve(fileName);

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Files.write(target, screenshotBytes);
        return target.toString();
    }
       
    
    @BeforeMethod
    public void setUp(Method method) throws InterruptedException {
        if (method.getName().equals("rememberMeChecks")) {
            if (driver != null) driver.quit();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(p.getProperty("appURL_QA"));
            Thread.sleep(3000);
        }
    }
    


}
