import io.qameta.allure.Description;
import utilities.CommonOps;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities.Listeners.class)
public class WikiMFATests extends CommonOps {

        @Test(description = "Analyze text on Wikipedia MFA page Test", priority = 1)
        @Description("Analyze text on Wikipedia MFA page, Count unique word occurrences & check for typos")
        public void AnalyzeTextTest() {

                List<String> texts = getTextsFromWebPage();

                String cleanedText = cleanTexts(texts);

                Map<String, Integer> wordCountMap = countAndPrintWordOccurrences(cleanedText);

                lookForTypos(wordCountMap.keySet());

                assertEquals(wordCountMap.get("something").toString(), "5");
        }

}
