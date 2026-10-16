package com.ccst.pages;

import com.ccst.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactUsPage extends BasePage {
    private final By loginButton = By.cssSelector("[data-qa='login-button']");
    private final By name = By.cssSelector("[data-qa='name']");
    private final By email = By.cssSelector("[data-qa='email']");
    private final  By subject = By.cssSelector("[data-qa='subject']");
    private  final  By message = By.cssSelector("#message");
    private  final  By fileUpload =By.cssSelector("[name='upload_file']");
    private final  By submitBtn =By.cssSelector("[name='submit']");
    private final By ErrorMessage =By.cssSelector("#contact-page > div.row > div.col-sm-8 > div > div.status.alert.alert-success");

    public  ContactUsPage(WebDriver driver){
        super(driver);
    }
    @Override
    public String url() {
        return super.url();
    }

    @Override
    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isEnabled();
    }

    public void addDetails(String inputName, String inputEmail, String inputSubject, String inputMessage) {
        enterText(name, inputName);
        enterText(email, inputEmail);
        enterText(subject, inputSubject);
        enterText(message, inputMessage);
        click(submitBtn);
    }
    public String getErrorMessageText(){
        return driver.findElement(ErrorMessage).getText();
    }

}
