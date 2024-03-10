package utilities;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.file.Files;

public class CommonOps extends Base {

    public static final Logger LOG = LoggerFactory.getLogger(CommonOps.class);

    // Initiate Parameters from Suite XML
    @BeforeClass
    @Parameters({ "BrowserName", "URL", "timeout" })
    public void startSession(String BrowserName, String URL, String timeout) {
        browserName = BrowserName;
        url = URL;
        try {
            timeoutDuration = Duration.ofSeconds(Integer.parseInt(timeout));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        initWeb();
    }

    // Close session
    @AfterClass
    public void closeSession() {
        driver.quit();
    }

    // Start video recording before starting a test
    @BeforeMethod
    public void beforeMethod(Method method) {

        try {
            MonteScreenRecorder.startRecord(method.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.get(url);

    }

    // Initiate Browser, Actions & Wait & Navigate to URL
    public static void initWeb() {
        if (browserName.equalsIgnoreCase("chrome")) {
            initChrome();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            initFireFox();
        } else if (browserName.equalsIgnoreCase("edge")) {
            initEdge();
        } else {
            throw new RuntimeException("Invalid Browser Type");
        }

        driver.manage().window().maximize();
        setDriverTimeoutAndWait();
        action = new Actions(driver);

        ManagePages.initVicariusPages();
    }

    private static void initEdge() {
        driver = new EdgeDriver();
    }

    private static void initFireFox() {
        driver = new FirefoxDriver();
    }

    private static void initChrome() {
        driver = new ChromeDriver();
    }

    // Take a screenshot for Allure report & save a png file
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] saveScreenshot(String desc, String format) {
        Date date = new Date();
        long time = date.getTime();
        Timestamp timeStamp = new Timestamp(time);
        String timeString = timeStamp.toString();
        timeString = timeString.substring(11).replace(".", ":").replace(":", "_");
        byte[] imgBytes = new byte[0];
        imgBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        try {
            FileUtils.writeByteArrayToFile(new File("./screenshots/" + timeString + " - " + desc + "." + format),
                    imgBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgBytes;
    }

    public static void setDriverTimeoutAndWait() {
        driver.manage().timeouts().implicitlyWait(timeoutDuration);
        wait = new WebDriverWait(driver, timeoutDuration);
    }

    @Step("Get element parent")
    public static WebElement getElementParent(WebElement element) {
        return element.findElement(By.xpath("parent::*"));
    }

    @Step("Get element following-sibling")
    public static WebElement getElementFollowingSibling(WebElement element) {
        return element.findElement(By.xpath("following-sibling::*"));
    }

    public static String cleanText(String inputText) {
        String regex = "\\b[^\\d\\p{P}()\\.]+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputText);

        StringBuilder cleanedText = new StringBuilder();
        while (matcher.find()) {
            cleanedText.append(matcher.group()).append(" ");
        }

        return cleanedText.toString().trim();
    }

    public static Map<String, Integer> countAndPrintWordOccurrences(String inputString) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        String[] words = inputString.split("\\s+");

        for (String word : words) {
            // Convert the word to lowercase to make the counting case-insensitive
            String lowercaseWord = word.toLowerCase();

            // Update the word count in the HashMap
            wordCountMap.put(lowercaseWord, wordCountMap.getOrDefault(lowercaseWord, 0) + 1);
        }

        // Print the word occurrences
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println("Word: " + entry.getKey() + ", Occurrences: " + entry.getValue());
        }

        return wordCountMap;
    }

    public static List<String> readDictionaryWords(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }

        return words;
    }

    public static void loadDictionary(String dictionaryFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.toLowerCase()); // Convert to lowercase for case-insensitive matching
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    public static boolean isWordCorrectlySpelled(String word) {
        return dictionary.contains(word.toLowerCase()); // Convert to lowercase for case-insensitive matching
    }

    public void lookForTypos(Set<String> words) {
        loadDictionary("eng_com.dic");

        for (String word : words) {
            if (!isWordCorrectlySpelled(word)) {
                System.out.println("Possible typo: " + word);
            }
        }
    }

    public String cleanTexts(List<String> texts) {
        String cleanedText = "";

        for (String text : texts) {
            String cleanText = cleanText(text);
            cleanedText += cleanText + " ";
        }

        return cleanedText;
    }

    public List<String> getTextsFromWebPage() {
        List<String> texts = new ArrayList<String>();

        for (int i = factorsParagraphsStartingIndex; i < factorsParagraphsStartingIndex
                + factorsParagraphsCount; i++) {
            texts.add(wikiMFAPage.getWikiPageParagraphs().get(i).getText());
        }

        texts.add(wikiMFAPage.getWikiPageUnorderedLists().get(factorsUnorderedListIndex).getText());

        return texts;
    }

}