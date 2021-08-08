package com.Farnell.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import com.Farnell.utils.TestContext;
import com.Farnell.utils.WebDriverUtils;

public class RegisterPage {
	
	//Locators
    public static final By txtUserName = By.cssSelector("input[id='logonId']");
    public static final By txtPassword = By.cssSelector("input[id='logonPassword']");
    public static final By txtFullName = By.cssSelector("input[id='firstName']");
    public static final By txtEmail = By.cssSelector("input[id='email1']");
    public static final By btnRegister = By.cssSelector("input[id='registerValidate']");

}
