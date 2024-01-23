package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class VehicleFleetPage extends BasePage{

    @FindBy(xpath = "(//ul[@class='wpb_image_grid_ul']/li/a/img)[1]")
    private WebElement firstPhoto;

    @FindBy(xpath = "(//ul[@class='wpb_image_grid_ul']/li/a/img)[8]")
    private WebElement eighthPhoto;

    @FindBy(className = "lb-close")
    private WebElement imageClose;

    @FindBy(className = "lb-next")
    private WebElement rightArrow;

    @FindBy(className = "lb-prev")
    private WebElement leftArrow;

    @FindBy(id = "custom-id-0")
    private WebElement getAQuoteButton;

    @FindBy(css = "img[alt='Site logo']")
    private WebElement viaportLogo;

    @FindBy(css = "ul[class='breadcrumbs'] a ")
    private WebElement homeLink;

    @FindBy(className = "lb-close")
    private WebElement lightboxClose;

    @FindBy(xpath = "//ul[@class='wpb_image_grid_ul']/li/a/img")
    private List<WebElement> allImages;
}

