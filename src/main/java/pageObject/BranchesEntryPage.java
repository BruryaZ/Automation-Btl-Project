package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class BranchesEntryPage extends BtlPageObject {
    @FindBy(id = "ctl00_Topmneu_BranchesHyperLink")
    private WebElement branchesButton;

    public BranchesEntryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public BranchesPage getBranchesPage() {
        WaitUtils.waitForClickAbilityByElement(driver, branchesButton, 10).click();
        return new BranchesPage(driver);
    }
}