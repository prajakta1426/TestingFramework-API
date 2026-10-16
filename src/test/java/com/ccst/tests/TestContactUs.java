package com.ccst.tests;

import com.ccst.base.BaseTest;
import com.ccst.pages.ContactUsPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestContactUs extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TestContactUs.class);

    ContactUsPage page ;
    WebDriverWait wait;
    String message;

    @BeforeMethod
    public void init(){
        page=new ContactUsPage(driver);

        driver.get("https://automationexercise.com/contact_us");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));


    }
    @Test
    public void testContactUsPage(){
        page.addDetails("John Doe", "john.doe@example.com", "Test Subject", "Test Message");

        Alert submitAlert = wait.until(ExpectedConditions.alertIsPresent());
        submitAlert.accept();
        log.info("Contact Us form submitted successfully");

        message =page.getErrorMessageText();
        Assert.assertEquals(message,"Success! Your details have been submitted successfully.");
        log.info("Success message verified successfully");
    }


}
