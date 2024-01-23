package tests;

import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TransportPage;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static org.testng.Assert.*;

public class TransportPageTest extends BaseTest{

    private TransportPage transportPage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        transportPage = new TransportPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToStoragePage() {
        Log.info("Opening " + ConfigReader.getProperty("transportPageUrl") + "...");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        click(homepage.getMainMenuServicesStorageElement());
    }

    @Test(groups = {"smoke"})
    public void viaportLogoVisibilityTest() {
        Log.info("Transport Page Viaport Logo Visibility Test Started");
        assertTrue(transportPage.getViaportLogo().isDisplayed());
    }

    @Test(dependsOnMethods = "viaportLogoVisibilityTest")
    public void viaportLogoWorkingTest() {
        Log.info("Transport Page Viaport Logo Working Test Started");
        click(transportPage.getViaportLogo());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("Transport Page Home Link Visibility Test Started");
        assertTrue(transportPage.getHomeLink().isDisplayed());
    }

    @Test(dependsOnMethods = "homeLinkVisibilityTest")
    public void homeLinkWorkingTest() {
        Log.info("Transport Page Home Link Working Test Started");
        click(transportPage.getHomeLink());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("Transport Page search button visibility test started...");
        waitVisibility(transportPage.getSearchButton());
        assertTrue(transportPage.getSearchButton().isDisplayed());
    }

    @Test
    public void searchInputVisibilityTest() {
        Log.info("Transport Page search input working test started...");
        click(transportPage.getSearchButton());
        assertTrue(transportPage.getSearchInput().isDisplayed());
    }

    @Test
    public void searchCloseButtonVisibilityTest() {
        Log.info("Transport Page search close button visibility test started...");
        click(transportPage.getSearchButton());
        assertTrue(transportPage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test
    public void searchCloseButtonWorkingTest() {
        Log.info("Transport Page search close button working test started...");
        click(transportPage.getSearchButton());
        click(transportPage.getSiteSearchCloseButton());

    }

    @Test
    public void searchButtonWorkingPositiveTest() {
        Log.info("Transport Page search button working positive test started...");
        click(transportPage.getSearchButton());
        write(transportPage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(transportPage.getSearchPosts(), textContains, "About us");
    }

    @Test
    public void searchButtonWorkingNegativeTest() {
        Log.info("Transport Page search button working negative test started...");
        click(transportPage.getSearchButton());
        write(transportPage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = transportPage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test(groups = {"smoke"})
    public void brokenImageTest() {
        Log.info("Transport Page broken image test started...");
        SoftAssert softAssert = new SoftAssert();
        List<String> imageUrlList = transportPage.getImages().stream()
                .map(x -> x.getAttribute("src"))
                .toList();

        System.out.println(imageUrlList);

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
