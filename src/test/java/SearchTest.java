import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import pageObject.BtlPageObject;
import reports.ExtentReportManager;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class SearchTest {
    @Test
    public void testSearch() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.btl.gov.il/");

        ExtentTest test = ExtentReportManager.getTest();
        test.info("We entered Btl website");

        test.info("Search \"חישוב סכום דמי לידה ליום\"");

        BtlPageObject btlPageObject = new BtlPageObject(DriverManager.getDriver());
        btlPageObject.initSearchBox("חישוב סכום דמי לידה ליום");

        String pageTitle = driver.getTitle();

        System.out.println(pageTitle);

        try {
            assertTrue(pageTitle.equals("חישוב סכום דמי לידה ליום - מחשבוני זכויות | ביטוח לאומי"));
            test.info("The search was successful. The title is correct");
        } catch (AssertionError e) {
            test.info("The search was not successful. the title is " + pageTitle);
            throw e; // חובה לזרוק כדי שהטסט ייכשל באמת
        }
    }
}