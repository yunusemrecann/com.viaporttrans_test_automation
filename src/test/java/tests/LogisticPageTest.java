package tests;

import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LogisticPage;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static org.testng.Assert.*;

public class LogisticPageTest extends BaseTest{

    private LogisticPage logisticPage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        logisticPage = new LogisticPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToLogisticPage() {
        Log.info("Opening " + ConfigReader.getProperty("logisticPageUrl") + "...");
        actions.moveToElement(homepage.getMainMenuServicesLogisticElement()).perform();
        click(homepage.getMainMenuServicesLogisticElement());
    }

    @Test(groups = {"smoke"})
    public void viaportLogoVisibilityTest() {
        Log.info("Logistic Page Viaport Logo Visibility Test Started");
        assertTrue(logisticPage.getViaportLogo().isDisplayed());
    }

    @Test(dependsOnMethods = "viaportLogoVisibilityTest")
    public void viaportLogoWorkingTest() {
        Log.info("Logistic Page Viaport Logo Working Test Started");
        click(logisticPage.getViaportLogo());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("Logistic Page Home Link Visibility Test Started");
        assertTrue(logisticPage.getHomeLink().isDisplayed());
    }

    @Test(dependsOnMethods = "homeLinkVisibilityTest")
    public void homeLinkWorkingTest() {
        Log.info("Logistic Page Home Link Working Test Started");
        click(logisticPage.getHomeLink());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("Logistic Page search button visibility test started...");
        waitVisibility(logisticPage.getSearchButton());
        assertTrue(logisticPage.getSearchButton().isDisplayed());
    }

    @Test
    public void searchInputVisibilityTest() {
        Log.info("Logistic Page search input working test started...");
        click(logisticPage.getSearchButton());
        assertTrue(logisticPage.getSearchInput().isDisplayed());
    }

    @Test
    public void searchCloseButtonVisibilityTest() {
        Log.info("Logistic Page search close button visibility test started...");
        click(logisticPage.getSearchButton());
        assertTrue(logisticPage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test
    public void searchCloseButtonWorkingTest() {
        Log.info("Logistic Page search close button working test started...");
        click(logisticPage.getSearchButton());
        click(logisticPage.getSiteSearchCloseButton());

    }

    @Test
    public void searchButtonWorkingPositiveTest() {
        Log.info("Logistic Page search button working positive test started...");
        click(logisticPage.getSearchButton());
        write(logisticPage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(logisticPage.getSearchPosts(), textContains, "About us");
    }

    @Test
    public void searchButtonWorkingNegativeTest() {
        Log.info("Logistic Page search button working negative test started...");
        click(logisticPage.getSearchButton());
        write(logisticPage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = logisticPage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void contactUsButtonVisibilityTest() {
        Log.info("Logistic Page contact us button visibility test started...");
        assertTrue(logisticPage.getContactUsButton().isDisplayed());
    }

    @Test
    public void contactUsButtonWorkingTest() {
        Log.info("Logistic Page contact us button working test started...");
        click(logisticPage.getContactUsButton());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("logisticPageUrl"));
    }

    @Test(groups = {"smoke"})
    public void brokenImageTest() {
        Log.info("Logistic Page broken image test started...");
        SoftAssert softAssert = new SoftAssert();
        List<String> imageUrlList = logisticPage.getImages().stream()
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
