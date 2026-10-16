package com.ccst.tests;

import com.ccst.base.BaseTest;
import com.ccst.components.HeaderComponent;
import com.ccst.data.TestData;
import com.ccst.data.TestUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.ccst.pages.LoginPage;
import com.ccst.exception.FrameworkException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;

public class LoginPageTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(LoginPageTest.class);
    LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
       // super._setUp("chrome");
        loginPage = new LoginPage(driver);
    }


    @Test(groups = "smoke")
    public void validateLoginPage(){

        driver.get(loginPage.url());
        Assert.assertTrue(loginPage.isLoaded());

        loginPage.login("message.here0102@gmail.com","null7890");
        Assert.assertTrue(loginPage.isUserLoggedIn());
    }

    @Test(dataProvider = "invalidsLoginCsv", dataProviderClass = TestData.class)
    public void invalidLoginFromCsv(TestUser user)
    {
        invalidLoginShowsError(user);
    }


    @Test(dataProvider = "invalidsLoginExcel", dataProviderClass = TestData.class)
    public void invalidLoginFromExcel(TestUser user)
    {
        invalidLoginShowsError(user);
    }

    public void invalidLoginShowsError(TestUser user) {

        driver.get(loginPage.url());
        Assert.assertTrue(loginPage.isLoaded());

        loginPage.login(user.email(), user.password());
        String message = loginPage.getLoginErrorMessage();
        Assert.assertEquals(message,user.expectedMessage());
    }



    /*static {
        try {
            Files.createDirectories(Paths.get("logs"));
        } catch (Exception e) {
            System.err.println("Unable to create logs directory: " + e.getMessage());
        }
    }

    public void loadTestData(String filePath) {
        try {
            String data = new String(Files.readAllBytes(Paths.get(filePath)));
            log.info("Using file: {}", filePath);
            if (data.isEmpty()) {
                throw new FrameworkException("Test data file is empty: " + filePath);
            }
            log.info("Test data loaded successfully.");
            System.out.println("Test data loaded successfully: " + data);
        } catch (NoSuchFileException e) {
            log.error("File not found at path: {}", filePath, e);
            throw new FrameworkException("File not found at path: " + filePath);
        } catch (Exception e) {
            log.error("Error reading file: {}", e.getMessage(), e);
            throw new FrameworkException("Error reading file: " + e.getMessage());
        }
    }*/

}
