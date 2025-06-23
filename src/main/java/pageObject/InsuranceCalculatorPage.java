package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class InsuranceCalculatorPage extends BtlPageObject {
    @FindBy(xpath = "//*[@id=\"ctl00_Topmneu_Benefits\"]/div/div[1]/div[5]/ul/li[1]/a")
    private WebElement unemployment;

    @FindBy(xpath = "//*[@id=\"mainContent\"]/div[1]/div[2]/span/div[2]/div[3]/a/strong")
    private WebElement calcUnemployment;

    @FindBy(xpath = "//*[@id=\"ctl00_PlaceHolderMain_SiteNodesControl_ChildrensDiv\"]/ul/li[2]/a")
    private WebElement calcUnemployment2;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_DynDatePicker_PiturimDate_Date")
    private WebElement date;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_rdb_age_1")
    private WebElement on28;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StartNavigationTemplateContainerID_StartNextButton")
    private WebElement continue1;

    @FindBy(xpath = "//*[@id=\"ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid\"]/tbody")
    private WebElement fathersSalaries;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StepNavigationTemplateContainerID_StepNextButton")
    private WebElement continue2;

    @FindBy(xpath = "//*[@id=\"ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StepDiv3\"]/h3")
    private WebElement calcResults;

    public InsuranceCalculatorPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void chooseUnemployment() {
        WaitUtils.waitForClickAbilityByElement(driver, unemployment, 10).click();
    }

    public void chooseCalcUnemployment() {
        WaitUtils.waitForClickAbilityByElement(driver, calcUnemployment, 10).click();
    }

    public void chooseCalcUnemployment2() {
        WaitUtils.waitForClickAbilityByElement(driver, calcUnemployment2, 10).click();
    }

    public void fillDate(String dateOfWorkStoppage) {
        WaitUtils.waitForVisibility(driver, date, 10).sendKeys(dateOfWorkStoppage);
    }

    public void chooseOn28() {
        WaitUtils.waitForClickAbilityByElement(driver, on28, 10).click();
    }

    public void setContinue1(){
        WaitUtils.waitForClickAbilityByElement(driver, continue1, 10).click();
    }

    public void setSalaries(List<Integer> salariesList){
        List<WebElement> salaries = WaitUtils.waitForMultipleElementsInElement(driver, fathersSalaries, By.className("txtbox_sallary"), 30);
        for (int i = 0; i < salaries.size(); i++) {
            salaries.get(i).sendKeys(salariesList.get(i).toString());
        }
    }

    public void setContinue2(){
        WaitUtils.waitForClickAbilityByElement(driver, continue2, 10).click();
    }

    public WebElement isCalcResult() {
          return  WaitUtils.waitForVisibility(driver, calcResults, 10);
    }

    public void checkCalcResult(){

    }
}
