package reports;

import com.aventstack.extentreports.ExtentTest;

// ניהול דוחות בצורה מקבילית
public class ExtentReportManager {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setTest(ExtentTest extentTest){
        test.set(extentTest);
    }

    public static ExtentTest getTest(){
        return test.get();
    }
}
