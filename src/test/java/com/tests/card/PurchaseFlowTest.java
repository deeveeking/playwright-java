package com.tests.card;

import com.models.ShippingInfoModel;
import com.pages.CartPage;
import com.pages.CheckOutPage;
import com.tests.base.BaseTest;
import com.utils.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class PurchaseFlowTest extends BaseTest {
    private String productName;

    @BeforeEach
    public void setUpProduct() {
        List<String> products = productsPage.getAllProductsName();
        productName = products.getFirst();
    }

    @Test
    @Tag("regressionTest")
    public void checkThatUserCanPurchaseProductTest() {
        ShippingInfoModel shippingInfoModel = RandomUtils.getRandomShippingInfo();
        String expectedHeaderText = "Thank you for your order!";
        String expectedDescriptionText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        productsPage.addProductToTheCart(productName);

        CartPage cartPage = productsPage.headerComponent.openCart();
        CheckOutPage checkOutPage = cartPage.clickCheckOutButton();
        checkOutPage.fillCheckoutForm(shippingInfoModel);
        checkOutPage.clickOnContinueButton();
        checkOutPage.clickOnFinishPurchaseButton();

        assertThat(checkOutPage.isFinishedPurchaseLogoDisplayed())
                .as("Finished purchase logo is not present")
                .isTrue();
        assertThat(checkOutPage.getFinishedPurchaseHeaderText())
                .as("Incorrect finished purchase header text")
                .isEqualTo(expectedHeaderText);
        assertThat(checkOutPage.getFinishedDescriptionText())
                .as("Incorrect description text")
                .isEqualTo(expectedDescriptionText);
    }

    @Test
    @Tag("regressionTest")
    public void checkErrorMessageWhenFirstNameNotFilledTest() {
        String expectedMessage = "Error: First Name is required";

        productsPage.addProductToTheCart(productName);

        CartPage cartPage = productsPage.headerComponent.openCart();
        CheckOutPage checkOutPage = cartPage.clickCheckOutButton();
        checkOutPage.clickOnContinueButton();

        assertThat(checkOutPage.getErrorMessageText())
                .as("Error message is not correct")
                .isEqualTo(expectedMessage);
    }
}
