package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.List;

public class BranchesPage extends BtlPageObject {
    @FindBy(id = "SnifimTabs")
    private WebElement branchesContainer;

    public BranchesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // חובה לאתחול @FindBy
    }

    public void selectBranch(int index) {
        // המתנה עד שמיכל הסניפים נטען
        WaitUtils.waitForVisibility(driver, branchesContainer, 10);

        List<WebElement> branches = branchesContainer.findElements(By.tagName("li"));

        if (index >= 0 && index < branches.size()) {
            WebElement selectedBranch = branches.get(index);
            WebElement branchLink = selectedBranch.findElement(By.tagName("a"));
            WaitUtils.waitForClickAbilityByElement(driver, branchLink, 10).click();

            // המתנה עד שהכותרת משתנה לאשדוד
            WaitUtils.waitForTitleIs(driver, "אשדוד - סניפים וערוצי שירות | ביטוח לאומי", 10);
        } else {
            System.out.println("אינדקס לא חוקי: " + index);
        }
    }

    public WebElement getAddressElement(String labelText) {
        return WaitUtils.waitForPresenceOfElement(driver, By.xpath("//label[text()='" + labelText + "']"), 10);
    }

    public WebElement getPhoneElement(String labelText) {
        return WaitUtils.waitForPresenceOfElement(driver, By.xpath("//label[text()='" + labelText + "']"), 10);
    }

    public WebElement getReceptionElement(String labelText) {
        return WaitUtils.waitForPresenceOfElement(driver, By.xpath("//label[text()='" + labelText + "']"), 10);
    }
}