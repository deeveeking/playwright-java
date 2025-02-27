package com.pages;

import com.components.HeaderComponent;
import com.components.LeftMenuComponent;
import com.enums.SortingEnum;
import com.microsoft.playwright.Locator;
import com.pages.basePage.BasePage;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {
    public HeaderComponent headerComponent;
    public LeftMenuComponent leftMenuComponent;

    private final String productCardsLocator = "div.inventory_item";

    private final String priceOnCardLocator = "div.inventory_item_price";

    private final String addToCartButtonOnProductCartLocator = "button.btn_inventory";

    private final String productNameLocator = "div.inventory_item_name";

    private final String soringSelectLocator = "select.product_sort_container";

    private final String removeFromCartButtonLocator = "button.btn_inventory";

    @Override
    public void initializePageComponent() {
        headerComponent = new HeaderComponent(page);
        leftMenuComponent = new LeftMenuComponent(page);
    }

    @Step("Getting price for product with name <productName>")
    public String getPriceForProduct(String productName) {
        Locator product = this.getProductCardByName(productName);
        String price = product.locator(this.priceOnCardLocator).textContent();

        return price.replace("$", "").trim();
    }

    @Step("Adding product with name <productName to the cart>")
    public void addProductToTheCart(String productName) {
        Locator product = this.getProductCardByName(productName);
        product.locator(this.addToCartButtonOnProductCartLocator).click();
    }

    @Step("Getting all product names")
    public List<String> getAllProductsName() {
        List<Locator> products = page.locator(this.productCardsLocator).all();
        List<String> productNames = new ArrayList<>();

        for (Locator product: products) {
            productNames.add(product.locator(this.productNameLocator).textContent());
        }

        return productNames;
    }

    @Step("Sort item on the page <sortQuery>")
    public void sortItem(SortingEnum sortQuery) {
        page.locator(this.soringSelectLocator).selectOption(sortQuery.getValue());
    }

    @Step("Check if remove button present for product by name <productName>")
    public boolean isRemoveButtonPresentForProduct(String productName) {
        Locator product = this.getProductCardByName(productName);

        return product.locator(this.removeFromCartButtonLocator).isVisible();
    }

    private Locator getProductCardByName(String productName) {
        List<Locator> products = page.locator(this.productCardsLocator).all();

        return products
                .stream()
                .filter(x -> x.locator(this.productNameLocator).textContent().equals(productName))
                .findFirst()
                .get();
    }
}
