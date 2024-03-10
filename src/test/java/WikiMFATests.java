import io.qameta.allure.Description;
import utilities.CommonOps;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities.Listeners.class)
public class WikiMFATests extends CommonOps {

        List<String> texts = new ArrayList<String>();

        @Test(description = "X Test", priority = 1)
        @Description("Tests x")
        public void XTest() {
                WebElement element = getElementFollowingSibling(getElementParent(wikiMFAPage.getFactorsTitle()));
                System.out.println("Tag name: " + element.getTagName());

                texts.add(element.getText());

        }

}
