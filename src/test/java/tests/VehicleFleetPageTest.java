package tests;

import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.VehicleFleetPage;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static org.testng.Assert.*;

public class VehicleFleetPageTest extends BaseTest {
    private VehicleFleetPage vehicleFleetPage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        vehicleFleetPage = new VehicleFleetPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToVehicleFleetPage() {
        click(homepage.getMainMenuVehicleFleetElement());
    }

    @Test(groups = {"smoke"})
    public void viaportLogoVisibilityTest() {
        Log.info("Vehicle Fleet Page Viaport Logo Visibility Test Started");
        assertTrue(vehicleFleetPage.getViaportLogo().isDisplayed());
    }

    @Test(dependsOnMethods = "viaportLogoVisibilityTest")
    public void viaportLogoWorkingTest() {
        Log.info("Vehicle Fleet Page Viaport Logo Working Test Started");
        click(vehicleFleetPage.getViaportLogo());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("Vehicle Fleet Page Home Link Visibility Test Started");
        assertTrue(vehicleFleetPage.getHomeLink().isDisplayed());
    }

    @Test(dependsOnMethods = "homeLinkVisibilityTest")
    public void homeLinkWorkingTest() {
        Log.info("Vehicle Fleet Page Home Link Working Test Started");
        click(vehicleFleetPage.getHomeLink());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test
    public void rightArrowVisibilityTest() {
        Log.info("Vehicle Fleet Page right arrow visibility test started...");
        click(vehicleFleetPage.getFirstPhoto());
        waitVisibility(vehicleFleetPage.getImageClose());
        Point point = vehicleFleetPage.getRightArrow().getLocation();
        int x = point.getX();
        actions.moveByOffset(x + 5, 50).perform();
        waitVisibility(vehicleFleetPage.getRightArrow());
        assertTrue(vehicleFleetPage.getRightArrow().isDisplayed());
    }

    @Test
    public void leftArrowVisibilityTest() {
        Log.info("Vehicle Fleet Page left arrow visibility test started...");
        scrollDown(vehicleFleetPage.getEighthPhoto());
        click(vehicleFleetPage.getEighthPhoto());
        waitVisibility(vehicleFleetPage.getImageClose());
        Point point = vehicleFleetPage.getLeftArrow().getLocation();
        int x = point.getX();
        actions.moveByOffset(x + 5, 50).perform();
        waitVisibility(vehicleFleetPage.getLeftArrow());
        assertTrue(vehicleFleetPage.getLeftArrow().isDisplayed());
    }

    @Test
    public void leftArrowWorkingTest() {
        Log.info("Vehicle Fleet Page left arrow working test started...");
        click(vehicleFleetPage.getEighthPhoto());
        for (int i = vehicleFleetPage.getAllImages().size()-1; i >= 1; i--) {
            scrollDown(vehicleFleetPage.getAllImages().get(i));
            waitVisibility(vehicleFleetPage.getImageClose());
            jsClick(vehicleFleetPage.getLeftArrow());
            assertTrue(vehicleFleetPage.getAllImages().get(i-1).isDisplayed());
        }
    }

    @Test
    public void rightArrowWorkingTest() {
        Log.info("Vehicle Fleet Page right arrow working test started...");
        click(vehicleFleetPage.getFirstPhoto());
        for (int i = 0; i < vehicleFleetPage.getAllImages().size()-1; i++) {
            scrollDown(vehicleFleetPage.getAllImages().get(i));
            waitVisibility(vehicleFleetPage.getImageClose());
            jsClick(vehicleFleetPage.getLeftArrow());
            assertTrue(vehicleFleetPage.getAllImages().get(i+1).isDisplayed());
        }
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("Vehicle Fleet Page search button visibility test started...");
        waitVisibility(homepage.getSearchButton());
        assertTrue(homepage.getSearchButton().isDisplayed());
    }

    @Test
    public void searchInputVisibilityTest() {
        Log.info("Vehicle Fleet Page search input working test started...");
        click(homepage.getSearchButton());
        assertTrue(homepage.getSearchInput().isDisplayed());
    }

    @Test
    public void searchCloseButtonVisibilityTest() {
        Log.info("Vehicle Fleet Page search close button visibility test started...");
        click(homepage.getSearchButton());
        assertTrue(homepage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test
    public void searchCloseButtonWorkingTest() {
        Log.info("Vehicle Fleet Page search close button working test started...");
        click(homepage.getSearchButton());
        click(homepage.getSiteSearchCloseButton());

    }

    @Test
    public void searchButtonWorkingPositiveTest() {
        Log.info("Vehicle Fleet Page search button working positive test started...");
        click(homepage.getSearchButton());
        write(homepage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(homepage.getSearchPosts(), textContains, "About us");
    }

    @Test
    public void searchButtonWorkingNegativeTest() {
        Log.info("Vehicle Fleet Page search button working negative test started...");
        click(homepage.getSearchButton());
        write(homepage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = homepage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void getAQuoteButtonTest() {
        Log.info("Vehicle Fleet Page get a quote button test started...");
        click(vehicleFleetPage.getGetAQuoteButton());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("contactUsPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void brokenImageTest() {
        Log.info("Vehicle Fleet Page broken image test started...");
        SoftAssert softAssert = new SoftAssert();
        List<String> imageUrlList = vehicleFleetPage.getAllImages().stream()
                .map(x -> x.getAttribute("src"))
                .toList();

        imageUrlList.forEach(x -> {
            try {
                softAssert.assertFalse(urlConnection(x).getResponseCode() >= 400,
                        urlConnection(x).getResponseMessage() + " is a broken image.");

            } catch (IOException ignored) {
            }

        });
        softAssert.assertAll();
    }
}
