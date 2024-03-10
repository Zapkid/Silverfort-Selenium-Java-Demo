package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class WikiMFAPage {

    @FindBy(css = "span#Factors")
    private WebElement factorsTitle;

    @Step("Get Factors title element")
    public WebElement getFactorsTitle() {
        return factorsTitle;
    }

}