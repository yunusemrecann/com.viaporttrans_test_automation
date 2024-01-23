package pages;

import org.openqa.selenium.support.PageFactory;
import tests.BaseTest;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(BaseTest.getDriver(),this);
    }

}
