package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AboutUsPage extends BasePage{

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

    @FindBy(id = "custom-id-0")
    private WebElement ourMissionButton;

    @FindBy(id = "custom-id-1")
    private WebElement ourVisionButton;

    @FindBy(id = "custom-id-2")
    private WebElement getAQuoteButton;
}
