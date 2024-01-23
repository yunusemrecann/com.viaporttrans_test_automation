package tests;

import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoragePage;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static org.testng.Assert.*;

public class StoragePageTest extends BaseTest{
    private StoragePage storagePage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        storagePage = new StoragePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToStoragePage() {
        Log.info("Opening " + ConfigReader.getProperty("storagePageUrl") + "...");
        actions.moveToElement(homepage.getMainMenuServicesElement()).perform();
        click(homepage.getMainMenuServicesStorageElement());
    }

    @Test(groups = {"smoke"})
    public void viaportLogoVisibilityTest() {
        Log.info("Storage Page Viaport Logo Visibility Test Started");
        assertTrue(storagePage.getViaportLogo().isDisplayed());
    }

    @Test(dependsOnMethods = "viaportLogoVisibilityTest")
    public void viaportLogoWorkingTest() {
        Log.info("Storage Page Viaport Logo Working Test Started");
        click(storagePage.getViaportLogo());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("Storage Page Home Link Visibility Test Started");
        assertTrue(storagePage.getHomeLink().isDisplayed());
    }

    @Test(dependsOnMethods = "homeLinkVisibilityTest")
    public void homeLinkWorkingTest() {
        Log.info("Storage Page Home Link Working Test Started");
        click(storagePage.getHomeLink());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("Storage Page search button visibility test started...");
        waitVisibility(storagePage.getSearchButton());
        assertTrue(storagePage.getSearchButton().isDisplayed());
    }

    @Test
    public void searchInputVisibilityTest() {
        Log.info("Storage Page search input working test started...");
        click(storagePage.getSearchButton());
        assertTrue(storagePage.getSearchInput().isDisplayed());
    }

    @Test
    public void searchCloseButtonVisibilityTest() {
        Log.info("Storage Page search close button visibility test started...");
        click(storagePage.getSearchButton());
        assertTrue(storagePage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test
    public void searchCloseButtonWorkingTest() {
        Log.info("Storage Page search close button working test started...");
        click(storagePage.getSearchButton());
        click(storagePage.getSiteSearchCloseButton());

    }

    @Test
    public void searchButtonWorkingPositiveTest() {
        Log.info("Storage Page search button working positive test started...");
        click(storagePage.getSearchButton());
        write(storagePage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(storagePage.getSearchPosts(), textContains, "About us");
    }

    @Test
    public void searchButtonWorkingNegativeTest() {
        Log.info("Storage Page search button working negative test started...");
        click(storagePage.getSearchButton());
        write(storagePage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = storagePage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test(groups = {"smoke"})
    public void brokenImageTest() {
        Log.info("Storage Page broken image test started...");
        SoftAssert softAssert = new SoftAssert();
        List<String> imageUrlList = storagePage.getImages().stream()
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
