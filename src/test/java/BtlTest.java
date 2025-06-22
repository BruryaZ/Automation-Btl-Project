import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import drivers.DriverManager;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.openqa.selenium.devtools.v134.page.Page.navigate;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
    public class BtlTest {
    @Test
        public void testWithReport(){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        ExtentSparkReporter reporter = new ExtentSparkReporter("./target/extentReport/ExtentReport"+timestamp+".html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // 2. יצירת טסט עם תיאור
        ExtentTest test = extent.createTest("Example", "דוגמה לשימוש ב Extent Report");

        test.assignAuthor("ו3");
        test.assignCategory("ReAgression");
        test.assignDevice("Chrome 124");

        test.info("my test was started");

        test.pass("my test is passed");
        test.fail("my test is fail");
        
        // Find the element using the locator and click it
//        clickTopMenu(DriverManager.getDriver(), Enums.MENUS.M_Z);
        extent.flush();
    }

    @RepeatedTest(3)
    public void testWithScreen(){
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.btl.gov.il/");
        driver.findElement(By.id("aaa"));
    }

    public static void clickTopMenu(WebDriver driver, enums.MENUS menuEnum) {
        // You assume the string is an ID
//        String id = menuEnum.getTopMenu();
//        WebElement element = driver.findElement(By.id(id));
//        element.click();
    }

    @ParameterizedTest
    @ValueSource(strings={"aaa","bbb","rrrr"})
    public void parametrizeTest(String input){
        Assertions.assertEquals(input.length(),3,"אורך המחרוזת איננו 3");
    }


    @ParameterizedTest
    @CsvSource({"aaa,3,size isn't 3",
            "bbbb,4,size isn't 4",
            "bbbbb,4,size isn't 4"})
    public void parametrizeCsvTest(String input,int expectedLength,String message){
        Assertions.assertEquals(expectedLength,input.length(),message);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata.csv",numLinesToSkip = 1)
    public void parametrizeCsvFileTest(String input,int expectedLength,String message){
        Assertions.assertEquals(expectedLength,input.length(),message);
    }
}