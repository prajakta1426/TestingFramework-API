package com.ccst.pages;

import com.ccst.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    private final By checkCart = By.cssSelector("#cart_items li.active");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String url() {
        return "https://automationexercise.com/";
    }

    @Override
    public boolean isLoaded() {
        //return driver.findElement(checkCart).isDisplayed();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkCart)).isDisplayed();
    }
}
