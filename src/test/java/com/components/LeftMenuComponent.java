package com.components;

import com.components.baseComponent.BaseComponent;
import com.microsoft.playwright.Page;

public class LeftMenuComponent extends BaseComponent {

    private final String logoutButton = "#logout_sidebar_link";

    public LeftMenuComponent(Page page) {
        super(page);
    }

    public void clickLogout() {
        page.click(this.logoutButton);
    }
}
