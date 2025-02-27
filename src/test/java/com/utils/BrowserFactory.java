package com.utils;

import com.config.ConfigurationReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public enum BrowserFactory {

    CHROMIUM {
        @Override
        public Browser createBrowserInstance(final Playwright playwright) {
            return playwright.chromium().launch(getOptions());
        }
    },
    FIREFOX {
        @Override
        public Browser createBrowserInstance(final Playwright playwright) {
            return playwright.firefox().launch(getOptions());
        }
    },
    WEBKIT {
        @Override
        public Browser createBrowserInstance(final Playwright playwright) {
            return playwright.webkit().launch(getOptions());
        }
    };

    public BrowserType.LaunchOptions getOptions() {
        return new BrowserType.LaunchOptions()
                .setHeadless(ConfigurationReader.config().isHeadlessRun())
                .setSlowMo(ConfigurationReader.config().slowMotionTimeForBrowser());
    }

    public  abstract Browser createBrowserInstance(final Playwright playwright);
}
