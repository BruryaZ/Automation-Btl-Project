import com.aventstack.extentreports.ExtentTest;
import drivers.DriverManager;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageObject.BtlPageObject;
import reports.ExtentReportManager;

@ExtendWith({ExtentReportExtension.class, WebDriverExtension.class})
public class CalculateInsurancePremiumsTest {
    @Test
    public void socialSecurityContributionCalculationScript(){
        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = ExtentReportManager.getTest();

        test.info("נכנסים לאתר ביטוח לאומי");
        driver.get("https://www.btl.gov.il/");

        BtlPageObject btlPageObject = new BtlPageObject(driver);

    }
}
