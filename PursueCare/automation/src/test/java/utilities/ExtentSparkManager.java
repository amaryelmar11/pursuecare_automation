package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.Baseclass;

public class ExtentSparkManager implements ITestListener {

    private static ExtentReports extent; // make it static to share across suites
    private static ExtentSparkReporter sparkReporter;
    private static String repName;
    public ExtentTest test;

    @Override
    public synchronized void onStart(ITestContext testContext) {
        // Initialize only once for all suites
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName = "Test-Report_SaiSystem_" + timeStamp + ".html";
            String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("PursueCare Addiction Therapy Application Report");
            sparkReporter.config().setReportName("Functional Testing Summary (All Suites)");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "Addiction Therapy App");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Executed By", System.getProperty("user.name"));
        }

        // Set current suite info dynamically
        extent.setSystemInfo("Current Suite", testContext.getSuite().getName());
    }

    public static String getReadableTestName(ITestResult result) {
        String desc = result.getMethod().getDescription();
        return (desc != null && !desc.isEmpty()) ? desc : result.getMethod().getMethodName();
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        test = extent.createTest(getReadableTestName(result))
                     .assignCategory(result.getTestContext().getSuite().getName()); // tag suite name
        test.log(Status.PASS, getReadableTestName(result) + " passed successfully.");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        test = extent.createTest(getReadableTestName(result))
                     .assignCategory(result.getTestContext().getSuite().getName());
        test.log(Status.FAIL, getReadableTestName(result) + " failed.");
        test.log(Status.INFO, result.getThrowable().getMessage());
        try {
            String imgPath = new Baseclass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        test = extent.createTest(getReadableTestName(result))
                     .assignCategory(result.getTestContext().getSuite().getName());
        test.log(Status.SKIP, getReadableTestName(result) + " skipped.");
        test.log(Status.INFO, result.getThrowable() != null ? result.getThrowable().getMessage() : "No skip reason provided.");
    }

    @Override
    public synchronized void onFinish(ITestContext testContext) {
        // Flush only at the end of the final suite
        if (testContext.getSuite().getAllMethods().size() == testContext.getAllTestMethods().length) {
            extent.flush();
            try {
                String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
                Desktop.getDesktop().browse(new File(pathOfExtentReport).toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
