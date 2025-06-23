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

    @FindBy(id = "TopQuestions")
    private WebElement searchBox;

    @FindBy(id = "ctl00_SiteHeader_reserve_btnSearch") // זכוכית מגדלת
    private WebElement glassButton;

    @FindBy(id = "ctl00_Topmneu_BranchesHyperLink") // כפתור "סניפים"
    private WebElement branchesButton;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2")
    private WebElement bachurRadio;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0")
    private WebElement genderRadio;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date")
    private WebElement birthDateInput;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StartNavigationTemplateContainerID_StartNextButton")
    private WebElement continueButtonStep1;

    @FindBy(xpath = "//*[contains(text(), 'צעד שני')]")
    private WebElement stepTwoTitle;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1")
    private WebElement noDisabilityRadio;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StepNavigationTemplateContainerID_StepNextButton")
    private WebElement nextStepButton;

    @FindBy(xpath = "//*[contains(text(), 'סיום')]")
    private WebElement finishTitle;

    @FindBy(className = "CalcResult")
    private WebElement resultContainer;

    public BtlPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
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