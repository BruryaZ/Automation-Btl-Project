import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import enums.MENUS;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.InsuranceCalculatorPageForBachur;
import reports.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class CalculateForBachurTest {

    @Test
    public void insurancePremiumCalculationForBachur() {
        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = ExtentReportManager.getTest();
        InsuranceCalculatorPageForBachur calc = new InsuranceCalculatorPageForBachur(driver);

        try {
            test.info("נכנסים לאתר ביטוח לאומי");
            driver.get("https://www.btl.gov.il/");
            test.pass("האתר נפתח בהצלחה");
        } catch (Exception e) {
            test.fail("שגיאה בפתיחת האתר: " + e.getMessage());
            throw e;
        }

        try {
            test.info("לחיצה על תפריט 'ביטוח'");
            calc.clickOnTopMenu(MENUS.INSURANCE);
            test.pass("תפריט 'ביטוח' נלחץ בהצלחה");
        } catch (Exception e) {
            test.fail("שגיאה בלחיצה על תפריט 'ביטוח': " + e.getMessage());
            throw e;
        }

        test.info("לחיצה על תת תפריט 'דמי ביטוח לאומי'");
        calc.clickOnSubMenu("דמי ביטוח לאומי");
        test.pass("תת תפריט 'דמי ביטוח לאומי' נלחץ");

        test.info("לחיצה על 'מחשבון לחישוב דמי הביטוח'");
        calc.clickOnSubMenu("מחשבון לחישוב דמי הביטוח");
        test.pass("נכנסנו למחשבון בהצלחה");

        test.info("בחירת 'בחור'");
        calc.selectBachur();
        test.pass("'בחור' נבחר");

        test.info("בחירת מגדר");
        calc.selectGender();
        test.pass("מגדר נבחר");

        test.info("הזנת תאריך לידה");
        calc.enterBirthDate("1/11/2006");
        test.pass("תאריך לידה הוזן");

        test.info("לחיצה על כפתור המשך");
        calc.clickContinueStep1();
        test.pass("המשכנו בהצלחה לשלב הבא");

        try {
            test.info("בדיקת מעבר לצעד שני");
            calc.isStepTwo(); // בשיטה זו צריכה להיות assert פנימי או שתחזיר true/false
            test.pass("עברנו לצעד שני");
        } catch (AssertionError e) {
            test.fail("לא עברנו לצעד שני: " + e.getMessage());
            throw e;
        }

        test.info("בחירת 'לא מקבל קצבת נכות'");
        calc.selectNoDisability();
        test.pass("נבחרה האפשרות ללא קצבת נכות");

        test.info("מעבר לצעד הבא");
        calc.clickContinueStep2();
        test.pass("עברנו לשלב הסיום");

        try {
            test.info("בדיקת מעבר למסך סיום");
            assertTrue(calc.isFinishScreenVisible());
            test.pass("הגענו למסך הסיום");
        } catch (AssertionError e) {
            test.fail("לא הגענו למסך הסיום: " + e.getMessage());
            throw e;
        }

        test.info("שליפת תוצאות");
        assertAll(
                () -> assertTrue(calc.isLabelWithAmountPresent("דמי ביטוח לאומי", "43"), "דמי ביטוח לאומי לא נמצא או שגוי"),
                () -> assertTrue(calc.isLabelWithAmountPresent("דמי ביטוח בריאות", "120.00"), "דמי ביטוח בריאות לא נמצא או שגוי"),
                () -> assertTrue(calc.isLabelWithAmountPresent("סך הכל דמי ביטוח לחודש", "163"), "סך הכל דמי ביטוח לחודש לא נמצא או שגוי")
        );

        for (String result : calc.getResults()) {
            test.info("תוצאה: " + result);
        }
        test.pass("תוצאות נשלפו בהצלחה");
    }
}
