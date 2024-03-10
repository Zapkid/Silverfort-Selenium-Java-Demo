package utilities;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

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

        protected static Set<String> dictionary = new HashSet<>();


        // Wikipedia MFA page
        protected static String URL = "https://en.wikipedia.org/wiki/Multi-factor_authentication";
        protected static pageObjects.WikiMFAPage wikiMFAPage;

        protected static int factorsParagraphsStartingIndex = 4;
        protected static int factorsParagraphsCount = 4;
        protected static int factorsUnorderedListIndex = 0;

}
