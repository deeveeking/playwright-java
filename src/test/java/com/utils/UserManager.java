package com.utils;

import com.config.ConfigurationReader;
import com.models.UserModel;

public class UserManager {
    public static UserModel getUserForTest() {
        return UserModel.builder()
                .userName(ConfigurationReader.config().userName())
                .password(ConfigurationReader.config().userPassword())
                .build();
    }

    public static UserModel getLockedUserForTest() {
        return UserModel.builder()
                .userName("locked_out_user")
                .password("secret_sauce")
                .build();
    }
}
