package pageObject;

import Enums.MENUS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BtlPageObject extends BasePage{
    MENUS searchBox = MENUS.SEARCH_BOX;
    MENUS glass = MENUS.GLASS;
    MENUS branch = MENUS.BRANCHES;
    public BtlPageObject( WebDriver driver) {
        super(driver);
    }

    public void clickOnTopMenu (MENUS selectedById){
        WebElement element = driver.findElement(By.id(selectedById.getDisplayName()));
        element.click();
    }

    public void clickOnSubMenu (String link){
        WebElement element = driver.findElement(By.linkText(link));
        element.click();
    }

    public void initSearchBox(String val){
        WebElement searchBoxE = driver.findElement(By.id(searchBox.getDisplayName()));
        searchBoxE.sendKeys(val);

        WebElement glassE = driver.findElement(By.id(glass.getDisplayName()));
        glassE.click();
    }

    public BranchesPage getBranchesPage(){
        WebElement element = driver.findElement(By.id(branch.getDisplayName()));
        element.click();

        // האם זה נכון להחזיר כך עמוד שנותבנו אליו על ידי לחיצה על לינק
        return new BranchesPage(driver);
    }
}
