package com.components.baseComponent;

import com.microsoft.playwright.Page;

public abstract class BaseComponent {
    protected Page page;

    protected BaseComponent (Page page) {
        this.page = page;
    }
}
