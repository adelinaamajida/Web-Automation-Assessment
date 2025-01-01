package com.assessment.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.UUID;
import java.util.Random;

public class RegistrationPage extends BasePage {
    private WebDriver driver;
    // Locators

    @FindBy(id = "emailControl")
    private WebElement emailField;

    @FindBy(id = "passwordControl")
    private WebElement passwordField;

    @FindBy(id = "repeatPasswordControl") // Assuming ID for confirm password input
    private WebElement repeatPasswordField;

    @FindBy(xpath = "//span[text()='Show password advice']") // Show password advice button
    private WebElement showPasswordAdviceButton;

    @FindBy(id = "mat-select-value-3")
    private WebElement securityQuestionField;

    @FindBy(xpath = "//span[@class='mat-option-text' and contains(text(),'middle name')]")
    private WebElement chooseMiddleName;

    @FindBy(id = "securityAnswerControl")
    private WebElement answerSecurityQuestionField;

    @FindBy(xpath = "//button[@id='registerButton']")
    private WebElement btnRegister;

    @FindBy(xpath = "//h1[contains(text(),'Login')]")
    private WebElement verifyToLoginPage;



    //Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String[] generateEmailPassword() {
        String generatedEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        String generatedPassword = generatePassword();

        return new String[]{generatedEmail, generatedPassword}; // Return email and password
    }

    // Filling data on Registration Page
    public void fillOutRegistrationForm(String[] user) {


        setEmailField(user[0]);
        setPasswordField(user[1]);
        setRepeatPasswordField(user[1]);
        switchTogglePasswordAdvice();
        chooseSecurityQuestions();
        setAnswerSecurityQuestionField("middle");

//
    }

    // Generate a random password by fulfilling the requirements
    private String generatePassword() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*";
        String allChars = upperCase + lowerCase + numbers + specialChars;

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        // Ensure password contains at least one character from each category
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Fill the rest with random characters from all categories
        for (int i = 4; i < 12; i++) { // Generate a 12-character password
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString();
    }

    //Methods

    public void setEmailField(String input) {
        emailField.sendKeys(input);
    }

    public void setPasswordField(String input) {
        passwordField.sendKeys(input);
    }

    public void setRepeatPasswordField(String input) {
        repeatPasswordField.sendKeys(input);
    }

    public void switchTogglePasswordAdvice() {
        showPasswordAdviceButton.click();
    }

    public void chooseSecurityQuestions() {
        securityQuestionField.click();
        chooseMiddleName.click();
    }

    public void setAnswerSecurityQuestionField(String input) {
        answerSecurityQuestionField.sendKeys(input);
    }

    public void submitNewAccount() {
        btnRegister.click();
    }

    // Trigger validation messages by clicking on inputs without entering data
    public void triggerValidationMessages() {
        clickElement(emailField);
        clickElement(passwordField);
        clickElement(repeatPasswordField);
        clickElement(btnRegister);
    }

    // Assert validation messages exist
    public boolean ifValidationMessagesDisplayed() {
        return !getValidationMessage(emailField).isEmpty()
                && !getValidationMessage(passwordField).isEmpty()
                && !getValidationMessage(repeatPasswordField).isEmpty();
    }

    // Verify register successful by entering login page
    public boolean isDirectedToLoginPage() {
        return waitForElement(verifyToLoginPage).isDisplayed();
    }
}