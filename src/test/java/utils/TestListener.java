package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();
        test.fail("Test Failed");

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        String path = ScreenshotUtil.captureScreenshot(driver, result.getName());

        try {
            test.addScreenCaptureFromPath(path);  // only once
        } catch (Exception e) {
            test.fail("Unable to attach screenshot");
        }

        Throwable cause = result.getThrowable();
        if (cause != null) {
            test.fail("Reason: " + cause.getMessage().split("\n")[0]);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test Skipped ");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}