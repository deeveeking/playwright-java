package com.pages;

import com.microsoft.playwright.Locator;
import com.models.ShippingInfoModel;
import com.pages.basePage.BasePage;
import io.qameta.allure.Step;

public class CheckOutPage extends BasePage {
    private final String firstNameLocator = "#first-name";

    private final String lastNameLocator = "#last-name";

    private final String zipCodeLocator = "#postal-code";

    private final String continueButtonLocator = "input.cart_button";

    private final String finishPurchaseButtonLocator = "button.btn_action";

    private final String finishedPurchaseLogoLocator = "img.pony_express";

    private final String completePurchaseHeaderTextLocator = "h2.complete-header";

    private final String completePurchaseDescriptionTextLocator = "div.complete-text";

    private final String errorMessageLocator = "[data-test='error']";

    @Step("Filling shipping info <shippingInfo>")
    public void fillCheckoutForm(ShippingInfoModel shippingInfo) {
        page.locator(this.firstNameLocator).fill(shippingInfo.getFirstName());
        page.locator(this.lastNameLocator).fill(shippingInfo.getLastName());
        page.locator(this.zipCodeLocator).fill(shippingInfo.getZipCode());
    }

    @Step("Click on continue button")
    public void clickOnContinueButton() {
        page.locator(this.continueButtonLocator).click();
    }

    @Step("Click on finish purchase button")
    public void clickOnFinishPurchaseButton() {
        page.locator(this.finishPurchaseButtonLocator).click();
    }

    @Step("Check if finished purchase logo is displayed")
    public boolean isFinishedPurchaseLogoDisplayed() {
        return page.locator(this.finishedPurchaseLogoLocator).isVisible();
    }

    @Step("Get finished purchase header text")
    public String getFinishedPurchaseHeaderText() {
        return page.locator(this.completePurchaseHeaderTextLocator).textContent().trim();
    }

    @Step("Get finished purchase description text")
    public String getFinishedDescriptionText() {
        return page.locator(this.completePurchaseDescriptionTextLocator).textContent().trim();
    }

    @Step("Get error message text")
    public String getErrorMessageText() {
        return page.locator(this.errorMessageLocator).textContent().trim();
    }
}
