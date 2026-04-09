package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static ExtentReports extent;

	public static ExtentReports getInstance() {
		if(extent ==null) {
			String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("SauceDemo Automation Report");
			reporter.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(reporter);

			extent.setSystemInfo("Tester", "Rajnish");
            extent.setSystemInfo("Environment", "QA");
		}
		return extent;

	}

}
