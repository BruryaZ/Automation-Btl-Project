import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.BranchesPage;
import pageObject.BtlPageObject;
import reports.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class BranchTest {
    @Test
    public void branchTest() {
        ExtentTest test = ExtentReportManager.getTest();
        WebDriver driver = DriverManager.getDriver();

        try {
            test.info("נכנסים לאתר ביטוח לאומי");
            driver.get("https://www.btl.gov.il/");
        } catch (Exception e) {
            test.fail("נכשל בכניסה לאתר ביטוח לאומי: " + e.getMessage());
            throw e;
        }

        BtlPageObject btlPageObject = new BtlPageObject(driver);
        BranchesPage branchesPage;

        try {
            test.info("מעבר לעמוד סניפים וערוצי שירות");
            branchesPage = btlPageObject.getBranchesPage();

            String title = driver.getTitle();
            assertTrue(title.equals("סניפים וערוצי שירות | ביטוח לאומי"));
            test.pass("עמוד הסניפים עלה בהצלחה!");
        } catch (AssertionError | Exception e) {
            test.fail("עמוד הסניפים לא עלה כראוי: " + e.getMessage());
            throw e;
        }

        try {
            test.info("נבחר הסניף הראשון ברשימה");
            branchesPage.selectBranch(0);
        } catch (Exception e) {
            test.fail("נכשל בבחירת סניף: " + e.getMessage());
            throw e;
        }

        try {
            WebElement addressElement = branchesPage.getAddressElement("כתובת");
            WebElement receptionElement = branchesPage.getReceptionElement("קבלת קהל");
            WebElement phoneElement = branchesPage.getPhoneElement("מענה טלפוני");

            assertTrue(addressElement.isDisplayed(), "כתובת לא מוצגת!");
            test.pass("כתובת מוצגת");

            assertTrue(receptionElement.isDisplayed(), "קבלת קהל לא מוצגת!");
            test.pass("קבלת קהל מוצגת");

            assertTrue(phoneElement.isDisplayed(), "מענה טלפוני לא מוצג!");
            test.pass("מענה טלפוני מוצג");
        } catch (AssertionError | Exception e) {
            test.fail("שגיאה בבדיקת נתוני סניף: " + e.getMessage());
            throw e;
        }
    }
}