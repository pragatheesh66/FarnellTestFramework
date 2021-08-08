package com.Farnell.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

public class TestRunProperties {
    private Logger logger = Logger.getLogger(TestRunProperties.class);
    private String testProjectLocation;
    private Properties properties;
    private final String PROPERTY_FILE_PATH= "/resources/configuration.properties";

    public TestRunProperties() {
        testProjectLocation = System.getProperty("user.dir");
        try (InputStream input = new FileInputStream(testProjectLocation + PROPERTY_FILE_PATH)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            logger.error("configuration.properties not found at " + testProjectLocation + PROPERTY_FILE_PATH);
            throw new RuntimeException("configuration.properties not found at " + testProjectLocation + PROPERTY_FILE_PATH);
        }
    }

    public String getChromeDriverPath(){
        return testProjectLocation + properties.getProperty("chrome.driver.path");
    }

    public String getIEDriverPath(){
        return testProjectLocation + properties.getProperty("ie.driver.path");
    }

    public String getGeckoDriverPath(){
        return testProjectLocation + properties.getProperty("gecko.driver.path");
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("default.implicit.wait").trim());
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("default.pageload.timeout").trim());
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("test.application.url");
        if(url != null) return url;
        else throw new RuntimeException("Application URL is not specified in the configuration.properties file.");
    }

    public Browser getBrowser(){
        switch(properties.getProperty("browser.name")){
            case "FIREFOX":
            case "firefox":
                return Browser.Firefox;
            case "ie":
            case "IE":
                return Browser.InternetExplorer;
            default:
                return Browser.Chrome;
        }
    }

}