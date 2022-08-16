package yoversewallet.utils.listeners;

import com.aventstack.extentreports.Status;
import yoversewallet.pages.BasePage;
import yoversewallet.utils.extentreports.ExtentManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static yoversewallet.utils.extentreports.ExtentTestManager.getTest;

public class TestListener extends BasePage implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onStart(ITestContext iTestContext) {
//        Log.info("I am in onStart method " + iTestContext.getName());
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", getDriver());
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
//        Log.info("I am in onFinish method " + iTestContext.getName());
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
//        Log.info(getTestMethodName(iTestResult) + " test is starting.");
        System.out.println(getTestMethodName(iTestResult) + " test is starting.");
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
//        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        System.out.println(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log( Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
//        Log.info(getTestMethodName(iTestResult) + " test is failed.");
        System.out.println(getTestMethodName(iTestResult) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
//        driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot)getDriver()).getScreenshotAs( OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
//        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        System.out.println(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
