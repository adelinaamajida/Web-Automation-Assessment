package com.assessment.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends BasePage {
    private WebDriver driver;

    //Locators
    @FindBy(xpath = "//span[@class='confirmation card-title']")
    private WebElement lblWalletBalance;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Add new')]")
    private WebElement expandAddNewCard;

    @FindBy(xpath = "//mat-label[text()='Name']/ancestor::mat-form-field//input")
    private WebElement nameField;

    @FindBy(xpath = "//mat-label[contains(text(),'Card')]/ancestor::mat-form-field//input")
    private WebElement cardNumberField;

    @FindBy(xpath = "//mat-label[contains(text(),'Expiry Month')]/ancestor::div/select")
    private WebElement expiryMonthOptions;

    @FindBy(xpath = "//mat-label[contains(text(),'Expiry Year')]/ancestor::div/select")
    private WebElement expiryYearOptions;

    @FindBy(id = "submitButton")
    private WebElement btnSubmit;

    @FindBy(xpath = "//span[@class='mat-radio-outer-circle']")
    private WebElement radioBtnCard;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    private WebElement btnContinue;

    @FindBy(xpath = "//h1[@class='confirmation']")
    private WebElement lblSuccessfulPurchase;

    @FindBy(id = "checkoutButton")
    private WebElement btnPurchase;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Method
    public String getWalletBalance() {
        return lblWalletBalance.getText();
    }

    public void addNewCard() {
        expandAddNewCard.click();
    }

    public void setNameField(String input) {
        nameField.sendKeys(input);
    }

    public void setCardNumberField(String input) {
        cardNumberField.sendKeys(input);
    }

    public void selectExpiryMonth(String month) {
        Select select = new Select(expiryMonthOptions);
        select.selectByVisibleText(month);
    }

    public void selectExpiryYear(String year) {
        Select select = new Select(expiryYearOptions);
        select.selectByVisibleText(year);
    }

    public void fillNewCardForm() {
        setNameField("Mirana");
        setCardNumberField("8888555588885555");
        selectExpiryMonth("2");
        selectExpiryYear("2081");
    }

    public void submitNewCard() {
        btnSubmit.click();
    }

    public void selectNewCard() {
        radioBtnCard.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public boolean verifySuccessfulPurchase() {
        return lblSuccessfulPurchase.isDisplayed();
    }

    public void clickBtnPurchase() {
        btnPurchase.click();
    }

}
