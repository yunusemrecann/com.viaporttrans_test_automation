package tests;

import common.ConfigReader;
import common.DriverManager;
import common.logs.Log;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.Homepage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

@Getter
public class BaseTest {

    protected static WebDriverWait webDriverWait;
    protected static JavascriptExecutor jsx;
    protected static Actions actions;
    protected Homepage homepage;
    protected HttpURLConnection httpURLConnection;
    private static WebDriver driver;


    @BeforeClass(alwaysRun = true)
    public static void setUp() {
        driver = DriverManager.getDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Log.info("Driver is maximized");
        driver.manage().window().maximize();
        jsx = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void goToHomepage() {
        homepage = new Homepage();
        Log.info("Opening " + ConfigReader.getProperty("homepageUrl") + " ...");
        driver.get(ConfigReader.getProperty("homepageUrl"));
    }

    @AfterClass(alwaysRun = true)
    public static void tearDown() {
        Log.info("All tests completed.");
        DriverManager.quitDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public WebElement waitVisibility(WebElement element) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitVisibility(element).click();
    }

    public void jsClick(WebElement element) {
        jsx.executeScript("arguments[0].click();",element);
    }

    public String getUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    public void write(WebElement element, String text) {
        waitVisibility(element).sendKeys(text);
    }

    public void write(WebElement element, String text, Keys key) {
        waitVisibility(element).sendKeys(text,key);
    }

    public String read(WebElement element) {
        return waitVisibility(element).getText();
    }

    public void scrollDown(WebElement element) {
        jsx.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDownSetPixel(int x, int y) {
        jsx.executeScript("window.scrollBy(" + x + "," +y + ")");
    }

    public HttpURLConnection urlConnection(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
        } catch (IOException exception) {
            Log.warn("Connection failed.");
        }
        return httpURLConnection;
    }
}
