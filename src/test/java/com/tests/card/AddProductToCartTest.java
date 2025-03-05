package com.tests.card;

import com.pages.CartPage;
import com.tests.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class AddProductToCartTest extends BaseTest {
    private String productName;

    @BeforeEach
    public void setUpProduct() {
        List<String> products = productsPage.getAllProductsName();
        productName = products.getFirst();
    }

    @Test
    @Tag("regressionTest")
    public void productCanBeAddedToTheCartTest() {
        productsPage.addProductToTheCart(productName);
        CartPage cartPage = productsPage.headerComponent.openCart();

        assertThat(cartPage.getAllCartItemNames())
                .as("Product not added to the cart")
                .contains(productName);
    }

    @Test
    @Tag("regressionTest")
    public void productCanBeRemovedFromTheCartTest() {
        productsPage.addProductToTheCart(productName);

        CartPage cartPage = productsPage.headerComponent.openCart();
        cartPage.removeCartItemByName(productName);

        assertThat(cartPage.getAllCartItemNames())
                .as("Product not removed from the cart")
                .isEmpty();
    }
}
