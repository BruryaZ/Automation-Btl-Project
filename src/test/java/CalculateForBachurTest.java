import enums.MENUS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.BtlPageObject;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateForBachurTest {
    private WebDriver driver;
    private BtlPageObject btlPageObject;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://btl.gov.il/");
        btlPageObject = new BtlPageObject(driver);
    }

    @Test
    public void insurancePremiumCalculationForBachur() {
        btlPageObject.clickOnTopMenu(MENUS.INSURANCE);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        btlPageObject.clickOnSubMenu("דמי ביטוח לאומי");
        try {
            assertEquals(driver.getTitle(), "דמי ביטוח לאומי - דמי ביטוח | ביטוח לאומי");
        } catch (AssertionError e) {
            throw new RuntimeException("לא הגעת לדמי ביטוח לאומי");
        }

        btlPageObject.clickOnSubMenu("מחשבון לחישוב דמי הביטוח");
        try {
            assertEquals(driver.getTitle(), "חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד - דמי ביטוח | ביטוח לאומי");
        } catch (AssertionError e) {
            throw new RuntimeException("לא הגעת למחשבון דמי ביטוח");
        }

        WebElement bachur = driver.findElement(By.id("ctl00_ctl44_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2_lbl"));
        bachur.click();

        WebElement gender = driver.findElement(By.id("ctl00_ctl44_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0"));
        gender.click();

        WebElement date = driver.findElement(By.id("ctl00_ctl44_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date"));
        date.sendKeys("1/11/2006");

        WebElement continueButton = driver.findElement(By.id("ctl00_ctl44_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StartNavigationTemplateContainerID_StartNextButton"));
        continueButton.click();

        try {
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement finish = wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'צעד שני')]")));
            assertEquals(finish.getText().split("\n")[0], "צעד שני");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("לא הגעת לצעד שני");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement disability = wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl44_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1")));


        try {
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement continueButton2 = wait2.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl44_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StepNavigationTemplateContainerID_StepNextButton")));
            continueButton2.click();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("לא הגעת לאלמנט כפתור ההמשך");
        }

        try {
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement finish2 = wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'סיום')]")));
            assertEquals(finish2.getText().split("\n")[0], "סיום");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("לא הגעת לסיום");
        }

        System.out.println("Success");

        //מקבל קצבת נכות
        WebElement res = driver.findElement(By.className("CalcResult"));
        List<WebElement> resList = res.findElements(By.tagName("li"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
