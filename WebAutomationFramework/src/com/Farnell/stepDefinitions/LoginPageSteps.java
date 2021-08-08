package com.Farnell.stepDefinitions;

import com.Farnell.pages.*;
import com.Farnell.utils.TestContext;
import com.Farnell.utils.WebDriverUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class LoginPageSteps extends HomePage{
    private Logger logger = Logger.getLogger(LoginPageSteps.class);
    public TestContext testContext;
    private WebDriverUtils webDriverUtils;


    public LoginPageSteps(TestContext testContext) {
        this.testContext = testContext;
        this.webDriverUtils = testContext.getWebDriverUtils();
    }
    
    @Given("User is on Farnell Home Page")
    public void launchFarnellSite() {
        webDriverUtils.launchApplicationUrl();
        webDriverUtils.checkElementIsPresent(HomePage.lnkMyAccount,
                "Home page is not displayed");
    }
    
    @When("User clicks Register button and navigates to Registration page")
    public void navigateToRegistrationPage() {
        webDriverUtils.clickLinkOrButton(lnkRegister, "Login button in HomePage");
        logger.info("Navigated to user registration page ");
    }
    
    @When("User clicks Login button and navigates to Login page")
    public void navigateToLoginPage() {
        webDriverUtils.clickLinkOrButton(lnkLogin, "Login button in HomePage");
        logger.info("Navigated to user Log in page ");
    }
    

    @When("User enters Username {string} and Password {string}")
    public void provideCredentials(String userName, String password) {
        webDriverUtils.enterText(LoginPage.txtUserName, userName, "Username Textbox");
        webDriverUtils.enterText(LoginPage.txtPassword, password, "Password Textbox");
    }
    
    @When("User {string} remember me button and clicks log in button")
    public void CheckOrUncheckRememberMeCheckbox(String rememberMe) {
        webDriverUtils.launchApplicationUrl();
        webDriverUtils.checkOrUncheckElement(LoginPage.chkRememberMe, rememberMe, "Remember me checkbox");
        webDriverUtils.clickLinkOrButton(LoginPage.btnLogIn, "Log in Button");
    }

    @When("User clicks LogIn")
    public void submitLogin() {
        webDriverUtils.clickLinkOrButton(LoginPage.btnLogIn, "Log in Button");
    }
    
    @When("User forgot password and clicks forgot password link")
    public void navigateForgotPasswordLink() {
        webDriverUtils.clickLinkOrButton(LoginPage.lnkForgotPassword, "Forgotten password Link");
    }
    
    @When("Logout from application and log in again")
    public void LogoutAndLoginAgain() {
        webDriverUtils.clickLinkOrButton(HomePage.lnkMyAccount, "My account Link");
        webDriverUtils.clickLinkOrButton(HomePage.lnkLogout, "Logout Link");
        webDriverUtils.clickLinkOrButton(HomePage.lnkLogin, "Login Link");
    }

    @Then("User should login successfully to the Farnell site and navigate to account summary")
    public void validateAccountSummaryPage() {
        WebElement welcomeHeader = webDriverUtils.waitForElement(AccountSummaryPage.hdrAccountSummary, 20,
                "Login is not successful - Accounts Page is not loaded");
        logger.info("Welcome Header is displayed - " + welcomeHeader.getText());
        webDriverUtils.checkElementIsPresent(AccountSummaryPage.hdrAccountSummary,
                "Account Header section is not displayed");
    }
    
    @Then("User should register successfully to the Farnell site")
    public void validateUserRegistration() {
        WebElement welcomeHeader = webDriverUtils.waitForElement(AccountSummaryPage.hdrAccountSummary, 20,
                "Login is not successful - Accounts Page is not loaded");
        logger.info("Welcome Header is displayed - " + welcomeHeader.getText());
        webDriverUtils.checkElementIsPresent(AccountSummaryPage.hdrAccountSummary,
                "Account Header section is not displayed");
    }
   

    @Then("User should be allowed to request a reset password email")
    public void validateForgotPasswordPage() {
        webDriverUtils.waitForElement(ResetAccessPage.btnRequestReset, 20,
                "Forgotten Password - Reset Access Page is not loaded");
        webDriverUtils.checkElementIsPresent(ResetAccessPage.txtEmail,
                "Reset Access - User Email text box is not displayed");
        webDriverUtils.checkElementIsPresent(ResetAccessPage.btnRequestReset,
                "Reset Access - Reset Request Button is not displayed");
    }

    @Then("User should get {string} SignIn error message and {string} Warning Message")
    public void validateLoginErrorMessage(String errorMessage, String warningMessage) {
        WebElement errorMsgElement = webDriverUtils.findElement(LoginPage.errorSignIn, "Incorrect username name and password Sign In Error");
        webDriverUtils.staticWait(2);
        webDriverUtils.waitForPageLoad();
        Assert.assertEquals(errorMsgElement.getText(), errorMessage + "\n" + warningMessage);
    }

    @Then("User should get {string} error message for username and password fields")
    public void validateEmptyCredentialsSignInErrorMessage(String errorMessage) {
        WebElement usernameBlankError = webDriverUtils.findElement(LoginPage.errorUserNameBlank, "Login Page Username Blank Error");
        WebElement passwordBlankError = webDriverUtils.findElement(LoginPage.errorPasswordBlank, "Login Page Password Blank Error");
        Assert.assertEquals(usernameBlankError.getText(), errorMessage);
        Assert.assertEquals(passwordBlankError.getText(), errorMessage);
    }
    
    @Then("Username {string} should be auto populated")
    public void validateFieldUsernameIsAutoPopulated(String username) {
        WebElement usernameText = webDriverUtils.findElement(LoginPage.txtUserName, "Username field");
        Assert.assertEquals(usernameText.getText(), username);
    }
    
    @Then("Username should be empty")
    public void validateFieldUsernameIsEmpty(String errorMessage) {
        WebElement usernameText = webDriverUtils.findElement(LoginPage.txtUserName, "Username field");
        Assert.assertEquals(usernameText.getText(), "");
    }

    @When("User clicks Register")
    public void userClicksRegister() {
        webDriverUtils.clickLinkOrButton(RegisterPage.btnRegister, "Register account button");
    }
}
