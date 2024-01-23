package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ContactUsPage extends BasePage{

    @FindBy(css = "img[alt='Site logo']")
    private WebElement viaportLogo;

    @FindBy(css = "ul[class='breadcrumbs'] a ")
    private WebElement homeLink;

    @FindBy(xpath = "//button[@class='fa fa-search site-search-toggle']")
    private WebElement searchButton;

    @FindBy(className = "site-search-input")
    private WebElement searchInput;

    @FindBy(xpath = "//li[@class='search-post']")
    private List<WebElement> searchPosts;

    @FindBy(className = "no-results")
    private WebElement searchResult;

    @FindBy(className = "site-search-close")
    private WebElement siteSearchCloseButton;

    @FindBy(css = "input[name='your-name']")
    private WebElement name;

    @FindBy(css = "input[name='your-subject']")
    private WebElement subject;

    @FindBy(css = "input[name='your-email']")
    private WebElement email;

    @FindBy(css = "textarea[name='your-message']")
    private WebElement message;

    @FindBy(css = "input[type='submit']")
    private WebElement sendMessageButton;

    @FindBy(className = "wpcf7-response-output")
    private WebElement responseOutput;
}
