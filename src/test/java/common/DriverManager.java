package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private DriverManager() {

    }

    public static WebDriver getDriver(){
        String browser = ConfigReader.getProperty("browser");
        if (DRIVER_THREAD_LOCAL.get() == null) {
            switch (browser) {
                case "firefox" :
                    DRIVER_THREAD_LOCAL.set(new FirefoxDriver());
                    break;
                case "safari" :
                    DRIVER_THREAD_LOCAL.set(new SafariDriver());
                    break;
                case "edge" :
                    DRIVER_THREAD_LOCAL.set(new EdgeDriver());
                    break;
                default:
                    DRIVER_THREAD_LOCAL.set(new ChromeDriver());
            }
        }
        return DRIVER_THREAD_LOCAL.get();
    }

    public static void closeDriver() {
        if (DRIVER_THREAD_LOCAL.get() != null) {
            DRIVER_THREAD_LOCAL.get().close();
            DRIVER_THREAD_LOCAL.remove();
        }
    }

    public static void quitDriver() {
        if (DRIVER_THREAD_LOCAL.get() != null) {
            DRIVER_THREAD_LOCAL.get().quit();
            DRIVER_THREAD_LOCAL.remove();
        }
    }
}
