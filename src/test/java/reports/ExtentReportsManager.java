
package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.Getter;

@Getter
public class ExtentReportsManager {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    public static void initExtentReports() {
        String path = System.getProperty("user.dir") + "\\target\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("My Report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author","Yunus");
    }

    public static void createTest(String name) {
        extentTestThreadLocal.set(extentReports.createTest(name));
    }

    public static void pass(String message) {
        extentTestThreadLocal.get().log(Status.PASS, message);
    }

    public static void fail(String message, String base64Screenshot,String title) {
        extentTestThreadLocal.get().log(Status.FAIL, message);
        extentTestThreadLocal.get().addScreenCaptureFromBase64String(base64Screenshot,title);
    }

    public static void skip(String message) {
        extentTestThreadLocal.get().log(Status.SKIP, message);
    }

    public static void flush() {
        extentReports.flush();
    }
}


