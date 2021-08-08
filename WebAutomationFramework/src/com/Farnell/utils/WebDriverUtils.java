package com.Farnell.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    public static Logger logger = Logger.getLogger(WebDriverUtils.class);
    public WebDriver driver;
    public TestRunProperties testRunProperties;

    public WebDriverUtils(TestRunProperties testRunProperties) {
        this.testRunProperties = testRunProperties;
    }

    public WebDriver initializeWebDriver() {
        if(driver != null) return driver;
        Browser browser = testRunProperties.getBrowser();
        switch (browser) {
            case Chrome:
                System.setProperty("webdriver.chrome.driver", testRunProperties.getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            case Firefox:
                System.setProperty("webdriver.gecko.driver",testRunProperties.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            case InternetExplorer:
                System.setProperty("webdriver.ie.driver",testRunProperties.getIEDriverPath());
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("Unsupported Browser Type - " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(testRunProperties.getImplicitWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(testRunProperties.getPageLoadTimeout(), TimeUnit.SECONDS);
        logger.info(browser.getBrowserName() + " Browser is launched");
        return driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void launchApplicationUrl() {
        driver.get(testRunProperties.getApplicationUrl());
        logger.info("Application URL is loaded");
    }

    public WebElement findElement(By byElement, String elementDesc) {
        WebElement element = null;
        try {
            element = driver.findElement(byElement);
        }
        catch (NoSuchElementException e) {
            logger.error(elementDesc + " Element is not available");
            Assert.fail(elementDesc + " Element is not available");
        }
        return element;
    }

    public void enterText(By byElement, String textToEnter, String elementDesc) {
        try {
            WebElement element = driver.findElement(byElement);
            if (element.isDisplayed()) {
                element.clear();
                element.sendKeys(textToEnter);
                staticWait(1); // For Demo - added static wait
                logger.info("Text(" + textToEnter + ") is entered in " + elementDesc);
            }
            else {
                logger.error(elementDesc + " Element is not visible");
                Assert.fail(elementDesc + " Element is not visible");
            }
        }
        catch (NoSuchElementException e) {
            logger.error(elementDesc + " Element is not available");
            Assert.fail(elementDesc + " Element is not available");
        }
    }

    public void clickLinkOrButton(By byElement, String linkDesc) {
        try {
            WebElement element = driver.findElement(byElement);
            if (element.isDisplayed()) {
                element.click();
                logger.info(linkDesc + " is clicked successfully");
            }
            else {
                logger.error(linkDesc + " Element is not visible");
                Assert.fail(linkDesc + " Element is not visible");
            }
        }
        catch (NoSuchElementException e) {
            logger.error(linkDesc + " Element is not available");
            Assert.fail(linkDesc + " Element is not available");
        }
    }

    public void checkOrUncheckElement(By byElement, String check, String elementDesc){
        try {
            WebElement element = driver.findElement(byElement);
            if (element.isDisplayed()) {
                if(check.equalsIgnoreCase("check")){
                    if (!element.isSelected()) {
                        element.click();
                    }
                    logger.info(elementDesc + " is checked successfully");
                }else if(check.equalsIgnoreCase("uncheck")) {
                    if (element.isSelected()) {
                        element.click();
                    }
                    logger.info(elementDesc + " is Unchecked successfully");
                }
            }else{
                logger.error(elementDesc + " Element is not visible");
                Assert.fail(elementDesc + " Element is not visible");
            }
        }
        catch (NoSuchElementException e) {
            logger.error(elementDesc + " Element is not available");
            Assert.fail(elementDesc + " Element is not available");
        }
    }



    public void quitBrowser() {
        if(driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }

    public void staticWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }
        catch (InterruptedException e) {
            logger.error("Thread sleep interrupted");
        }
    }

    public WebElement waitForElement(By byElement, int timeoutInSeconds, String customErrorMessage) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
        }
        catch (TimeoutException e) {
            logger.error(customErrorMessage);
            Assert.fail(customErrorMessage);
        }
        return element;
    }

    public void checkElementIsPresent(By byElement, String customErrorMessage) {
        try {
            if(!driver.findElement(byElement).isDisplayed()) {
                Assert.fail(customErrorMessage);
            }
        }
        catch (NoSuchElementException e) {
            logger.error(customErrorMessage);
            Assert.fail(customErrorMessage);
        }
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, testRunProperties.getPageLoadTimeout());
        wait.until((driver) -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }
}
