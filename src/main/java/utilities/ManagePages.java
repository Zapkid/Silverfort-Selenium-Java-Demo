package utilities;

import org.openqa.selenium.support.PageFactory;

public class ManagePages extends Base {

    // Web: Initiate Webpage Objects
    public static void initVicariusPages() {
        wikiMFAPage = PageFactory.initElements(driver, pageObjects.WikiMFAPage.class);
    }

}
