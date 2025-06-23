package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class SearchPage extends BtlPageObject {
    private WebDriver driver;

    @FindBy(id = "TopQuestions")
    private WebElement searchBox;

    @FindBy(id = "ctl00_SiteHeader_reserve_btnSearch")
    private WebElement glassButton;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void search(String value) {
        WaitUtils.waitForVisibility(driver, searchBox, 20).sendKeys(value);
        WaitUtils.waitForClickAbilityByElement(driver, glassButton, 10).click();
    }
}