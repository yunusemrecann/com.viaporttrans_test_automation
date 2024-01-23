package common.listeners;

import common.logs.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReportsManager;
import tests.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Log.info(result.getName() + " is started...");
        ExtentReportsManager.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(result.getName() + " is succeed.");
        ExtentReportsManager.pass(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info(result.getName() + " is failed.");
        WebDriver driver = BaseTest.getDriver();
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        String screenshotStr = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        ExtentReportsManager.fail(result.getThrowable().getMessage(),screenshotStr,result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info(result.getName() + " test is skipped.");
        ExtentReportsManager.skip(result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportsManager.flush();
    }
}
