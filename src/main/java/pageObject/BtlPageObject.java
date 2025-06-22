package pageObject;

import enums.MENUS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class BtlPageObject extends BasePage {
    protected WebDriver driver;

    @FindBy(id = "TopQuestions")
    private WebElement searchBox;

    @FindBy(id = "ctl00_SiteHeader_reserve_btnSearch") // זכוכית מגדלת
    private WebElement glassButton;

    @FindBy(id = "ctl00_Topmneu_BranchesHyperLink") // כפתור "סניפים"
    private WebElement branchesButton;

    public BtlPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // חשוב: אתחול כל האלמנטים
    }

    public void clickOnTopMenu(MENUS selectedById) {
        WebElement menu = WaitUtils.waitForClickAbility(driver, selectedById.getBy(), 10);
        menu.click();
    }

    public void clickOnSubMenu(String linkText) {
        WebElement link = WaitUtils.waitForClickAbility(driver, By.linkText(linkText), 10);
        link.click();
    }

    public void initSearchBox(String value) {
        WaitUtils.waitForVisibility(driver, searchBox, 10).sendKeys(value);
        WaitUtils.waitForClickAbilityByElement(driver, glassButton, 10).click();
    }

    public BranchesPage getBranchesPage() {
        WaitUtils.waitForClickAbilityByElement(driver, branchesButton, 10).click();
        return new BranchesPage(driver);
    }
}
