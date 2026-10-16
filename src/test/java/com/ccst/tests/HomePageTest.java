package com.ccst.tests;

import com.ccst.base.BaseTest;
import com.ccst.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageLoadsSuccessfully() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.url());

        Assert.assertTrue(homePage.isLoaded(), "Home page did not load successfully");
        Assert.assertTrue(homePage.isLogoVisible(), "Logo is not visible on the home page");
        Assert.assertEquals(homePage.getFeaturesItemsTitle(), "FEATURES ITEMS");
    }
}
