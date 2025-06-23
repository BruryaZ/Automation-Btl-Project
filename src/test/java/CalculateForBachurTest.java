import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import enums.MENUS;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.BtlPageObject;
import reports.ExtentReportManager;
import utils.ElementAction;
import utils.WaitUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class CalculateForBachurTest {

    @Test
    public void insurancePremiumCalculationForBachur() {
        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = ExtentReportManager.getTest();

        test.info("נכנסים לאתר ביטוח לאומי");
        driver.get("https://www.btl.gov.il/");

        BtlPageObject btlPageObject = new BtlPageObject(driver);

        test.info("לחיצה על תפריט עליון 'ביטוח'");
        btlPageObject.clickOnTopMenu(MENUS.INSURANCE);

        test.info("לחיצה על תת תפריט 'דמי ביטוח לאומי'");
        btlPageObject.clickOnSubMenu("דמי ביטוח לאומי");

        try {
            WaitUtils.waitForTitleIs(driver, "דמי ביטוח לאומי - דמי ביטוח | ביטוח לאומי", 10);
            test.pass("נכנסנו לדמי ביטוח לאומי");
        } catch (Exception e) {
            test.fail("לא הגעת לדמי ביטוח לאומי");
            throw new RuntimeException(e);
        }

        test.info("לחיצה על תת תפריט 'מחשבון לחישוב דמי הביטוח'");
        btlPageObject.clickOnSubMenu("מחשבון לחישוב דמי הביטוח");

        try {
            WaitUtils.waitForTitleContains(driver, "חישוב דמי ביטוח", 10);
            test.pass("נכנסנו למחשבון לחישוב דמי ביטוח");
        } catch (Exception e) {
            test.fail("לא הגעת למחשבון דמי ביטוח");
            throw new RuntimeException(e);
        }

        test.info("בחירת 'בחור'");
        By bachurLocator = By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2");
        WebElement bachur = WaitUtils.waitForClickAbility(driver, bachurLocator, 10);
        bachur.click();

        test.info("בחירת מגדר");
        By genderLocator = By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0");
        WebElement gender = WaitUtils.waitForClickAbility(driver, genderLocator, 20);
        gender.click();

        test.info("הזנת תאריך לידה");
        By dateLocator = By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date");
        WebElement date = WaitUtils.waitForVisibilityLocated(driver, dateLocator, 10);
        date.sendKeys("1/11/2006");

        test.info("לחיצה על כפתור המשך");
        By continueLocator = By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StartNavigationTemplateContainerID_StartNextButton");
        WebElement continueBtn = WaitUtils.waitForClickAbility(driver, continueLocator, 20);
        continueBtn.click();

        test.info("בדיקת מעבר לצעד שני");
        By stepTwoLocator = By.xpath("//*[contains(text(), 'צעד שני')]");
        try {
            WebElement stepTwo = WaitUtils.waitForPresenceOfElement(driver, stepTwoLocator, 10);
            assertEquals("צעד שני", stepTwo.getText().split("\n")[0]);
            test.pass("עברנו לצעד שני בהצלחה");
        } catch (Exception e) {
            test.fail("לא הגעת לצעד שני");
            throw new RuntimeException(e);
        }
        // בחירת "לא מקבל קצבת נכות"
        test.info("בחירת 'לא מקבל קצבת נכות'");
        By disabilityLocator = By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1");
        WebElement disability = WaitUtils.waitForClickAbility(driver, disabilityLocator, 30);
        ElementAction.scrollAndClick(driver, disability);

        // לחיצה על כפתור המשך
        test.info("לחיצה על כפתור המשך לצעד הבא");
        By nextStepLocator = By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StepNavigationTemplateContainerID_StepNextButton");
        try {
            WebElement nextButton = WaitUtils.waitForClickAbility(driver, nextStepLocator, 10);
            nextButton.click();
            test.pass("לחיצה על כפתור ההמשך בוצעה בהצלחה");
        } catch (Exception e) {
            test.fail("לא הצלחנו ללחוץ על כפתור ההמשך לצעד הבא");
            throw new RuntimeException(e);
        }

        // בדיקה שהגענו למסך הסיום
        test.info("בדיקת מעבר למסך סיום");
        By finishLocator = By.xpath("//*[contains(text(), 'סיום')]");
        try {
            WebElement finish = WaitUtils.waitForPresenceOfElement(driver, finishLocator, 10);
            assertEquals("סיום", finish.getText().split("\n")[0]);
            test.pass("הגענו למסך הסיום בהצלחה");
        } catch (Exception e) {
            test.fail("לא הגעת למסך הסיום");
            throw new RuntimeException(e);
        }

        // תוצאה: קצבת נכות
        test.info("שליפת תוצאות חישוב מהמסך");
        try {
            WebElement resultContainer = WaitUtils.waitForVisibilityLocated(driver, By.className("CalcResult"), 10);
            List<WebElement> resultItems = resultContainer.findElements(By.tagName("li"));
            for (WebElement li : resultItems) {
                test.info("תוצאה: " + li.getText());
            }
            test.pass("תוצאות נשלפו בהצלחה");
        } catch (Exception e) {
            test.fail("נכשל באחזור תוצאות החישוב");
            throw new RuntimeException(e);
        }

        test.info("סיום תרחיש בדיקת חישוב בחור");
    }
}