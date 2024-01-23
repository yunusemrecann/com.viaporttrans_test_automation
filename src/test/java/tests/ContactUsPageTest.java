package tests;

import com.github.javafaker.Faker;
import common.ConfigReader;
import common.logs.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactUsPage;

import java.util.function.Predicate;

import static org.testng.Assert.*;

public class ContactUsPageTest extends BaseTest{

    private ContactUsPage contactUsPage;

    @BeforeClass
    public void init() {
        contactUsPage = new ContactUsPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToAboutUsPage() {
        Log.info("Opening " + ConfigReader.getProperty("contactUsPageUrl") + "...");
        click(homepage.getMainMenuContactUsElement());
    }

    @Test(groups = {"smoke"})
    public void viaportLogoVisibilityTest() {
        Log.info("Contact Us Page Viaport Logo Visibility Test Started");
        assertTrue(contactUsPage.getViaportLogo().isDisplayed());
    }

    @Test(dependsOnMethods = "viaportLogoVisibilityTest")
    public void viaportLogoWorkingTest() {
        Log.info("Contact Us Page Viaport Logo Working Test Started");
        click(contactUsPage.getViaportLogo());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test(groups = {"smoke"})
    public void homeLinkVisibilityTest() {
        Log.info("Contact Us Page Home Link Visibility Test Started");
        assertTrue(contactUsPage.getHomeLink().isDisplayed());
    }

    @Test(dependsOnMethods = "homeLinkVisibilityTest")
    public void homeLinkWorkingTest() {
        Log.info("Contact Us Page Home Link Working Test Started");
        click(contactUsPage.getHomeLink());
        assertEquals(getDriver().getCurrentUrl(),ConfigReader.getProperty("homepageUrl"));
    }

    @Test
    public void searchButtonVisibilityTest() {
        Log.info("Contact Us Page search button visibility test started...");
        waitVisibility(contactUsPage.getSearchButton());
        assertTrue(contactUsPage.getSearchButton().isDisplayed());
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchInputVisibilityTest() {
        Log.info("Contact Us Page search input working test started...");
        click(contactUsPage.getSearchButton());
        assertTrue(contactUsPage.getSearchInput().isDisplayed());
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchCloseButtonVisibilityTest() {
        Log.info("Contact Us Page search close button visibility test started...");
        click(contactUsPage.getSearchButton());
        assertTrue(contactUsPage.getSiteSearchCloseButton().isDisplayed());
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchCloseButtonWorkingTest() {
        Log.info("Contact Us Page search close button working test started...");
        click(contactUsPage.getSearchButton());
        click(contactUsPage.getSiteSearchCloseButton());

    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchButtonWorkingPositiveTest() {
        Log.info("Contact Us Page search button working positive test started...");
        click(contactUsPage.getSearchButton());
        write(contactUsPage.getSearchInput(), "about us", Keys.ENTER);
        Predicate<WebElement> textContains = x -> x.getText().toLowerCase().contains("about us");
        assertListContains(contactUsPage.getSearchPosts(), textContains, "About us");
    }

    @Test(dependsOnMethods = "searchButtonVisibilityTest")
    public void searchButtonWorkingNegativeTest() {
        Log.info("Contact Us Page search button working negative test started...");
        click(contactUsPage.getSearchButton());
        write(contactUsPage.getSearchInput(), "negative", Keys.ENTER);
        String expectedResult = "No results found for: negative";
        String actualResult = contactUsPage.getSearchResult().getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void contactUsFormTest() throws InterruptedException {
        Log.info("Contact Us page Contact Us Form Test started.");
        Faker faker = new Faker();
        contactUsPage.getName().sendKeys(faker.name().name());
        contactUsPage.getSubject().sendKeys(faker.random().toString());
        contactUsPage.getEmail().sendKeys(faker.internet().emailAddress());
        contactUsPage.getMessage().sendKeys(faker.random().toString());
        Thread.sleep(5000);

        click(contactUsPage.getSendMessageButton());

    }
}
