package com.ccst.components;

import com.ccst.base.BasePage;
import com.ccst.pages.CartPage;
import com.ccst.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BasePage {
    private final By toHome = By.xpath("//a[text()=' Home']");
    private final By toProduct = By.cssSelector("a[href='/products']");
    private final By toCart = By.cssSelector("a[href='/view_cart']");
    private final By toLogin = By.cssSelector("a[href=\"/login\"]");


    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public String url() {
        return "https://automationexercise.com/";
    }

    @Override
    public boolean isLoaded() {
        return driver.findElement(toHome).isDisplayed();
    }

    public void gotoHome() {
        click(toHome);
    }


    public ProductPage gotoProducts() {
        ProductPage page=new ProductPage(driver);
        clinkLink(toProduct,page::isLoaded);
        return page;

    }

    public CartPage gotoCart() {
        CartPage page=new CartPage(driver);
        clinkLink(toCart,page::isLoaded);
        return page;

    }

    @FindBy(css = "a[href='/login']")
    private WebElement username;
    public void gotoLogin() {
        click(toLogin);
    }




}
