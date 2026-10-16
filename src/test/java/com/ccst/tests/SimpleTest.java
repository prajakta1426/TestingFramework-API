package com.ccst.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class SimpleTest {
    private static final Logger log = LogManager.getLogger(SimpleTest.class);

    @BeforeMethod
    public void testBeforeMethod()
    {
        log.info("in BeforeMethod");
    }
    @BeforeSuite
    public void testBeforeSuite()
    {
        log.info("in BeforeSuite");
    }
    @BeforeTest
    public void testBeforeTest()
    {
        log.info("in BeforeTest");
    }
    @BeforeClass
    public void testBeforeClass()
    {
        log.info("in BeforeClass");
    }
    @AfterMethod
    public void testAfterMethod()
    {
        log.info("in AfterMethod");
    }
    @AfterClass
    public void testAfterClass()
    {
        log.info("in AfterClass");
    }
    @AfterTest
    public void testAfterTest()
    {
        log.info("in AfterTest");
    }
    @AfterSuite
    public void testAfterSuite()
    {
        log.info("in AfterSuite");
    }
    @Test(dependsOnMethods = "test2")
    public void test1()
    {
        log.info("in test1");
    }
    @Test(groups = "smoke")
    public void test2()
    {
        log.info("in test2");
    }
}
