package tests;

import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AboutUsPage;

import java.util.function.Predicate;

import static org.testng.Assert.*;

public class AboutUsTest extends BaseTest{

    private AboutUsPage aboutUsPage;

    @BeforeClass
    public void init() {
        aboutUsPage = new AboutUsPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToAboutUsPage() {
        Log.info("Opening " + ConfigReader.getProperty("aboutUsPageUrl") + "...");
        click(homepage.getMainMenuAboutUsElement());
    }

    @Test(groups = {"smoke"})
    public void viaportLogoVisibilityTest() {
        Log.info("About Us Page Viaport Logo Visibility Test Started");
        assertTrue(aboutUsPage.getViaportLogo().isDisplayed());
    }

    @Test(dependsOnMethods = "viaportLogoVisibilityTest")
    public void viaportLogoWorkingTest() {
        Log.info("About Us Page Viaport Logo Working Test Started");
        click(aboutUsPage.getViaportLogo());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("About Us Page Home Link Visibility Test Started");
        assertTrue(aboutUsPage.getHomeLink().isDisplayed());
    }

    @Test(dependsOnMethods = "homeLinkVisibilityTest")
    public void homeLinkWorkingTest() {
        Log.info("About Us Page Home Link Working Test Started");
        click(aboutUsPage.getHomeLink());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("aboutUsPageUrl"));
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("About Us Page search button visibility test started...");
        waitVisibility(aboutUsPage.getSearchButton());
        assertTrue(aboutUsPage.getSearchButton().isDisplayed());
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchInputVisibilityTest() {
        Log.info("About Us Page search input working test started...");
        click(aboutUsPage.getSearchButton());
        assertTrue(aboutUsPage.getSearchInput().isDisplayed());
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchCloseButtonVisibilityTest() {
        Log.info("About Us Page search close button visibility test started...");
        click(aboutUsPage.getSearchButton());
        assertTrue(aboutUsPage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchCloseButtonWorkingTest() {
        Log.info("About Us Page search close button working test started...");
        click(aboutUsPage.getSearchButton());
        click(aboutUsPage.getSiteSearchCloseButton());

    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchButtonWorkingPositiveTest() {
        Log.info("About Us Page search button working positive test started...");
        click(aboutUsPage.getSearchButton());
        write(aboutUsPage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(aboutUsPage.getSearchPosts(), textContains, "About us");
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchButtonWorkingNegativeTest() {
        Log.info("About Us Page search button working negative test started...");
        click(aboutUsPage.getSearchButton());
        write(aboutUsPage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = aboutUsPage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void ourMissionButtonVisibilityTest() {
        Log.info("About us page our mission link visibility test started...");
        assertTrue(aboutUsPage.getOurMissionButton().isDisplayed());
    }

    @Test
    public void ourVisionButtonVisibilityTest() {
        Log.info("About us page our vision link visibility test started...");
        assertTrue(aboutUsPage.getOurVisionButton().isDisplayed());
    }

    @Test
    public void getAQuoteButtonVisibilityTest() {
        Log.info("About us page Get A Quote button visibility test started...");
        assertTrue(aboutUsPage.getGetAQuoteButton().isDisplayed());
    }

    @Test(dependsOnMethods = "getAQuoteButtonVisibilityTest")
    public void getAQuoteButtonWorkingTest(){
        Log.info("About us page Get A Quote button working test started...");
        scrollDown(aboutUsPage.getGetAQuoteButton());
        click(aboutUsPage.getGetAQuoteButton());
        assertEquals(getDriver().getCurrentUrl(), ConfigReader.getProperty("contactUsPageUrl"));
    }

}
