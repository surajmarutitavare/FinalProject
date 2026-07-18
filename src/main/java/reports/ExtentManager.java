package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("Reports/PractoReport.html");

            spark.config().setDocumentTitle("Practo Automation");
            spark.config().setReportName("Practo Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}