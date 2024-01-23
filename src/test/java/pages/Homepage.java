package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class Homepage extends BasePage{


    @FindBy(css = "img[alt='Site logo']")
    private WebElement viaportLogo;

    @FindBy(xpath = "//ul/li[contains(@class,'depth-0')]")
    private List<WebElement> mainMenuElements;

    @FindBy(xpath = "//ul[@id='menu-main-menu']/li[1]")
    private WebElement mainMenuHomeElement;

    @FindBy(xpath = "//ul[@id='menu-main-menu']/li[2]")
    private WebElement mainMenuVehicleFleetElement;

    @FindBy(xpath = "//ul[@id='menu-main-menu']/li[3]")
    private WebElement mainMenuServicesElement;

    @FindBy(css = "ul.sub-menu li:first-child")
    private WebElement mainMenuServicesStorageElement;

    @FindBy(css = "ul.sub-menu li:nth-child(2)")
    private WebElement mainMenuServicesLogisticElement;

    @FindBy(css = "ul.sub-menu li:nth-child(3)")
    private WebElement mainMenuServicesTransportElement;

    @FindBy(xpath = "//ul[@id='menu-main-menu']/li[4]")
    private WebElement mainMenuAboutUsElement;

    @FindBy(xpath = "//ul[@id='menu-main-menu']/li[5]")
    private WebElement mainMenuContactUsElement;

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

    @FindBy(id = "rev_slider_2_1")
    private WebElement sliderComponent;

    @FindBy(id = "slider-2-slide-2-layer-11")
    private WebElement sliderText;

    @FindBy(id = "slider-2-slide-2-layer-5")
    private WebElement firstSlideGetAQuoteButton;

    @FindBy(id = "slider-2-slide-3-layer-5")
    private WebElement secondSlideGetAQuoteButton;

    @FindBy(id = "slider-2-slide-4-layer-5")
    private WebElement thirdSlideGetAQuoteButton;

    @FindBy(xpath = "//rs-arrow[1]")
    private WebElement leftArrowButton;

    @FindBy(xpath = "//rs-arrow[2]")
    private WebElement rightArrowButton;

    @FindBy(xpath = "//a[text()='Contact us']")
    private List<WebElement> contactUsButtons;

    @FindBy(id = "custom-id-0")
    private WebElement readMoreLinkBelowTheFirstPhoto;

    @FindBy(id = "custom-id-1")
    private WebElement readMoreLinkBelowTheSecondPhoto;

    @FindBy(id = "custom-id-2")
    private WebElement readMoreLinkBelowTheThirdPhoto;

    @FindBy(id = "custom-id-4")
    private WebElement storageReadMoreLink;

    @FindBy(id = "custom-id-5")
    private WebElement logisticReadMoreLink;

    @FindBy(id = "custom-id-6")
    private WebElement transportReadMoreLink;

    @FindBy(xpath = "//a[text()='View all services']")
    private WebElement viewAllServicesLink;

    @FindBy(id = "custom-id-8")
    private WebElement requestFreeQuoteButton;

    @FindBy(className = "js--scroll")
    private WebElement scrollToTop;

    @FindBy(tagName = "a")
    private List<WebElement> allLinks;

    @FindBy(tagName = "img")
    private List<WebElement> allImages;

    @FindBy(css = "div .selected a")
    private WebElement translateSelectMenu;

    @FindBy(css = "a[title='English']")
    private WebElement englishLanguageOption;

    @FindBy(css = "html[lang]")
    private WebElement htmlLangElement;


}
