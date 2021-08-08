package com.Farnell.stepDefinitions;

import com.Farnell.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void BeforeSteps() {
        testContext.getWebDriverUtils().initializeWebDriver();
    }

    @After
    public void AfterSteps() {
        testContext.getWebDriverUtils().quitBrowser();
    }
}