package testng.listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import testng.tech.Topic_08_Listener;

import java.io.File;
import java.io.IOException;

public class Report_TestNG implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
       TakesScreenshot t = (TakesScreenshot) Topic_08_Listener.driver;
       File srcFile = t.getScreenshotAs(OutputType.FILE);
        try {
            File desFile = new File("./screenshot/" + iTestResult.getName() + ".jpg");
            FileUtils.copyFile(srcFile, desFile);
            Reporter.log("<a href='" + desFile.getAbsolutePath() + "'> <img src='" + desFile.getAbsolutePath() + "' height='100' width'100' /> </a>");
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
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

    }
}
