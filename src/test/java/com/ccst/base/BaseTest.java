package com.ccst.base;

import com.ccst.framework.DriverFactory;
import com.ccst.framework.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.xml.sax.XMLReader;

import javax.xml.stream.XMLStreamReader;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void _setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.create(browser);
        DriverManager.setDriver(driver);

        //driver=DriverManager.getDriver();


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Browser initialized and window maximized");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Closing the browser");
        DriverManager.quitDriver();
    }

    protected WebDriver driver()
    {
        return DriverManager.getDriver();
    }
}
