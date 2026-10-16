package com.ccst.base;

import com.ccst.components.HeaderComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;

public abstract class BasePage implements Page {

    protected final Logger log = LogManager.getLogger(getClass());
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public String url() {
        return "https://automationexercise.com/login";
    }

    protected void click(By locator) {
       // driver.findElement(locator).click();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void enterText(By locator, String text) {
       // driver.findElement(locator).sendKeys(text);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    protected boolean validateContent(By locator, String text) {
       // return driver.findElement(locator).getText().equals(text);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().equals(text);
    }

    protected void clinkLink(By locator, BooleanSupplier arrived) {
        click(locator);
        wait.until(ignored -> arrived.getAsBoolean());
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }
}
