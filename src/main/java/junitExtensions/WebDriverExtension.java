package junitExtensions;

import drivers.DriverManager;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class WebDriverExtension implements BeforeTestExecutionCallback, AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        DriverManager.quitDriver();
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        DriverManager.initDriver();
    }
}
