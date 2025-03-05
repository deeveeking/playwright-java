package com.tests.login;

import com.models.UserModel;
import com.tests.base.BaseTest;
import com.utils.RandomUtils;
import com.utils.UserManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest extends BaseTest {
    @Test()
    @Tag("loginTest")
    public void errorMessageIsShownWithInvalidLoginDataTest() {
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";

        UserModel user = RandomUtils.getRandomUserLoginData();

        loginPage.login(user);

        assertThat(loginPage.getErrorMessageText())
                .as("Invalid error message text")
                .isEqualTo(expectedMessage);
    }

    @Test
    @Tag("loginTest")
    public void lockedOutUserLoginTest() {
        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";

        UserModel user = UserManager.getLockedUserForTest();

        loginPage.login(user);

        assertThat(loginPage.getErrorMessageText())
                .as("Invalid error message text")
                .isEqualTo(expectedMessage);
    }
}
