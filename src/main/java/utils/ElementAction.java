package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementAction {
    public static void scrollAndClick(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}
        js.executeScript("arguments[0].click();", element);
    }
}
