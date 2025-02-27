package com.utils;

import com.microsoft.playwright.Page;
import com.pages.basePage.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageFactory {
    private static final Logger log = LoggerFactory.getLogger(PageFactory.class);

    public static <T extends BasePage> T createPageInstance(Page page, Class<T> clazz) {
        try {
            BasePage pageInstance = clazz.getDeclaredConstructor().newInstance();
            pageInstance.initializePage(page);
            pageInstance.initializePageComponent();

            return clazz.cast(pageInstance);
        } catch (Exception e) {
            log.error("PageFactoryClass::createPageInstance error.", e);
        }
        throw new NullPointerException("Create Page Instance failed");
    }
}
