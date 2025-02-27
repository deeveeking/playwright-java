package com.pages;

import com.microsoft.playwright.Locator;
import com.pages.basePage.BasePage;
import com.utils.PageFactory;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final String cartItemsLocator = "div.cart_item";

    private final String cartItemNameLocator = "[data-test='inventory-item-name']";

    private final String removeCartButtonLocator = "button.btn_secondary";

    private final String checkOutButtonLocato = "button.checkout_button";

    @Step("Getting all cart item names")
    public List<String> getAllCartItemNames() {
        List<Locator> products = page.locator(this.cartItemsLocator).all();
        List<String> productNames = new ArrayList<>();

        products.forEach(x -> productNames.add(x.locator(this.cartItemNameLocator).textContent()));

        return productNames;
    }

    @Step("Remove cart item by name <productName>")
    public CartPage removeCartItemByName(String productName) {
        Locator product = this.getCartItemByName(productName);

        product.locator(this.removeCartButtonLocator).click();

        return this;
    }

    @Step("Click on checkOut button")
    public CheckOutPage clickCheckOutButton() {
        page.locator(this.checkOutButtonLocato).click();

        return PageFactory.createPageInstance(page, CheckOutPage.class);
    }

    private Locator getCartItemByName(String productName) {
        List<Locator> products = page.locator(this.cartItemsLocator).all();

        return products
                .stream()
                .filter(x -> x.locator(this.cartItemNameLocator).textContent().equals(productName))
                .findFirst()
                .get();
    }
}
