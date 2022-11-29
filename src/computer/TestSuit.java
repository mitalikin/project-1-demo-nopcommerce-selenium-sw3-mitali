package computer;

import com.google.common.base.Verify;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Computer;
import org.openqa.selenium.By;
import utilities.Utility;

import java.awt.*;

public class TestSuit extends Utility {
    String baseUrl = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void TestNameVerifyProductArrangeInAlphaBatiCalOrder() {
        //1.1 Click on Computer Menu.
        clickOnElement(By.linkText("Computers"));
        //1.2 Click on Desktop
        clickOnElement(By.xpath("//h2//a[@title='Show products in category Desktops']"));
        //1.3 Select Sort By position "Name: Z to A"
        clickOnElement(By.id("products-orderby"));
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: Z to A");
        //1.4 Verify the Product will arrange in Descending order.

    }

    @Test
    public void NameVerifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        // 2.1 Click on Computer Menu.
        clickOnElement(By.linkText("Computers"));
        // 2.2 Click on Desktop
        clickOnElement(By.xpath("//h2//a[@title='Show products in category Desktops']"));
        //2.3 Select Sort By position "Name: A to Z"
        Thread.sleep(1000);
        clickOnElement(By.id("products-orderby"));
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");
        //2.4 Click on "Add To Cart"
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@onclick='return AjaxCart.addproducttocart_catalog(\"/addproducttocart/catalog/1/1/1\"),!1']"));
        //2.5 Verify the Text "Build your own computer"
        String expectedMessage = "Build your own computer";
        String actualMessage = getTextFromElement(By.xpath("//h1[text()='Build your own computer']"));
        Assert.assertEquals("Text is not present", expectedMessage, actualMessage);
        // 2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        clickOnElement(By.id("product_attribute_1"));
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");
        // 2.7.Select "8GB [+$60.00]" using Select class.
        clickOnElement(By.name("product_attribute_2"));
        selectByVisibleTextFromDropDown(By.name("product_attribute_2"), "8GB [+$60.00]");
        // 2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));
        sendTexTooElement(By.id("product_attribute_3_7"), "400 GB [+$100.00]");
        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));
        sendTexTooElement(By.id("product_attribute_4_9"), "Vista Premium [+$60.00]");

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander[+$5.00]"
        clickOnElement(By.id("product_attribute_5_12"));
        //selectByVisibleTextFromDropDown(By.id("product_attribute_4"),"Vista Premium [+$60.00]");
        // 2.11 Verify the price "$1,475.00"
        Thread.sleep(5000);
        String expectedAmount = "$1,475.00";
        String actualAmount = getTextFromElement(By.id("price-value-1"));
        Assert.assertEquals("Text is not present", expectedAmount, actualMessage);

        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));

        //2.13 Verify the Message "The product has been added to your shopping cart" on Topgreen Ba
        String expectedMessage1 = "The product has been added to your";
        String actualMessage1 = getTextFromElement(By.xpath("//P[@class='content']"));
        Assert.assertEquals("Text is not present", expectedMessage1, actualMessage);

        // After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));
        //  2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        Thread.sleep(5000);
        mouseHoverToElementAndClick(By.xpath("//span[@class='cart-label']"));

        // 2.15 Verify the message "Shopping cart"
        String expectedMessage2 = "Shopping cart";
        String actualMessage2 = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        Assert.assertEquals("Text is not present", expectedMessage2, actualMessage2);

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        clickOnElement(By.xpath("//input[@class='qty-input']"));
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTexTooElement(By.xpath("//input[@class='qty-input']"), "2");

        // clickOnElement(By.name("updatecart"));
        clickOnElement(By.xpath("//button[@class='button-2 update-cart-button']"));
        // 2.17 Verify the Total"$2,950.00"
        String expectedValue = "$2,950.00";
        String actualValue = getTextFromElement(By.xpath("//strong[text()='$2,950.00']"));
        Assert.assertEquals("Text is not present", expectedValue, actualMessage);
        //2.18 click on checkbox “I agree with the terms of service"
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        // 2.19 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@class='button-1 checkout-button']"));
        //2.20 Verify the Text “Welcome, Please Sign In!”
        String expectedMessage4 = "Welcome, Please Sign In!";
        String actualMessage4 = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        Assert.assertEquals("Text is not present", expectedMessage4, actualMessage);
        //2.21Click on “CHECKOUT AS GUEST”Tab
        clickOnElement(By.xpath("//button[text()='Checkout as Guest']"));

        //2.22 Fill the all mandatory field
        sendTexTooElement(By.id("BillingNewAddress_FirstName"), "john");
        sendTexTooElement(By.id("BillingNewAddress_LastName"), "white");
        sendTexTooElement(By.id("BillingNewAddress_Email"), "123test@gmail.com");
        sendTexTooElement(By.id("BillingNewAddress_Company"), "ABC company ltd");
        clickOnElement(By.id("BillingNewAddress_CountryId"));
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "United Kingdom");
        clickOnElement(By.id("BillingNewAddress_StateProvinceId"));
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_StateProvinceId"), "Other");

        sendTexTooElement(By.id("BillingNewAddress_City"), "London");
        sendTexTooElement(By.id("BillingNewAddress_Address1"), "14 crofts Rd");
        sendTexTooElement(By.id("BillingNewAddress_Address2"), "Watford");
        sendTexTooElement(By.id("BillingNewAddress_ZipPostalCode"), "wd17 2tr");
        sendTexTooElement(By.id("BillingNewAddress_PhoneNumber"), "07654327856");
        sendTexTooElement(By.id("BillingNewAddress_FaxNumber"), "7654327856");

        // 2.23 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 new-address-next-step-button'and@name='save']"));
        // 2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));
        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        // 2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@type='button'and@onclick='PaymentMethod.save()']"));
        // 2.27 Select “Master card” From Select credit card dropdown
        clickOnElement(By.id("CreditCardType"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        // 2.28 Fill all the details
        sendTexTooElement(By.id("CardholderName"), "john white");
        sendTexTooElement(By.id("CardNumber"), "0795 5666 6667 7777");

        clickOnElement(By.id("ExpireMonth"));
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "05");

        clickOnElement(By.id("ExpireYear"));
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2025");

        sendTexTooElement(By.xpath("//input[@data-val-regex='Wrong card code']"), "567");

        // 2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        // 2.30 Verify “Payment Method” is “Credit Card”
        String expectedMethod = "Credit Card";
        String actualMethod = getTextFromElement(By.xpath("//label[text()='Credit Card']"));
        Assert.assertEquals("Text is not present", expectedMethod, actualMessage);

        // 2.32 Verify “Shipping Method” is “Next Day Air”
        String expectedText = "Next Day Air ($0.00)";
        String actualText = getTextFromElement(By.xpath("//label[text()='Next Day Air ($0.00)']"));
        Assert.assertEquals("Text is not present", expectedText, actualMessage);

        //2.33 Verify Total is “$2,950.00”
        String expectedAmount1 = "$2,950.00";
        String actualAmount1 = getTextFromElement(By.xpath("//strong[text()='$2,950.00']"));
        Assert.assertEquals("Text is not present", expectedAmount1, actualMessage);

        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        //2.35 Verify the Text “Thank You”
        String expectedText3 = "Thank you";
        String actualText3 = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals("Text is not present", expectedText3, actualMessage);

        //2.36 Verify the message “Your order has been successfully processed!”
        String expectedText4 = "Your order has been successfully processed!";
        String actualText4 = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals("Text is not present", expectedText4, actualMessage);
        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        //2.37 Verify the text “Welcome to our store”
        String expectedText5 = "Welcome to our store";
        String actualText5 = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        Assert.assertEquals("Text is not present", expectedText5, actualMessage);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
