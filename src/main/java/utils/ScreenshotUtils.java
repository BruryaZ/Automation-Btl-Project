package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtils {
    public static boolean takeScreenshot(WebDriver driver, String destinationPath) {
        if(driver != null){
            // צילום חלון הדפדפן והחזרה כקובץ זמני
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(destinationPath);

            try {
                destFile.getParentFile().mkdirs();
                Files.copy(srcFile.toPath(), destFile.toPath());
                return true;
            } catch (IOException e) {
                System.err.println("Failed to save screenshot to " + destinationPath);
                e.printStackTrace();
            }
        } else {
            System.err.println("WebDriver is null - cannot take screenshot.");
        }
        return false;
    }
}
