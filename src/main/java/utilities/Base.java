package utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
        protected static WebDriver driver;
        protected static WebDriverWait wait;
        protected static Actions action;

        protected static String browserName;
        protected static String url;
        protected static Duration timeoutDuration;

        protected static pageObjects.WikiMFAPage wikiMFAPage;

        protected static final int SLEEP_TIMEOUT = 1_000;
        protected static String URL = "https://en.wikipedia.org/wiki/Multi-factor_authentication";

}
