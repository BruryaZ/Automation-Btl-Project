package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    public static ExtentReports extent;

    public static void initReport() {
        String timeStamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        // מוציאת את הקוד לקובץ HTML:
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport/ExtentReport" + timeStamp + ".html");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentReports getExtent() {
        if (extent == null) {
            initReport();
        }

        return extent;
    }

    public static void flushReport(){
        if(extent != null){
            extent.flush();
        }
    }
}
