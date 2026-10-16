package com.ccst.tests;

import com.ccst.base.BasePage;
import com.ccst.base.BaseTest;
import com.ccst.components.HeaderComponent;
import com.ccst.pages.CartPage;
import com.ccst.pages.LoginPage;
import com.ccst.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestHeaderComponent extends BaseTest {
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void initPage() {
        //super._setUp("chrome");
        loginPage = new LoginPage(driver);
    }
    @Test
    public void testProductPage(){
        driver.get(loginPage.url());
        Assert.assertTrue(loginPage.isLoaded());

        loginPage.header().gotoLogin();
        loginPage.login("message.here0102@gmail.com","null7890");
        productPage=loginPage.header().gotoProducts();
        Assert.assertTrue(productPage.isLoaded());

    }

    @Test
    public void testCartPage(){
        driver.get(loginPage.url());
        Assert.assertTrue(loginPage.isLoaded());

        loginPage.header().gotoLogin();
        loginPage.login("message.here0102@gmail.com","null7890");
        cartPage=loginPage.header().gotoCart();
        Assert.assertTrue(cartPage.isLoaded());

    }
}
