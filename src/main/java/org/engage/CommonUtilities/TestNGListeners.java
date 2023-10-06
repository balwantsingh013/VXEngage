package org.engage.CommonUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.engage.Base.BaseUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestNGListeners extends BaseUtilities implements ITestListener {

    ExtentReports extents = ExtentSparkReport.initialise();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extents.createTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "Test Case is Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.fail(iTestResult.getThrowable());
        try {
            driver = (WebDriver) iTestResult.getTestClass()
                    .getRealClass().getField("driver")
                    .get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = null;
        try {
            filePath= getscreenshots(iTestResult.getMethod().getMethodName(), driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(filePath,iTestResult.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extents.flush();
    }
}
