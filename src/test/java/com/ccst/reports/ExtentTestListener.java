package com.ccst.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.ccst.framework.Config;
import com.ccst.framework.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ISuiteListener , ITestListener {
    private static final  ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite){
        ExtentReports extent = ExtentManager.createInstance(suite.getName());

        extent.setSystemInfo("Browser",valueOrConfig(suite.getParameter("browser"),"browser"));
        extent.setSystemInfo("Environment",valueOrConfig(suite.getParameter("env"),"env"));
        extent.setSystemInfo("Tester", Config.get("tester"));
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("Java",System.getProperty("java.version"));
    }

    @Override
    public void onFinish(ISuite suite){
        ExtentManager.flush();
        System.out.println("Extent report :" + ExtentManager.getReportFile().toAbsolutePath());
    }

    @Override
    public void onTestStart(ITestResult result){
        startEntry(result);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        entry(result).pass("Passed");
        TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result){
        entry(result).skip(result.getThrowable() != null ? result.getThrowable().toString() : "Skipped");
        TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = entry(result);


        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        } else {
            test.fail("Test failed without a thrown exception.");
        }


        attachScreenshot(test);

        TEST.remove();
    }


    private ExtentTest startEntry(ITestResult result){
        ExtentTest test=ExtentManager.getInstance().createTest(testName(result),result.getMethod().getMethodName());

        String[] groups = result.getMethod().getGroups();
        if(groups.length > 0 ){
            test.assignCategory(groups);
        }
        String browser = result.getTestContext().getCurrentXmlTest().getAllParameters().get("browser");
        test.assignDevice(valueOrConfig(browser,"browser"));

        TEST.set(test);
        return  test;
    }

    private ExtentTest entry(ITestResult result){
        ExtentTest test = TEST.get();
        return (test!=null) ? test : startEntry(result);
    }

    private static String testName(ITestResult result){
        String name = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if(parameters.length == 0){
            return name;
        }
        return name;
    }

    private static String valueOrConfig(String value, String configKey){
        return (value!=null && !value.isBlank()) ? value : Config.get(configKey);
    }

    private static void attachScreenshot(ExtentTest test) {
        try {
            String base64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
            test.fail("Screenshot at the moment of failure", MediaEntityBuilder.createScreenCaptureFromBase64String(base64, "Failure Screenshot").build());
        } catch (Exception e) {
            test.warning("Could not take a screenshot: " + e.getMessage());
        }
    }

}
