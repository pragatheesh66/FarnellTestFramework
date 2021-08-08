package com.Farnell.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features="resources/features",
        glue="com.Farnell.stepDefinitions",
        tags = "@Positive or @Negative",
        plugin= {"pretty","html:FarnellTestReport.html"},

        monochrome=true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}