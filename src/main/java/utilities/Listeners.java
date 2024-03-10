package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

public class Listeners extends CommonOps implements ITestListener {
    public void onStart(ITestContext execution) {
        System.out.println("----------------- Starting Execution ---------------");
    }

    public void onFinish(ITestContext execution) {
        System.out.println("----------------- Ending Execution ---------------");
    }

    public void onTestSkipped(ITestResult test) {
        System.out.println("----------- Skipping Test: " + test.getName() + " -------------");
    }

    public void onTestStart(ITestResult test) {
        String[] testName = test.getName().split("(?=\\p{Upper})");
        String properTestName = String.join(" ", testName);
        System.out.println("----------- Starting Test: " + properTestName + " -------------");
    }

    public void onTestSuccess(ITestResult test) {
        String[] testName = test.getName().split("(?=\\p{Upper})");
        String properTestName = String.join(" ", testName);
        System.out.println("----------- Test: " + properTestName + " Passed -------------");

        try {
            MonteScreenRecorder.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File("./test-recordings/" + test.getName() + ".avi");
        if (!file.delete())
            System.out.println("Failed to Delete File");
    }

    public void onTestFailure(ITestResult test) {
        String[] testName = test.getName().split("(?=\\p{Upper})");
        String properTestName = String.join(" ", testName);
        System.out.println("----------- Test: " + properTestName + " Failed -------------");

        try {
            MonteScreenRecorder.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveScreenshot("Failed", "png");

    }

}
