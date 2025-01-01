package com.assessment.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "(//mat-cell[contains(@class, 'quantity')]/button[2])[2]")
    private WebElement btnAddQtySecondItem;

    @FindBy(xpath = "(//mat-cell[contains(@class, 'remove')]/button)[2]")
    private WebElement btnDeleteSecondItem;

    @FindBy(id = "price")
    private WebElement lblTotalPrice;

    @FindBy(id = "checkoutButton")
    private WebElement btnCheckout;
    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addQuantitySecondProduct() {
        super.waitForElement(btnAddQtySecondItem);
        btnAddQtySecondItem.click();

    }

    public void deleteSecondProduct() {
        btnDeleteSecondItem.click();
    }

    public String getTotalPrice() {
        return lblTotalPrice.getText();
    }

    public void clickCheckout() {
        btnCheckout.click();
    }
}
