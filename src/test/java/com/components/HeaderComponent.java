package com.components;

import com.components.baseComponent.BaseComponent;
import com.microsoft.playwright.Page;
import com.pages.CartPage;
import com.utils.PageFactory;

public class HeaderComponent extends BaseComponent {

    private final String leftMenuButton = "div.bm-burger-button";

    private final String cartButton = "#shopping_cart_container";

    public HeaderComponent(Page page) {
        super(page);
    }

    public void openLeftMenu() {
        page.click(this.leftMenuButton);
    }

    public CartPage openCart() {
        page.click(this.cartButton);

        return PageFactory.createPageInstance(page, CartPage.class);
    }
}
