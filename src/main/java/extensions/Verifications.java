package extensions;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.qameta.allure.Step;
import utilities.CommonOps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Step("Verify Response status code")
    public static void verifyApiResponse(String Url, HttpResponseStatus statusCode) {
        // TODO - FIX: Only works for chrome driver
        LogEntries les = driver.manage().logs().get(LogType.PERFORMANCE);
        for (LogEntry le : les) {
            if (le.getMessage().contains(Url)
                    && le.getMessage().contains("status")) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(le.getMessage());

                    JsonNode response = jsonNode.path("message").path("params").path("response");
                    String status = response.path("status").asText();

                    LOG.info("API response status: " + status + ". URL: " + Url);

                    try {
                        Verifications.verifyString(status, String.valueOf(statusCode.code()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Step("Verify Response status code & error message")
    public static void verifyApiResponse(String Url, HttpResponseStatus statusCode, String errorMessage) {
        LogEntries les = driver.manage().logs().get(LogType.PERFORMANCE);
        for (LogEntry le : les) {
            if (le.getMessage().contains(Url)
                    && le.getMessage().contains("status")) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(le.getMessage());

                    JsonNode response = jsonNode.path("message").path("params").path("response");
                    String status = response.path("status").asText();
                    if (status == "400") {
                        String message = response.path("message").asText();
                        Verifications.verifyString(message, errorMessage);
                    }
                    LOG.info("API response status: " + status + ". URL: " + Url);

                    try {
                        Verifications.verifyString(status, String.valueOf(statusCode.code()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
