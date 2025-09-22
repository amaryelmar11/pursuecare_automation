package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;

public class ProviderZoomSessionPage extends BasePageClass{

    
    WebDriver driver;

    public ProviderZoomSessionPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="//button[@class='inline-flex items-center justify-center font-medium rounded-md transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:opacity-50 disabled:cursor-not-allowed bg-red-500 hover:bg-red-600 text-white px-4 py-2 text-white bg-red-500/100 hover:bg-red-600/100'][1]")
	public WebElement clickOnMicIcon;
	

	@FindBy(xpath="//button[@class='inline-flex items-center justify-center font-medium rounded-md transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:opacity-50 disabled:cursor-not-allowed bg-red-500 hover:bg-red-600 text-white px-4 py-2 text-white bg-red-500/100 hover:bg-red-600/100'][2]")
	public WebElement clickOnVideoIcon;
	

	@FindBy(xpath="//div[@id='uikit-preview-vb-button']")
	public WebElement OpenBackgroungImages;
	
	@FindBy(xpath="//img[contains(@src, 'Background1.png')]")
	public WebElement ClickOnBackgroundImage;
	
	@FindBy(xpath="//button[@class='rounded-full p-1 text-theme-text hover:bg-theme-background transition-colors']")
	public WebElement ClickOnCutBackImages;
	
	@FindBy(xpath="//button[normalize-space()='Test speaker']")
	public WebElement clickOnTestSpeaker;
	
	@FindBy(xpath="//button[normalize-space()='Stop']")
	public WebElement clickOnTestSpeaker2;
	
	@FindBy(xpath="//button[normalize-space()='Test microphone']")
	public WebElement clickOnTestMicrophone;
	
	@FindBy(xpath="//button[normalize-space()='Mic recording']")
	public WebElement clickOnTestMicrophone2;
	
	@FindBy(xpath="//button[normalize-space()='Join Session']")
	public WebElement clickOnJoinSession;

    //Inside zoom forward flow

    	
	@FindBy(xpath="//span[@class='text-md pl-1 truncate w-auto']")
	public WebElement getProviderNameFromZoom;
	
	@FindBy(xpath="//button[@id='uikit-join-audio-consent-enable']")
	public WebElement clickonEnableOptionPatient;
	
	@FindBy(xpath="//button[@id='uikit-join-audio-consent-not-now']")
	public WebElement clickonNotNowPatient;

	@FindBy(xpath="//span[@class='text-md pl-1 truncate w-auto'][1]")
	public WebElement getPatientNameAfterJoining;
	
	@FindBy(xpath="//div[@class='max-w-7xl mx-auto flex justify-between items-center']//div//button[@id='leave-button']//*[name()='svg']")
	public WebElement clickonLeaveOption;
	
	@FindBy(xpath="//button[normalize-space()='End Session']")
	public WebElement clickOnEndSession;

    @FindBy(xpath="//button[@id='leave-meeting-button']")
	public WebElement clickOnLeaveSession;
	
	@FindBy(xpath="//select[@formcontrolname='status']")
	public WebElement completeAppDropdown;

	@FindBy(xpath="//div[@id='toast-container']")
	public WebElement CallInterpreterToast;

	@FindBy(xpath="//select[@formcontrolname='callOutDevice']")
	public WebElement ClickInterpreterLang;

	@FindBy(xpath="//button[@type='submit']")
	public WebElement ClickCallInterpreterBtn;

	@FindBy(xpath="//select[@formcontrolname='status']")
	public WebElement StatusSelection;

	@FindBy(xpath="//button[@type='submit']")
	public WebElement SubmitResponse;


}
