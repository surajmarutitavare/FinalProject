package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Class definition for managing Extent Reports for test reporting
public class ExtentManager {

    // Static ExtentReports instance to maintain single report instance throughout execution
    private static ExtentReports extent;

    // Static method to get or create ExtentReports instance (Singleton pattern)
    public static ExtentReports getInstance() {

        // Check if extent instance is null (not initialized yet)
        if (extent == null) {

            // Create ExtentSparkReporter to generate HTML report file
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("Reports/PractoReport.html");

            // Set the HTML report title
            spark.config().setDocumentTitle("Practo Automation");

            // Set the HTML report name
            spark.config().setReportName("Practo Test Execution Report");

            // Initialize ExtentReports object
            extent = new ExtentReports();
            // Attach the spark reporter to extent reports
            extent.attachReporter(spark);
        }

        // Return the ExtentReports instance
        return extent;
    }
// End of ExtentManager class
}