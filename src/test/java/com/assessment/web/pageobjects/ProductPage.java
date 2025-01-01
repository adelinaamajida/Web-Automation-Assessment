package com.assessment.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage{

    private WebDriver driver;

    //Set Locators
    @FindBy(xpath = "//div [@class = 'item-name' and contains(text(), 'Apple Juice')]")
    private WebElement appleJuiceProduct;

    @FindBy(xpath = "//mat-dialog-content [@class='mat-dialog-content']") // Popup container
    private WebElement productPopup;

    @FindBy(xpath = "//img[@class='img-thumbnail']") // Product image inside popup
    private WebElement productImage;

    @FindBy(xpath = "//span[contains(text(),'Reviews')]") // Expand review button
    private WebElement expandReview;

    @FindBy(xpath = "//button[@aria-label='Close Dialog']") // Close popup button
    private WebElement closePopupButton;

    //Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Click on the first product (Apple Juice)
    public void clickAppleJuice() {
        clickElement(appleJuiceProduct);
    }

    // Assert popup is displayed
    public boolean isPopupDisplayed() {
        return productPopup.isDisplayed();
    }

    // Assert product image is visible in the popup
    public boolean isProductImageDisplayed() {
        return productImage.isDisplayed();
    }

    // Click on the review button to expand it (if available)
    public void expandReview() {
        if (expandReview.isDisplayed()) {
            clickElement(expandReview);
        }
    }

    // Close the product popup
    public void closePopup() {
        clickElement(closePopupButton);
    }

}
