package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
/*
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;*/
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.Baseclass;

public class ExtentSparkManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) 
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report_SaiSystem" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);
		
		sparkReporter.config().setDocumentTitle("PusueCare Addiction Therapy Application Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Addiction Therapy Appl");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Environment", "QA");
		
	String browser	= testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
		
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getClass().getName());
		//test.assignCategory(result.getMethod().getGroups()); //turn on this when using the groups concept
		test.log(Status.PASS, result.getName()+" got Successfully Executed");
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getClass().getName());
		//test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ "got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgpath = new Baseclass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void  onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getClass().getName());
		//test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) 
	{
		extent.flush();
		
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
