package com.utils;

import com.config.ConfigurationReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class BrowserManager {
    public static Browser getBrowser(final Playwright playwright) {
        return BrowserFactory.valueOf(ConfigurationReader.config()
                .browserToLaunch()
                .toUpperCase())
                .createBrowserInstance(playwright);
    }
}
