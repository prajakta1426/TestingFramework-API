package com.ccst.pages;

import com.ccst.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final By allProducts= By.cssSelector(".title.text-center");
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String url() {
        return "https://automationexercise.com/";
    }

    @Override
    public boolean isLoaded() {
        //return driver.findElement(allProducts).isDisplayed();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProducts)).isDisplayed();
    }
}
