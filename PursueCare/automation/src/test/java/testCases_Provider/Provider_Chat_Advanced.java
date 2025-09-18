package testCases_Provider;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Baseclass;
import BasePage.Common_Utils;
import PageObjectClass.LoginPage;
import PageObjectClass.ProviderChatPage;

public class Provider_Chat_Advanced extends Baseclass{

    private LoginPage lp;
    private Common_Utils cu;
    private ProviderChatPage pc;


    @BeforeClass
    public void initPages() {
        lp = new LoginPage(driver);
        cu = new Common_Utils(driver);
        pc = new ProviderChatPage(driver);
    }

    @Test(priority = 1)
    public void MultimediaOptionInChat()
    {
        cu.login(lp, p.getProperty("ProviderChat"), p.getProperty("PasswordChat"));

        cu.click(pc.ProviderChatOptDash);
        cu.click(pc.clickPatienttab);
        cu.enterText(pc.searchPatientInput, p.getProperty("patient1"));
        cu.click(pc.clickonSearchedPatient);

        cu.click(pc.AttachMultimedia);
        cu.click(pc.OpenWebCamOption);
        cu.click(pc.CpatureAImageBtn);
        cu.click(pc.UploadACaptureImageBtn);
        cu.click(pc.clickSendMsgBtn);

        //Open Library Option Checks

        cu.click(pc.AttachMultimedia);
        cu.click(pc.OpenLibraryOption);
        cu.click(pc.MultimediaLibraryTextOption);
        cu.click(pc.selectTextOptionContent);
        cu.click(pc.clickSendMsgBtn);

    }
}
