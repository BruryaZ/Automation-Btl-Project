package enums;

import org.openqa.selenium.By;

public enum MENUS {
    SEARCH_BOX("TopQuestions"),
    GLASS("ctl00_SiteHeader_reserve_btnSearch"),
    EXERCISE_OF_RIGHTS("ctl00_Topmneu_BenefitsHyperLink"),
    INSURANCE("ctl00_Topmneu_InsuranceHyperLink"),
    ALLOWANCE("ctl00_Topmneu_HyperLink3"),
    CONTACT("ctl00_Topmneu_ContactsHyperLink"),
    BRANCHES("ctl00_Topmneu_BranchesHyperLink"),
    PERSONAL_SERVICE("ctl00_Topmneu_HyperLink9"),
    PAYMENTS("ctl00_Topmneu_HyperLink1");

    private final String id;

    MENUS(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return id;
    }

    public By getBy() {
        return By.id(id);
    }
}