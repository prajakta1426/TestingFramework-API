package com.ccst.pages;

import com.ccst.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final By emailElement = By.cssSelector("[data-qa='login-email']");
    private final By passwordElement = By.cssSelector("[data-qa='login-password']");
    private final By loginButton = By.cssSelector("[data-qa='login-button']");
    private final By loggedInMessage = By.xpath("//a[text()=' Logged in as ']");
    private final By loginErrorMessage = By.cssSelector("input[data-qa='login-password'] + p");
    //private final By loginErrorMessage=By.cssSelector("#form > div > div > div.col-sm-4.col-sm-offset-1 > div > form > p");
    public WebDriverWait wait;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public String url() {
        return "https://automationexercise.com/login";
    }

    @Override
    public boolean isLoaded(){
        //return driver.findElement(loginButton).isEnabled();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isEnabled();
    }


    public void login(String email, String password)
    {
        enterText(emailElement,email);
        enterText(passwordElement,password);
        click(loginButton);
    }

    public boolean isUserLoggedIn()
    {
        //return driver.findElement(loggedInMessage).isDisplayed();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInMessage)).isDisplayed();
    }

    public String getLoginErrorMessage()
    {
        //return driver.findElement(loginErrorMessage).getText();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage)).getText();
    }
}
