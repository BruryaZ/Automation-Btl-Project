package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    protected static WebDriver driver;

    public static void initDriver() {
        WebDriverManager.chromedriver().setup(); // התאמת גרסת הכרום לגרסה המעודכנת שעל המחשב. גישה מודרנית במקום הורדה ידנית של הקובץ
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
    }
}