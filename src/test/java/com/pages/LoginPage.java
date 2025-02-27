package com.pages;

import com.config.ConfigurationReader;
import com.models.UserModel;
import com.pages.basePage.BasePage;
import com.utils.PageFactory;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    private final String userNameInputLocator = "[data-test='username']";

    private final String passwordInputLocator = "[data-test='password']";

    private final String loginButtonLocator = "#login-button";

    private final String errorMessageLocator = "[data-test='error']";

    @Step("Navigate to the login page")
    public LoginPage open() {
        page.navigate(ConfigurationReader.config().appUrl());

        return this;
    }

    @Step("Login to the page with <testUser>")
    public ProductsPage login(UserModel testUser) {
        page.fill(this.userNameInputLocator, testUser.getUserName());
        page.fill(this.passwordInputLocator, testUser.getPassword());
        page.click(this.loginButtonLocator);

        return PageFactory.createPageInstance(page, ProductsPage.class);
    }

    @Step("Getting error message text")
    public String getErrorMessageText() {
        return page.textContent(this.errorMessageLocator);
    }
}
