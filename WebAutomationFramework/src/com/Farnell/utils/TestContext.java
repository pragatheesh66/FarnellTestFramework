package com.Farnell.utils;

public class TestContext {
    public static TestRunProperties testRunProperties;
    public WebDriverUtils webDriverUtils;

    static {
        testRunProperties = new TestRunProperties();
    }

    public TestContext() {
        webDriverUtils = new WebDriverUtils(testRunProperties);
    }

    public WebDriverUtils getWebDriverUtils() {
        return webDriverUtils;
    }

    public TestRunProperties getTestRunProperties() {
        return testRunProperties;
    }



}
