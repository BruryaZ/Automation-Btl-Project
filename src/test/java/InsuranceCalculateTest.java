import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import enums.MENUS;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.InsuranceCalculatorPage;
import reports.ExtentReportManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class InsuranceCalculateTest {
    @Test
    public void insuranceCalc() {
        ExtentTest test = ExtentReportManager.getTest();
        WebDriver driver = DriverManager.getDriver();

        try {
            test.info("נכנסים לאתר ביטוח לאומי");
            driver.get("https://www.btl.gov.il/");
        } catch (Exception e) {
            test.fail("נכשל בכניסה לאתר ביטוח לאומי: " + e.getMessage());
            throw e;
        }

        InsuranceCalculatorPage calc = new InsuranceCalculatorPage(driver);

        test.info("כניסה לזכויות והטבות");
        calc.clickOnTopMenu(MENUS.ALLOWANCE);

        test.info("כניסה לאבטלה");
        calc.chooseUnemployment();

        test.info("כניסה למחשבוני דמי אבטלה");
        calc.chooseCalcUnemployment();
        calc.chooseCalcUnemployment2();

        test.info("מילוי תאריך הפסקת עבודה");
        calc.fillDate("1/05/2025");

        test.info("בחירת 'מעל גיל 28'");
        calc.chooseOn28();

        test.info("לחיצה על כפתור 'המשך'");
        calc.setContinue1();

        test.info("הכנסת משכורות לחצי שנה אחרונה");
        List<Integer> salaries = new ArrayList<>(Collections.nCopies(6, 30000));
        calc.setSalaries(salaries);

        test.info("לחיצה על כפתור 'המשך'");
        calc.setContinue2();

        test.info("בדיקה שעלה דף 'תוצאות חישוב'");
        WebElement results;
        try {
            results = calc.isCalcResult();
            test.pass("תוצאות החישוב מוצגות בהצלחה");
        } catch (Exception e) {
            test.fail("לא מוצגות תוצאות החישוב!");
            throw e;
        }

        test.info("בדיקת תוצאות החישוב בטקסט המוצג");
        String resultText = calc.getResults().getText();

        try {
            assertAll(
                    () -> {
                        assertTrue(resultText.contains("שכר יומי ממוצע לצורך חישוב דמי אבטלה"), "שכר יומי ממוצע לא הופיע");
                        test.pass("שכר יומי ממוצע נמצא");
                    },
                    () -> {
                        assertTrue(resultText.contains("דמי אבטלה ליום"), "דמי אבטלה ליום לא הופיע");
                        test.pass("דמי אבטלה ליום נמצא");
                    },
                    () -> {
                        assertTrue(resultText.contains("דמי אבטלה לחודש"), "דמי אבטלה לחודש לא הופיע");
                        test.pass("דמי אבטלה לחודש נמצא");
                    }
            );
        } catch (AssertionError e) {
            test.fail("חלק מהתוצאות לא הופיעו: " + e.getMessage());
            throw e;
        }

        test.pass("כל תוצאות החישוב אומתו בהצלחה!");
    }

}
