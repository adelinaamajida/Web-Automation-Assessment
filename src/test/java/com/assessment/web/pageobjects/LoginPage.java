package com.assessment.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private WebDriver driver;
    //Locators
    @FindBy(xpath = "//a[contains(text(),'Not yet')]")
    private WebElement navigateToRegisterPage;
    @FindBy(id = "email")
    private WebElement loginEmailField;

    @FindBy(id = "password")
    private WebElement loginPasswordField;

    @FindBy(id = "loginButton")
    private WebElement btnLogin;

    @FindBy(xpath = "//span[contains(text(),'Registration completed')]")
    private WebElement registrationCompleteMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Method
    public void navigateToRegisterPage() {
        super.waitForElement(navigateToRegisterPage);
        navigateToRegisterPage.click();
    }

    public void setEmailField(String input) {
        loginEmailField.sendKeys(input);
    }

    public void setPasswordField(String input) {
        loginPasswordField.sendKeys(input);
    }

    public void submitLogin() {
        btnLogin.click();
    }

    public boolean registrationCompleted() {
        return registrationCompleteMessage.isDisplayed();
    }

}
