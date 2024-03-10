package extensions;

import io.qameta.allure.Step;
import utilities.CommonOps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.testng.Assert.*;

public class Verifications extends CommonOps {

    @Step("Verify text in element")
    public static void verifyElementText(WebElement element, String expected) {
        wait.until(ExpectedConditions.visibilityOf(element));
        assertEquals(element.getText(), expected);
    }

    @Step("Verify element is visible")
    public static void verifyElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        assertTrue(element.isDisplayed(), "Element is not visible");
    }

    @Step("Verify element not found")
    public static void verifyElementNotFound(String cssSelector) {
        assertTrue(driver.findElements(By.cssSelector(cssSelector)).isEmpty());
    }

    @Step("Verify Boolean")
    public static void verifyBoolean(Boolean actual, Boolean exp) {
        assertEquals(actual, exp);
    }

    @Step("Verify String")
    public static void verifyString(String actual, String exp) {
        assertEquals(actual, exp);
    }

    @Step("Verify String Contains")
    public static void verifyStringContains(String actual, String exp) {
        assertTrue(actual.contains(exp));
    }

    @Step("Verify elements amount & visibility")
    public static void verifyElementsAmountAndVisibility(List<WebElement> elements, int expected) {
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        assertEquals(elements.size(), expected);
    }

    @Step("Verify Element css property")
    public static void verifyElementCss(WebElement element, String cssProperty, String expectedCssValue) {
        Verifications.verifyString(element
                .getCssValue(cssProperty), expectedCssValue);
    }

}
