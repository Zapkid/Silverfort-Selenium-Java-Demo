package extensions;

import io.qameta.allure.Step;
import utilities.CommonOps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UIActions extends CommonOps {

    @Step("Wait for element click-ability, then Click on Element")
    public static void click(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("Wait for element click-ability, then Click on Element & sleep")
    public static void click(WebElement elem, long sleepTimeMillis) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
        sleep(sleepTimeMillis);
    }

    @Step("No wait Click on element")
    public static void noWaitClick(WebElement elem) {
        elem.click();
    }

    @Step("Wait for element visibility, then Update Text Element")
    public static void updateText(WebElement elem, String text) {
        wait.until((ExpectedConditions.visibilityOf(elem)));
        elem.sendKeys(text);
    }

    @Step("Update Text Element as Human")
    public static void updateTextAsHuman(WebElement elem, String text) {
        wait.until((ExpectedConditions.visibilityOf(elem)));
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            try {
                Thread.sleep((int) (400 + (Math.random() * 100)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elem.sendKeys(String.valueOf(c));
        }
    }

    @Step("Type text")
    public static void type(WebElement elem, String text) {
        elem.sendKeys(text);
    }

    @Step("Wait for element visibility & type text")
    public static void waitAndType(WebElement elem, String text) {
        wait.until((ExpectedConditions.visibilityOf(elem)));
        elem.sendKeys(text);
        try {
            Thread.sleep(SLEEP_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Mouse Hover Element")
    public static void hover(WebElement element) {
        action.moveToElement(element).build().perform();
    }

    @Step("Mouse Hover Element & sleep")
    public static void hover(WebElement element, long sleepTimeMillis) {
        action.moveToElement(element).build().perform();
        sleep(sleepTimeMillis);
    }

    @Step("Mouse Hover Element & Click")
    public static void hoverAndClick(WebElement element) {
        action.moveToElement(element).click().build().perform();
    }

    @Step("Scroll Element Into view")
    public static void scrollIntoView(WebElement element, long sleepTimeMillis) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(sleepTimeMillis);
    }

}