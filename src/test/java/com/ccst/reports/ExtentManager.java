package com.ccst.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExtentManager {
    private static ExtentReports extent;
    private static Path reportFile;

    private ExtentManager(){

    }

    public static synchronized ExtentReports createInstance(String suiteName){
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String safeName=suiteName.replaceAll("[^A-Za-z0-9]+","-");
        reportFile=Path.of("reports",safeName+"-"+stamp+".html");

        try{
            Files.createDirectories(reportFile.getParent());
        }
        catch (Exception e){
            throw new IllegalStateException("could not create reports folder",e);
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(reportFile.toString());
        spark.config().setDocumentTitle("Automation Exercise - Test Report");
        spark.config().setReportName(suiteName);
        spark.config().setTheme(Theme.STANDARD);


        extent=new ExtentReports();
        extent.attachReporter(spark);
        return  extent;
    }

    public static synchronized  ExtentReports getInstance()
    {
        if(extent==null){
            throw new IllegalStateException("Extent report not started : is ExtentTestListener register ");
        }
        return extent;
    }

    public static synchronized Path getReportFile(){
        return reportFile;
    }

    public static synchronized void flush(){
        if(extent!=null){
            extent.flush();
        }
    }
}
