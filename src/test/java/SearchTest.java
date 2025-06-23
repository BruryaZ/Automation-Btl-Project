import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import pageObject.BtlPageObject;
import pageObject.SearchPage;
import reports.ExtentReportManager;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class SearchTest {

    @Test
    public void testSearch() {
        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = ExtentReportManager.getTest();

        test.info("פתיחת האתר ביטוח לאומי");
        driver.get("https://www.btl.gov.il/");
        test.pass("האתר נפתח בהצלחה");

        test.info("מבצעים חיפוש של הביטוי: \"חישוב סכום דמי לידה ליום\"");
        SearchPage searchPage = new SearchPage(driver);
        searchPage.search("חישוב סכום דמי לידה ליום");
        test.pass("החיפוש בוצע");

        String pageTitle = driver.getTitle();
        test.info("כותרת העמוד שהתקבלה: " + pageTitle);

        try {
            assertTrue(pageTitle.equals("חישוב סכום דמי לידה ליום - מחשבוני זכויות | ביטוח לאומי"));
            test.pass("תוצאת החיפוש נכונה – הטסט עבר");
        } catch (AssertionError e) {
            test.fail("כותרת לא תואמת – הטסט נכשל: " + pageTitle);
            throw e;
        }
    }
}