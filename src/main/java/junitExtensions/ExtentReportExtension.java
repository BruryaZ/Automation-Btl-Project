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

    @Override
    public void beforeEach(ExtensionContext context)  {
        String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
        extentTest = ReportManager.getExtent().createTest(testName);
        ExtentReportManager.setTest(extentTest);
    }

    @Override
    public void afterAll(ExtensionContext context)  {
        ReportManager.flushReport();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        String methodName = context.getTestMethod().get().getName();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String filename = methodName + "_" + timestamp + ".png";
        String fullPath = "./target/ExtentReport/screenshot/" + filename;
        String relativePath = "./screenshot/" + filename;

        boolean isTakeScreenshot = ScreenshotUtils.takeScreenshot(DriverManager.getDriver(), fullPath);

        if (context.getExecutionException().isPresent()) {
            // במקרה של כשלון
            String cause = context.getExecutionException().get().getMessage();

            if (isTakeScreenshot) {
                extentTest.fail(cause, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } else {
                extentTest.fail("Test failed but could not take screenshot");
                extentTest.fail(cause);
            }

        } else {
            // במקרה של הצלחה
            if (isTakeScreenshot) {
                extentTest.pass("Test passed",
                        MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            } else {
                extentTest.pass("Test passed but could not take screenshot");
            }
        }
    }
}