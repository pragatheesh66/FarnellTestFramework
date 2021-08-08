package com.Farnell.pages;

import org.openqa.selenium.By;

public class LoginPage {
	
	//Locators
	public static final By txtUserName = By.xpath("//input[@id='logonId']");
    public static final By txtPassword = By.xpath("//input[@id='logonPassword']");
    public static final By btnLogIn= By.xpath("input[@value='Log In']");
    public static final By lnkForgotPassword = By.xpath("//a[text()='Forgotten your password?']");
    public static final By errorUserNameBlank = By.xpath("//label[@for= 'logonId']");
    public static final By errorPasswordBlank = By.xpath("//label[@for= 'logonPassword']");
    public static final By errorSignIn = By.xpath("//p[@class = 'invalidLoginError']");
    public static final By errorHeader = By.xpath("//p[@id = 'errorHeader']");
    public static final By chkRememberMe = By.xpath("//input[@id='rememberMe']");
    public static final By hdrAccountSummary = By.xpath("//h1[text()='My Account Summary']");

}
