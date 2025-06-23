package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementAction;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCalculatorPage extends BtlPageObject{
    private WebDriver driver;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2")
    private WebElement bachurRadio;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0")
    private WebElement genderRadio;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date")
    private WebElement birthDateInput;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StartNavigationTemplateContainerID_StartNextButton")
    private WebElement continueButtonStep1;

    @FindBy(xpath = "//*[@id=\"header\" and contains(normalize-space(), 'צעד שני')]")
    private WebElement stepTwoTitle;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1")
    private WebElement noDisabilityRadio;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StepNavigationTemplateContainerID_StepNextButton")
    private WebElement nextStepButton;

    @FindBy(xpath = "//*[@id=\"header\" and contains(normalize-space(), 'סיום')]")
    private WebElement finishTitle;

    @FindBy(className = "CalcResult")
    private WebElement resultContainer;

    public InsuranceCalculatorPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillStepOne(String date) {
        WaitUtils.waitForClickAbilityByElement(driver, bachurRadio, 10).click();
        WaitUtils.waitForClickAbilityByElement(driver, genderRadio, 10).click();
        WaitUtils.waitForVisibility(driver, birthDateInput, 10).sendKeys(date);
        WaitUtils.waitForClickAbilityByElement(driver, continueButtonStep1, 10).click();
    }

    public void selectGender() {
        WaitUtils.waitForClickAbilityByElement(driver, genderRadio, 10).click();
    }

    public void enterBirthDate(String date) {
        WaitUtils.waitForVisibility(driver, birthDateInput, 10).sendKeys(date);
    }

    public void selectBachur() {
        WaitUtils.waitForClickAbilityByElement(driver, bachurRadio, 10).click();
    }

    public void clickContinueStep1() {
        WaitUtils.waitForClickAbilityByElement(driver, continueButtonStep1, 10).click();
    }

    public void isStepTwo(){
        WaitUtils.waitForVisibility(driver, stepTwoTitle, 10);
    }

    public void selectNoDisability() {
        ElementAction.scrollAndClick(driver, noDisabilityRadio);
    }

    public void clickContinueStep2(){
        WaitUtils.waitForClickAbilityByElement(driver, nextStepButton, 10).click();
    }

    public boolean isFinishScreenVisible() {
        return WaitUtils.waitForVisibility(driver, finishTitle, 10).getText().contains("סיום");
    }

    public List<String> getResults() {
        WaitUtils.waitForVisibility(driver, resultContainer, 10);
        List<WebElement> lis = resultContainer.findElements(By.tagName("li"));
        List<String> results = new ArrayList<>();
        for (WebElement li : lis) {
            results.add(li.getText());
        }
        return results;
    }
}

