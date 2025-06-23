package pageObject;

import enums.MENUS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementAction;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class BtlPageObject extends BasePage{
    protected WebDriver driver;

    public BtlPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickOnTopMenu(MENUS menu) {
        WebElement menuElement = WaitUtils.waitForClickAbility(driver, By.id(menu.getDisplayName()), 10);
        menuElement.click();
    }

    public void clickOnSubMenu(String text) {
        WebElement element = WaitUtils.waitForClickAbility(driver, By.linkText(text), 10);
        element.click();
    }
}