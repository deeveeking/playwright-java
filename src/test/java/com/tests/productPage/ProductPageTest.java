package com.tests.productPage;

import com.enums.SortingEnum;
import com.tests.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageTest extends BaseTest {

    @Test
    @Tag("regressionTest")
    public void checkSortingByNameAscendingTest() {
        String firstItemName = "Sauce Labs Backpack";
        String lastItemName = "T-Shirt";

        productsPage.sortItem(SortingEnum.A_TO_Z);

        List<String> productNames = productsPage.getAllProductsName();

        assertThat(productNames.getFirst())
                .as("Incorrect first item name")
                .isEqualTo(firstItemName);
        assertThat(productNames.getLast())
                .as("Incorrect last item name")
                .contains(lastItemName);
    }

    @Test
    @Tag("regressionTest")
    public void checkSortingByNameDescendingTest() {
        String firstItemName = "Sauce Labs Backpack";
        String secondItemName = "T-Shirt";

        productsPage.sortItem(SortingEnum.Z_TO_A);

        List<String> productNames = productsPage.getAllProductsName();

        assertThat(productNames.getFirst())
                .as("Incorrect first item name")
                .contains(secondItemName);
        assertThat(productNames.getLast())
                .as("Incorrect last item name")
                .isEqualTo(firstItemName);
    }

    @Test
    @Tag("regressionTest")
    public void checkRemoveButtonIsPresentForProductTest() {
        String productName = productsPage.getAllProductsName().getFirst();

        productsPage.addProductToTheCart(productName);

        assertThat(productsPage.isRemoveButtonPresentForProduct(productName))
                .as("Remove button is not displayed")
                .isTrue();
    }

    @Test
    @Tag("regressionTest")
    public void checkPriceForProductTest() {
        String productName = productsPage.getAllProductsName().getFirst();
        String expectedPrice = "29.99";

        assertThat(productsPage.getPriceForProduct(productName))
                .as("Incorrect product price")
                .isEqualTo(expectedPrice);
    }
}
