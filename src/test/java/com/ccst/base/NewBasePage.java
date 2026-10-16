package com.ccst.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewBasePage extends BasePage {

    public NewBasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String url() {
        return "https://automationexercise.com/";
    }

    @Override
    public boolean isLoaded() {
        return true;
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(WebElement element, String value) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        visibleElement.clear();
        visibleElement.sendKeys(value);
    }

    protected String textOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
}
