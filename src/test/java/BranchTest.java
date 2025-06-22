import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.BranchesPage;
import pageObject.BtlPageObject;

import static org.junit.jupiter.api.Assertions.*;

public class BranchTest {
    private WebDriver driver;
    private BtlPageObject btlPageObject;
    private BranchesPage branchesPage;

    @BeforeEach
    public void setUp() {
        // הגדרת נתיב לדפדפן Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://btl.gov.il/"); // פתח את האתר לפני הבדיקה
        btlPageObject = new BtlPageObject(driver);
    }

    @Test
    public void branchTest() {
        branchesPage = btlPageObject.getBranchesPage();
        String title = driver.getTitle();
        if(!title.equals("סניפים וערוצי שירות | ביטוח לאומי"))
            throw new AssertionError("עמוד הסניפים לא עלה בהצלחה! \n");

        branchesPage.selectBranch(0);

        // בדוק שהמידע מופיע
        WebElement addressElement = branchesPage.getAddressElement("כתובת");
        WebElement receptionElement = branchesPage.getReceptionElement("קבלת קהל");
        WebElement phoneElement = branchesPage.getPhoneElement("מענה טלפוני");

        // בדוק אם האלמנטים קיימים
        assertTrue(addressElement.isDisplayed(), "כתובת לא מוצגת!");
        assertTrue(receptionElement.isDisplayed(), "קבלת קהל לא מוצגת!");
        assertTrue(phoneElement.isDisplayed(), "מענה טלפוני לא מוצג!");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}