package com.ccst.pages;

import com.ccst.base.NewBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends NewBasePage {

    private static final By FEATURES_ITEMS_TITLE = By.cssSelector("h2.title.text-center");
    private static final By CATEGORY_TITLE = By.cssSelector("div.left-sidebar h2");
    private static final By BRANDS_TITLE = By.cssSelector("div.brands_products h2");

    @FindBy(css = "a[href='/'] img")
    private WebElement logo;

    @FindBy(css = "a[href='/products']")
    private WebElement productsLink;

    @FindBy(css = "a[href='/view_cart']")
    private WebElement cartLink;

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginLink;

    @FindBy(css = "a[href='/test_cases']")
    private WebElement testCasesLink;

    @FindBy(css = "a[href='/api_list']")
    private WebElement apiTestingLink;

    @FindBy(css = "a[href='https://www.youtube.com/c/AutomationExercise']")
    private WebElement videoTutorialsLink;

    @FindBy(css = "a[href='/contact_us']")
    private WebElement contactUsLink;

    @FindBy(css = "h2.title.text-center")
    private WebElement featuresItemsTitle;

    @FindBy(css = "div.left-sidebar h2")
    private WebElement categoryTitle;

    @FindBy(css = "div.brands_products h2")
    private WebElement brandsTitle;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String url() {
        return "https://automationexercise.com/";
    }

    @Override
    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FEATURES_ITEMS_TITLE)).isDisplayed();
    }

    public boolean isLogoVisible() {
        return wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
    }

    public String getFeaturesItemsTitle() {
        return textOf(featuresItemsTitle);
    }

    public String getCategoryTitle() {
        return textOf(categoryTitle);
    }

    public String getBrandsTitle() {
        return textOf(brandsTitle);
    }

    public HomePage openProducts() {
        click(productsLink);
        return this;
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public LoginPage openSignupLogin() {
        click(signupLoginLink);
        return new LoginPage(driver);
    }

    public HomePage openTestCases() {
        click(testCasesLink);
        return this;
    }

    public HomePage openApiTesting() {
        click(apiTestingLink);
        return this;
    }

    public HomePage openVideoTutorials() {
        click(videoTutorialsLink);
        return this;
    }

    public ContactUsPage openContactUs() {
        click(contactUsLink);
        return new ContactUsPage(driver);
    }

    public HomePage addToCart(int productId) {
        click(addToCartButton(productId));
        return this;
    }

    public ProductPage openProduct(int productId) {
        click(viewProductLink(productId));
        return new ProductPage(driver);
    }

    private By addToCartButton(int productId) {
        return By.cssSelector("a[data-product-id='" + productId + "'].add-to-cart");
    }

    private By viewProductLink(int productId) {
        return By.cssSelector("a[href='/product_details/" + productId + "']");
    }
}