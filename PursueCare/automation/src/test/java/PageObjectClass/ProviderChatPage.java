package PageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BasePageClass;
import BasePage.Common_Utils;

public class ProviderChatPage extends BasePageClass{

    WebDriver driver;
    private Common_Utils cu;
    public ProviderChatPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
       
    }
    // Chat Related

    @FindBy(xpath="//div[@class='ng-scroll-content'][1]/ul/li[6]")
    public WebElement ProviderChatOptDash;

    @FindBy(xpath="//div[@class='card selected-color']/ul/li[3]/a")
    public WebElement clickPatienttab;

    @FindBy(xpath="//input[@placeholder='Search Patient...']")
    public WebElement searchPatientInput;

    @FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[1]/div/div[2]")
	public WebElement clickonSearchedPatient;

    @FindBy(xpath="//textarea[@formcontrolname='chatmessage']")
	public WebElement enterTextonInputMessage;

    @FindBy(xpath="//div[@class='col-lg-1 col-md-2 col p-l-0 align-self-end m-b-30 send-btn ng-star-inserted']/button")
	public WebElement clickSendMsgBtn;


    //Patient Chat Option

    @FindBy(xpath="//div[@class='ng-scroll-content']/ul/li[3]")
	public WebElement clickPatientChatOpt;
	
	@FindBy(xpath="//div[@class='card selected-color']/ul/li[2]")
	public WebElement clickProviderPatLogin;
	
	@FindBy(xpath="//input[@placeholder='Search Provider...']")
	public WebElement searchProviderPatLogin;
	
	@FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[1]/div/div[2]")
	public WebElement clickOnProviderPatLogin;
	
    @FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[1]/div/div[1]/span/span")
	public WebElement getPatientMsgBadgeCount;
	

    // Multimedia/Attachment controls
    @FindBy(xpath="//button[@data-toggle='dropdown']")
    public WebElement AttachMultimedia;

    @FindBy(xpath="//span[normalize-space()='Open Webcam']")
    public WebElement OpenWebCamOption;

    @FindBy(xpath="//span[normalize-space()='Upload File(s)']")
    public WebElement UploadFilesOption;

    @FindBy(xpath="//span[normalize-space()='Open Library']")
    public WebElement OpenLibraryOption;

    @FindBy(xpath="//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary captureImageButton ng-star-inserted']")
    public WebElement CpatureAImageBtn;

    @FindBy(xpath="//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary captureImageButton']")
    public WebElement UploadACaptureImageBtn;

    @FindBy(xpath="//span[normalize-space()='Text']")
    public WebElement MultimediaLibraryTextOption;

    @FindBy(xpath="//app-library-modal[@class='mat-mdc-dialog-component-host ng-star-inserted']/div/div[3]/div/div/div/div/div/p")
    public WebElement selectTextOptionContent;


    @FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[3]")
	public WebElement msgProviderOrPatientInMiddle;

    @FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[1]")
	public WebElement msgProviderOrPatientAtTop;

    @FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[3]/div/div/div/p[1]")
	public WebElement getmsgProviderOrPatientAtMiddle;


    @FindBy(xpath="//ul[@class='chat-list list-unstyled m-b-0']/li[1]/div/div/div/p[1]")
	public WebElement getmsgProviderOrPatientInTop;
    // Method to send message
    public void sendMessage(String message)
    {
        cu = new Common_Utils(driver);
        cu.enterText(enterTextonInputMessage, message);
        cu.click(clickSendMsgBtn);
    }

}
