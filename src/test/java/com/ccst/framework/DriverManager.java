package com.ccst.framework;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager(){}

    public static void setDriver(WebDriver driver)
    {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver()
    {
        WebDriver driver = DRIVER.get();
        if(driver==null){
            throw new IllegalStateException("No web driver started");
        }
        return driver;
    }

    public static void quitDriver()
    {
        WebDriver driver = DRIVER.get();
        if(driver!=null){
            driver.quit();
            DRIVER.remove();
        }
    }
}
