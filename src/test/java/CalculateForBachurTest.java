import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import enums.MENUS;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageObject.BtlPageObject;
import pageObject.InsuranceCalculatorPage;
import reports.ExtentReportManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class CalculateForBachurTest {
    @Test
    public void insurancePremiumCalculationForBachur() {
        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = ExtentReportManager.getTest();
        InsuranceCalculatorPage calc = new InsuranceCalculatorPage(driver);

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

        try {
            test.info("לחיצה על תת תפריט 'דמי ביטוח לאומי'");
            calc.clickOnSubMenu("דמי ביטוח לאומי");
            test.pass("תת תפריט 'דמי ביטוח לאומי' נלחץ");
        } catch (Exception e) {
            test.fail("שגיאה בלחיצה על תת תפריט 'דמי ביטוח לאומי': " + e.getMessage());
            throw e;
        }

        try {
            test.info("לחיצה על 'מחשבון לחישוב דמי הביטוח'");
            calc.clickOnSubMenu("מחשבון לחישוב דמי הביטוח");
            test.pass("נכנסנו למחשבון בהצלחה");
        } catch (Exception e) {
            test.fail("שגיאה בכניסה למחשבון: " + e.getMessage());
            throw e;
        }

        try {
            test.info("בחירת 'בחור'");
            calc.selectBachur();
            test.pass("'בחור' נבחר");
        } catch (Exception e) {
            test.fail("שגיאה בבחירת 'בחור': " + e.getMessage());
            throw e;
        }

        try {
            test.info("בחירת מגדר");
            calc.selectGender();
            test.pass("מגדר נבחר");
        } catch (Exception e) {
            test.fail("שגיאה בבחירת מגדר: " + e.getMessage());
            throw e;
        }

        try {
            test.info("הזנת תאריך לידה");
            calc.enterBirthDate("1/11/2006");
            test.pass("תאריך לידה הוזן");
        } catch (Exception e) {
            test.fail("שגיאה בהזנת תאריך לידה: " + e.getMessage());
            throw e;
        }

        try {
            test.info("לחיצה על כפתור המשך");
            calc.clickContinueStep1();
            test.pass("המשכנו בהצלחה לשלב הבא");
        } catch (Exception e) {
            test.fail("שגיאה בלחיצה על כפתור המשך: " + e.getMessage());
            throw e;
        }

        try {
            test.info("בדיקת מעבר לצעד שני");
            calc.isStepTwo();
            test.pass("עברנו לצעד שני");
        } catch (AssertionError e) {
            test.fail("לא עברנו לצעד שני: " + e.getMessage());
            throw e;
        }

        try {
            test.info("בחירת 'לא מקבל קצבת נכות'");
            calc.selectNoDisability();
            test.pass("נבחרה האפשרות ללא קצבת נכות");
        } catch (Exception e) {
            test.fail("שגיאה בבחירת נכות: " + e.getMessage());
            throw e;
        }

        try {
            test.info("מעבר לצעד הבא");
            calc.clickContinueStep2();
            test.pass("עברנו לשלב הסיום");
        } catch (Exception e) {
            test.fail("שגיאה במעבר לצעד הבא: " + e.getMessage());
            throw e;
        }

        try {
            test.info("בדיקת מעבר למסך סיום");
            assertTrue(calc.isFinishScreenVisible());
            test.pass("הגענו למסך הסיום");
        } catch (AssertionError e) {
            test.fail("לא הגענו למסך הסיום: " + e.getMessage());
            throw e;
        }

        try {
            test.info("שליפת תוצאות");
            for (String result : calc.getResults()) {
                test.info("תוצאה: " + result);
            }
            test.pass("תוצאות נשלפו בהצלחה");
        } catch (Exception e) {
            test.fail("שגיאה בשליפת התוצאות: " + e.getMessage());
            throw e;
        }
    }
}