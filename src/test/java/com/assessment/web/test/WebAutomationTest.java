package com.assessment.web.test;

import com.assessment.web.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class WebAutomationTest {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private BasketPage basketPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;

    private String[] user;

    @BeforeClass(alwaysRun = true)
    public void setUp()  {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        basketPage = new BasketPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
        user = registrationPage.generateEmailPassword();
        homePage.navigateToHomePage("https://juice-shop.herokuapp.com/");
        homePage.dismissPopUpButton();
        homePage.closeCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void task1Test() {
        homePage.scrolltoBottomPage();
        homePage.changeItemsPerPage();  //Change items per page to 48 and verify its display
        int itemCount = homePage.getItemCount();
        // Assert that items are displayed
        Assert.assertTrue(itemCount > 0, "No items are displayed on the homepage.");
    }

    @Test
    public void task2Test() throws InterruptedException {
        productPage.clickAppleJuice();
        // Verify if product popup appears
        Thread.sleep(500);
        Assert.assertTrue(productPage.isPopupDisplayed(), "Product popup is not showing.");
        // Verify if product image is displayed
        Assert.assertTrue(productPage.isProductImageDisplayed(), "Product image is not showing.");
        productPage.expandReview();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.closePopup();
    }

    @Test
    public void task3Test() throws InterruptedException {
        homePage.clickAccountMenu();
        homePage.clickLoginOption();
        loginPage.navigateToRegisterPage();

        registrationPage.triggerValidationMessages(); //clicking on each required fields
        //verify validation warning
        Assert.assertTrue(registrationPage.ifValidationMessagesDisplayed(), "Validation messages are not showing.");
        //fill registration form with auto-generated data
        registrationPage.fillOutRegistrationForm(user); // Generate email and password
        registrationPage.submitNewAccount();
        Assert.assertTrue(registrationPage.isDirectedToLoginPage());
        Assert.assertTrue(loginPage.registrationCompleted(),"Registration is not successful.");
        loginPage.setEmailField(user[0]);
        loginPage.setPasswordField(user[1]);
        loginPage.submitLogin();
        Assert.assertTrue(homePage.verifyYourBasket());
        homePage.clickAccountMenu();
        Thread.sleep(500);
        homePage.clickLogoutOption();
    }

    @Test
    public void task4Test() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        homePage.clickAccountMenu();
        homePage.clickLoginOption();
        loginPage.setEmailField(user[0]);
        loginPage.setPasswordField(user[1]);
        loginPage.submitLogin();
        int numberOfProduct = 5;
        int indexProduct = 0;
        int productAdded = 0;
        while (productAdded < numberOfProduct) {
            homePage.getAddToBasketButtons().get(indexProduct+1).click();
            Thread.sleep(2000);
            boolean popUpSuccess = homePage.verifyPopUpAddBasket();
            softAssert.assertTrue(popUpSuccess, "Popup was not displayed as expected.");
            indexProduct++;
            if(popUpSuccess) {
                productAdded++;
                homePage.closeSnackBar();
            }
        }
        Assert.assertEquals(homePage.getBasketNumber(), "5");
        homePage.openBasket();
        Thread.sleep(2000);
        String initialPrice = basketPage.getTotalPrice();
        System.out.println(initialPrice);
        basketPage.addQuantitySecondProduct();
        Thread.sleep(2000);
        basketPage.deleteSecondProduct();
        Thread.sleep(2000);
        String totalPrice = basketPage.getTotalPrice();
        System.out.println(totalPrice);
        Assert.assertNotEquals(totalPrice, initialPrice);
        basketPage.clickCheckout();
        System.out.println("");
        checkoutPage.clickAddNewAddress();
        Thread.sleep(2000);
        checkoutPage.fillAddressForm();
        checkoutPage.clickSubmitAddress();
        checkoutPage.clickNewAddressOptions();
        checkoutPage.clickContinue();
        Thread.sleep(2000);
        checkoutPage.clickOneDayDelivery();
        checkoutPage.clickContinue();
        Thread.sleep(2000);
        Assert.assertEquals(paymentPage.getWalletBalance(),"0.00");
        paymentPage.addNewCard();
        paymentPage.fillNewCardForm();
        paymentPage.submitNewCard();
        paymentPage.selectNewCard();
        paymentPage.clickContinue();
        paymentPage.clickBtnPurchase();
        Assert.assertTrue(paymentPage.verifySuccessfulPurchase());

    }



}
