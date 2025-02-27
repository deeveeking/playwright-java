package com.pages.basePage;

import com.config.ConfigurationReader;
import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected Page page;

    public void initializePage(final Page page) {
        this.page = page;
        page.setDefaultTimeout(ConfigurationReader.config().timeOutForAction());
    }

    public void initializePageComponent() { };
}
