package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import org.openqa.selenium.WebElement;

public class WikiMFAPage {

    @FindBy(css = "span#Factors")
    private WebElement factorsTitle;

    @Step("Get Factors title element")
    public WebElement getFactorsTitle() {
        return factorsTitle;
    }

    @FindBy(css = ".mw-content-ltr p")
    private List<WebElement> wikiPageParagraphs;

    @Step("Get Wiki Page Paragraphs elements")
    public List<WebElement> getWikiPageParagraphs() {
        return wikiPageParagraphs;
    }

    
    @FindBy(css = ".mw-content-ltr ul")
    private List<WebElement> wikiPageUnorderedLists;

    @Step("Get Wiki Page Unordered Lists elements")
    public List<WebElement> getWikiPageUnorderedLists() {
        return wikiPageUnorderedLists;
    }

}