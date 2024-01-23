package tests;

import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class HomepageTest extends BaseTest {

    @Test
    public void viaportLogoVisibilityTest() {
        Log.info("Viaport logo visibility test started...");
        waitVisibility(homepage.getViaportLogo());
        assertTrue(homepage.getViaportLogo().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("Home link visibility test started...");
        waitVisibility(homepage.getMainMenuHomeElement());
        assertTrue(homepage.getMainMenuHomeElement().isDisplayed());

    }

    @Test(groups = {"smoke"})
    public void homeLinkWorkingTest() {
        Log.info("Home link working test started...");
        click(homepage.getMainMenuHomeElement());
        String actualUrl = getDriver().getCurrentUrl();
        assertEquals(actualUrl, ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void vehicleFleetLinkVisibilityTest() {
        Log.info("Vehicle Fleet link visibility test started...");
        waitVisibility(homepage.getMainMenuVehicleFleetElement());
        assertTrue(homepage.getMainMenuVehicleFleetElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void vehicleFleetLinkWorkingTest() {
        Log.info("Vehicle Fleet working test started...");
        click(homepage.getMainMenuVehicleFleetElement());
        String actualUrl = getDriver().getCurrentUrl();
        assertEquals(actualUrl, ConfigReader.getProperty("vehicleFleetPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void servicesLinkVisibilityTest() {
        Log.info("Services link visibility test started...");
        waitVisibility(homepage.getMainMenuServicesElement());
        assertTrue(homepage.getMainMenuServicesElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void servicesLinkWorkingTest() {
        Log.info("Services link working test started...");
        click(homepage.getMainMenuServicesElement());
        String actualUrl = getDriver().getCurrentUrl();
        assertEquals(actualUrl, ConfigReader.getProperty("servicesPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void aboutUsLinkVisibilityTest() {
        Log.info("About Us link visibility test started...");
        waitVisibility(homepage.getMainMenuAboutUsElement());
        assertTrue(homepage.getMainMenuAboutUsElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void aboutUsLinkWorkingTest() {
        Log.info("About Us link working test started...");
        click(homepage.getMainMenuAboutUsElement());
        String actualUrl = getUrl(getDriver());
        assertEquals(actualUrl, ConfigReader.getProperty("aboutUsPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void contactUsLinkVisibilityTest() {
        Log.info("Contact Us link visibility test started...");
        waitVisibility(homepage.getMainMenuContactUsElement());
        assertTrue(homepage.getMainMenuContactUsElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void contactUsLinkWorkingTest() {
        Log.info("Contact Us link working test started...");
        click(homepage.getMainMenuContactUsElement());
        String actualUrl = getUrl(getDriver());
        assertEquals(actualUrl, ConfigReader.getProperty("contactUsPageUrl"));
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("Search button visibility test started...");
        waitVisibility(homepage.getSearchButton());
        assertTrue(homepage.getSearchButton().isDisplayed());
    }

    @Test
    public void searchInputVisibilityTest() {
        Log.info("Search input working test started...");
        click(homepage.getSearchButton());
        assertTrue(homepage.getSearchInput().isDisplayed());
    }

    @Test
    public void searchCloseButtonVisibilityTest() {
        Log.info("Search close button visibility test started...");
        click(homepage.getSearchButton());
        assertTrue(homepage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test
    public void searchCloseButtonWorkingTest() {
        Log.info("Search close button working test started...");
        click(homepage.getSearchButton());
        click(homepage.getSiteSearchCloseButton());

    }

    @Test
    public void searchButtonWorkingPositiveTest() {
        Log.info("Search button working positive test started...");
        click(homepage.getSearchButton());
        write(homepage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(homepage.getSearchPosts(), textContains, "About us");
    }

    @Test
    public void searchButtonWorkingNegativeTest() {
        Log.info("Search button working negative test started...");
        click(homepage.getSearchButton());
        write(homepage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = homepage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test(groups = {"smoke"})
    public void servicesStorageVisibilityTest() {
        Log.info("Visibility testing of the Storage option of the Services menu has been started");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        waitVisibility(homepage.getMainMenuServicesStorageElement());
        assertTrue(homepage.getMainMenuServicesStorageElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void servicesStorageWorkingTest() {
        Log.info("Working testing of the Storage option of the Services menu has been started");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        click(homepage.getMainMenuServicesStorageElement());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("storagePageUrl"));
    }

    @Test(groups = {"smoke"})
    public void servicesLogisticVisibilityTest() {
        Log.info("Visibility testing of the Logistic option of the Services menu has been started");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        waitVisibility(homepage.getMainMenuServicesLogisticElement());
        assertTrue(homepage.getMainMenuServicesLogisticElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void servicesLogisticWorkingTest() {
        Log.info("Working testing of the Logistic option of the Services menu has been started");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        click(homepage.getMainMenuServicesLogisticElement());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("logisticPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void servicesTransportVisibilityTest() {
        Log.info("Visibility testing of the Transport option of the Services menu has been started");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        waitVisibility(homepage.getMainMenuServicesTransportElement());
        assertTrue(homepage.getMainMenuServicesTransportElement().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void servicesTransportWorkingTest() {
        Log.info("Working testing of the Transport option of the Services menu has been started");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        click(homepage.getMainMenuServicesTransportElement());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("transportPageUrl"));
    }

    @Test
    public void sliderLeftArrowButtonVisibilityTest() {
        Log.info("Slider left arrow button visibility test started");
        scrollDownSetPixel(0, 100);
        Point location = homepage.getLeftArrowButton().getLocation();
        int locationX = location.getX();
        int locationY = location.getY();
        actions.moveByOffset(locationX,locationY).perform();
        actions.moveToElement(homepage.getFirstSlideGetAQuoteButton()).perform();
        waitVisibility(homepage.getLeftArrowButton());
        assertTrue(homepage.getLeftArrowButton().isDisplayed());
    }

    @Test
    public void sliderLeftArrowButtonWorkingTest() {
        Log.info("Slider left arrow button working test started...");
        scrollDownSetPixel(0, 100);
        jsClick(homepage.getRightArrowButton());
        assertTrue(waitVisibility(homepage.getSecondSlideGetAQuoteButton()).isDisplayed());
        jsClick(homepage.getRightArrowButton());
        assertTrue(waitVisibility(homepage.getThirdSlideGetAQuoteButton()).isDisplayed());
        jsClick(homepage.getRightArrowButton());
        assertTrue(waitVisibility(homepage.getFirstSlideGetAQuoteButton()).isDisplayed());
    }

    @Test
    public void sliderRightArrowButtonVisibilityTest() {
        Log.info("Slider right arrow button visibility test started");
        scrollDownSetPixel(0, 100);
        Point location = homepage.getRightArrowButton().getLocation();
        int locationX = location.getX();
        int locationY = location.getY();
        actions.moveByOffset(locationX,locationY).perform();
        actions.moveToElement(homepage.getFirstSlideGetAQuoteButton()).perform();
        waitVisibility(homepage.getLeftArrowButton());
        assertTrue(homepage.getRightArrowButton().isDisplayed());
    }

    @Test
    public void sliderRightArrowButtonWorkingTest() {
        Log.info("Slider left arrow button working test started...");
        scrollDownSetPixel(0, 100);
        waitVisibility(homepage.getFirstSlideGetAQuoteButton());
        actions.moveToElement(homepage.getFirstSlideGetAQuoteButton()).perform();
        click(homepage.getRightArrowButton());
        waitVisibility(homepage.getSecondSlideGetAQuoteButton());
        assertTrue(homepage.getSecondSlideGetAQuoteButton().isDisplayed());
        click(homepage.getRightArrowButton());
        waitVisibility(homepage.getThirdSlideGetAQuoteButton());
        assertTrue(homepage.getThirdSlideGetAQuoteButton().isDisplayed());
        click(homepage.getRightArrowButton());
        waitVisibility(homepage.getFirstSlideGetAQuoteButton());
        assertTrue(homepage.getFirstSlideGetAQuoteButton().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheFirstPhotoVisibilityTest() {
        Log.info("Read more link below the first photo visibility test started...");
        waitVisibility(homepage.getReadMoreLinkBelowTheFirstPhoto());
        scrollDown(homepage.getReadMoreLinkBelowTheFirstPhoto());
        assertTrue(homepage.getReadMoreLinkBelowTheFirstPhoto().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheFirstPhotoWorkingTest() {
        Log.info("Read more link below the first photo working test started...");
        scrollDown(homepage.getReadMoreLinkBelowTheFirstPhoto());
        click(homepage.getReadMoreLinkBelowTheFirstPhoto());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("storagePageUrl"));
    }

    @Test
    public void readMoreLinkBelowTheSecondPhotoVisibilityTest() {
        Log.info("Read more link below the second photo visibility test started...");
        waitVisibility(homepage.getReadMoreLinkBelowTheSecondPhoto());
        scrollDown(homepage.getReadMoreLinkBelowTheSecondPhoto());
        assertTrue(homepage.getReadMoreLinkBelowTheSecondPhoto().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheSecondPhotoWorkingTest() {
        Log.info("Read more link below the second photo working test started...");
        scrollDown(homepage.getReadMoreLinkBelowTheSecondPhoto());
        click(homepage.getReadMoreLinkBelowTheSecondPhoto());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("logisticPageUrl"));
    }

    @Test
    public void readMoreLinkBelowTheThirdPhotoVisibilityTest() {
        Log.info("Read more link below the third photo visibility test started...");
        waitVisibility(homepage.getReadMoreLinkBelowTheThirdPhoto());
        scrollDown(homepage.getReadMoreLinkBelowTheThirdPhoto());
        assertTrue(homepage.getReadMoreLinkBelowTheThirdPhoto().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheThirdPhotoWorkingTest() {
        Log.info("Read more link below the third photo working test started...");
        scrollDown(homepage.getReadMoreLinkBelowTheThirdPhoto());
        click(homepage.getReadMoreLinkBelowTheThirdPhoto());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("transportPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void contactUsButtonVisibilityTest() {
        Log.info("Contact us button visibility test started...");
        for (WebElement element : homepage.getContactUsButtons()) {
            scrollDown(element);
            waitVisibility(element);
            assertTrue(element.isDisplayed());
        }
    }

    @Test
    public void readMoreLinkBelowTheStorageTextVisibilityTest() {
        Log.info("Read more link below the storage text visibility test started...");
        scrollDown(homepage.getStorageReadMoreLink());
        waitVisibility(homepage.getStorageReadMoreLink());
        assertTrue(homepage.getStorageReadMoreLink().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheStorageTextWorkingTest() {
        Log.info("Read more link below the storage text working test started...");
        scrollDown(homepage.getStorageReadMoreLink());
        click(homepage.getStorageReadMoreLink());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("storagePageUrl"));
    }

    @Test
    public void readMoreLinkBelowTheLogisticTextVisibilityTest() {
        Log.info("Read more link below the logistic text visibility test started...");
        scrollDown(homepage.getLogisticReadMoreLink());
        waitVisibility(homepage.getLogisticReadMoreLink());
        assertTrue(homepage.getLogisticReadMoreLink().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheLogisticTextWorkingTest() {
        Log.info("Read more link below the logistic text working test started...");
        scrollDown(homepage.getLogisticReadMoreLink());
        click(homepage.getLogisticReadMoreLink());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("logisticPageUrl"));
    }

    @Test
    public void readMoreLinkBelowTheTransportTextVisibilityTest() {
        Log.info("Read more link below the transport text visibility test started...");
        scrollDown(homepage.getTransportReadMoreLink());
        waitVisibility(homepage.getTransportReadMoreLink());
        assertTrue(homepage.getTransportReadMoreLink().isDisplayed());
    }

    @Test
    public void readMoreLinkBelowTheTransportTextWorkingTest() {
        Log.info("Read more link below the transport text working test started...");
        scrollDown(homepage.getTransportReadMoreLink());
        click(homepage.getTransportReadMoreLink());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("transportPageUrl"));
    }

    @Test
    public void viewAllServicesLinkVisibilityTest() {
        Log.info("View all services link visibility test started...");
        scrollDown(homepage.getViewAllServicesLink());
        waitVisibility(homepage.getViewAllServicesLink());
        assertTrue(homepage.getViewAllServicesLink().isDisplayed());
    }

    @Test
    public void viewallServicesLinkWorkingTest() {
        Log.info("View all services link visibility test started...");
        scrollDown(homepage.getViewAllServicesLink());
        click(homepage.getViewAllServicesLink());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("servicesPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void requestFreeQuoteButtonVisibilityTest() {
        Log.info("Request free quote button visibility test started...");
        scrollDown(homepage.getRequestFreeQuoteButton());
        waitVisibility(homepage.getRequestFreeQuoteButton());
        assertTrue(homepage.getRequestFreeQuoteButton().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void requestFreeQuoteButtonWorkingTest() {
        Log.info("Request free quote button working test started...");
        scrollDown(homepage.getRequestFreeQuoteButton());
        click(homepage.getRequestFreeQuoteButton());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("contactUsPageUrl"));
    }

    @Test
    public void scrollToTopTest() {
        Log.info("Scroll to top test started...");
        scrollDownSetPixel(0, 600);
        waitVisibility(homepage.getScrollToTop());
        assertTrue(homepage.getScrollToTop().isDisplayed());
        click(homepage.getScrollToTop());
        waitVisibility(homepage.getViaportLogo());
        assertFalse(homepage.getScrollToTop().isDisplayed());
    }

    @Test(groups = {"smoke"})
    public void brokenLinkTest() {
        Log.info("Broken link test started...");
        SoftAssert softAssert = new SoftAssert();
        List<String> urlList = homepage.getAllLinks().stream()
                .map(x -> x.getAttribute("href"))
                .filter(x -> x.startsWith(ConfigReader.getProperty("contactUsPageUrl")))
                .collect(Collectors.toList());

        urlList.forEach(x -> {
            try {
                softAssert.assertFalse(urlConnection(x).getResponseCode() >= 400,
                        urlConnection(x).getResponseMessage() + " is a broken link.");

            } catch (IOException ignored) {
            }

        });
        softAssert.assertAll();

    }

    @Test(groups = {"smoke"})
    public void brokenImageTest() {
        Log.info("Broken image test started...");
        SoftAssert softAssert = new SoftAssert();
        List<String> imageUrlList = homepage.getAllImages().stream()
                .map(x -> x.getAttribute("src"))
                .collect(Collectors.toList());

        imageUrlList.forEach(x -> {
            try {
                softAssert.assertFalse(urlConnection(x).getResponseCode() >= 400,
                        urlConnection(x).getResponseMessage() + " is a broken image.");

            } catch (IOException ignored) {
            }

        });
        softAssert.assertAll();
    }

    @Test(priority = 100)
    public void translateTest() {
        Log.info("Translate test started...");
        click(homepage.getTranslateSelectMenu());
        click(homepage.getEnglishLanguageOption());
        assertNotEquals(homepage.getHtmlLangElement().getAttribute("lang"), "tr");
    }
}


