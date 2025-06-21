package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BranchesPage extends BtlPageObject {
    public BranchesPage(WebDriver driver) {
        super(driver);
    }

    public void selectBranch(int index) {
        WebElement branchesContainer = driver.findElement(By.id("SnifimTabs"));
        List<WebElement> branches = branchesContainer.findElements(By.tagName("li"));

        if (index >= 0 && index < branches.size()) {
            WebElement selectedBranch = branches.get(index);
            WebElement branchLink = selectedBranch.findElement(By.tagName("a"));
            branchLink.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs("אשדוד - סניפים וערוצי שירות | ביטוח לאומי"));

        } else {
            System.out.println("אינדקס לא חוקי: " + index);
        }
    }

    public WebElement getAddressElement(String labelText) {
        return driver.findElement(By.xpath("//label[text()='" + labelText + "']"));
    }

    public WebElement getPhoneElement(String labelText) {
        return driver.findElement(By.xpath("//label[text()='" + labelText + "']"));
    }

    public WebElement getReceptionElement(String labelText) {
        return driver.findElement(By.xpath("//label[text()='" + labelText + "']"));
    }
}
