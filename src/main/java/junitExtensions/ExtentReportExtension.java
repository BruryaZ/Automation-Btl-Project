package junitExtensions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import drivers.DriverManager;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import reports.ExtentReportManager;
import reports.ReportManager;
import utils.ScreenshotUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportExtension implements BeforeEachCallback, AfterTestExecutionCallback, AfterAllCallback {
    private static ExtentTest extentTest;
    private String testName;

    @Override
    public void beforeEach(ExtensionContext context)  {
        testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
        extentTest = ReportManager.getExtent().createTest(testName);
        ExtentReportManager.setTest(extentTest);
    }
    @Override
    public void afterAll(ExtensionContext context)  {
        ReportManager.flushReport();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if(context.getExecutionException().isPresent()){
            String cause = context.getExecutionException().get().getMessage();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String screenshotPath = "./target/ExtentReport/screenshot/" + context.getTestMethod().get().getName() + "_" + timestamp + ".png";
            boolean isTakeScreenshot = ScreenshotUtils.takeScreenshot(DriverManager.getDriver(), screenshotPath);
            String relativePath = "./screenshot/" + context.getTestMethod().get().getName() + "_" + timestamp + ".png";

            if (isTakeScreenshot) {
                extentTest.fail(cause, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } else {
                extentTest.fail("Test failed but could not take screenshot");
                extentTest.fail(cause);
            }
        }
    }
}
