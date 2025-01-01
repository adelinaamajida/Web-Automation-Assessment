package com.assessment.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    private WebDriver driver;

    //Locators
    @FindBy(xpath = "//button[@aria-label='Add a new address']")
    private WebElement btnAddNewAddress;

    @FindBy(xpath = "//input[@placeholder='Please provide a country.']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@placeholder='Please provide a name.']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Please provide a mobile number.']")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//input[@placeholder='Please provide a ZIP code.']")
    private WebElement zipCodeField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(xpath = "//input[@placeholder='Please provide a city.']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@placeholder='Please provide a state.']")
    private WebElement stateField;

    @FindBy(id = "submitButton")
    private WebElement btnSubmitNewAddress;

    @FindBy(xpath = "//mat-radio-button")
    private WebElement radioBtnAddress;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    private WebElement btnContinue;

    @FindBy(xpath = "(//span[@class='mat-radio-inner-circle'])[1]")
    private WebElement radioBtnOneDayDelivery;




    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Method
    public void clickAddNewAddress() {
        btnAddNewAddress.click();
    }

    public void setCountryField(String input) {
        countryField.sendKeys(input);
    }

    public void setNameField(String input) {
        nameField.sendKeys(input);
    }

    public void setMobileNumberField(String input) {
        mobileNumberField.sendKeys(input);
    }

    public void setZipCodeField(String input) {
        zipCodeField.sendKeys(input);
    }

    public void setAddressField(String input) {
        addressField.sendKeys(input);
    }

    public void setCityField(String input) {
        cityField.sendKeys(input);
    }

    public void setStateField(String input) {
        stateField.sendKeys(input);
    }

    public void fillAddressForm() {
        setCountryField("Nightfall");
        setNameField("Mirana");
        setMobileNumberField("297386298");
        setZipCodeField("18321");
        setAddressField("Starfall St.");
        setCityField("Selemene");
        setStateField("Leap");
    }

    public void clickSubmitAddress() {
        btnSubmitNewAddress.click();
    }

    public void clickNewAddressOptions() {
        radioBtnAddress.click();
    }

    public void clickOneDayDelivery() {
        radioBtnOneDayDelivery.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }


}
