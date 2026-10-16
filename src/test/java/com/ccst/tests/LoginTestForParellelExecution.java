package com.ccst.tests;

import com.ccst.base.BaseTest;
import com.ccst.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestForParellelExecution extends BaseTest {

    @Test
    public void validateLoginPage(){
        System.out.println("Testing parallel execution");
    }
}
