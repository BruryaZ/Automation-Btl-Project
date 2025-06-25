import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import enums.MENUS;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pageObject.BtlPageObject;
import pageObject.InsuranceCalculatorPage;
import reports.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class SearchInPensionAndBenefit {

    @ParameterizedTest(name = "בדיקת ניתוב עבור: {0}")
    @CsvSource({
            "נפגעי פוליו",
            "נפגעי גזזת",
            "נפגעי עירוי דם",
    })
    @ValueSource(strings = {"שיקום מקצועי", "ילד נכה"})
    public void searchTest(String input) {
        ExtentTest test = ExtentReportManager.getTest();
        WebDriver driver = DriverManager.getDriver();
        InsuranceCalculatorPage calc = new InsuranceCalculatorPage(driver);

        try {
            test.info("נכנסים לאתר ביטוח לאומי");
            driver.get("https://www.btl.gov.il/");
        } catch (Exception e) {
            test.fail("נכשל בכניסה לאתר ביטוח לאומי: " + e.getMessage());
            throw e;
        }

        test.info("כניסה לזכויות והטבות");
        calc.clickOnTopMenu(MENUS.ALLOWANCE);

        test.info("ניתוב לפי בחירה:");
        calc.EnterToOptions(input);
        test.pass("הניתוב ל " + input + "עבר בהצלחה");

        test.info("בדיקה שהעמוד נכון");
        try {
            assertTrue(driver.getTitle().contains(input + " - קצבאות והטבות | ביטוח לאומי"), "לא נותב ל " + input);
            test.pass("הניתוב הצליח!");
        } catch (AssertionError e) {
            test.fail("הניתוב לא הצליח!");
            throw e;
        }
    }
}
