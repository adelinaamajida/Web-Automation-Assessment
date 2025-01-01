package com.assessment.web.pageobjects;

import org.checkerframework.checker.signature.qual.FieldDescriptor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage{

    private WebDriver driver;

    //Set Locators
    @FindBy(xpath = "//span[contains(text(),'Account')]")
    private WebElement accountMenu;

    @FindBy(xpath = "//button/span[contains(text(),'Login')]")
    private WebElement btnLogin;
    @FindBy(xpath = "//span[contains(text(),'Dismiss')]")
    private WebElement btnDismiss;

    @FindBy(xpath = "//a[text()='Me want it!']")
    private WebElement btnCookies;

    @FindBy(id = "mat-select-value-1")
    private WebElement itemsPerPageDropdown;

    @FindBy(xpath = "//span[@class = 'mat-option-text' and contains(text(), '48')]")
    private WebElement maximumItemsCount;

    @FindBy(xpath = "//mat-card")
    private List<WebElement> itemCards;

    @FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
    private WebElement btnYourBasket;

    //Product Display on Homepage
    @FindBy(xpath = "(//button[@aria-label='Add to Basket']) [1]")
    private WebElement btnAddToBasketAppleJuice;

    @FindBy(xpath = "(//button[@aria-label='Add to Basket']) [2]")
    private WebElement btnAddToBasketApplePomace;

    @FindBy(xpath = "//button[@aria-label='Add to Basket']")
    private List<WebElement> addToBaskets;

    @FindBy(xpath = "//span[contains(text(), 'Placed')]")
    private WebElement popUpAddBasket;

    @FindBy(xpath = "//span[contains(text(),'Your Basket')]/following-sibling::span")
    private WebElement basketCount;

    @FindBy(xpath = "//button/span[contains(text(),'Logout')]")
    private WebElement btnLogout;

    @FindBy(xpath = "//span[text()='X']")
    private WebElement btnCloseSnackbar;

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Navigate to homepage
    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void dismissPopUpButton() {
        btnDismiss.click();
    }

    public void clickAccountMenu() {
        accountMenu.click();
    }

    public void clickLoginOption() {
        btnLogin.click();
    }
    //Change items per page to the maximum number
    public void changeItemsPerPage() {
        waitForElement(itemsPerPageDropdown).click(); // Open the dropdown
        waitForElement(maximumItemsCount).click();      // Select max items
    }

    // Get the count of displayed items
    public int getItemCount() {
        return itemCards.size();
    }

    //Verify logged in state by checking availability of Your Basket button
    public boolean verifyYourBasket() {
        return btnYourBasket.isDisplayed();
    }

    public boolean verifyProductAddedToBasket() {
        return btnYourBasket.isDisplayed();
    }

    public List<WebElement> getAddToBasketButtons() {
        return addToBaskets;
    }

    public boolean verifyPopUpAddBasket() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(200));
            wait.until(ExpectedConditions.visibilityOf(popUpAddBasket));
            return popUpAddBasket.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getBasketNumber() {
        return basketCount.getText();
    }

    public void clickLogoutOption() {
        super.waitForElement(btnLogout);
        btnLogout.click();
    }

    public void closeSnackBar() {
        btnCloseSnackbar.click();
    }

    public void closeCookies() {
        btnCookies.click();
    }

    public void openBasket() {
        btnYourBasket.click();
    }

}
