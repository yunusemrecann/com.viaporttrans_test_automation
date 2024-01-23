package common.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import reports.ExtentReportsManager;

public class SuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        ExtentReportsManager.initExtentReports();
    }


}
