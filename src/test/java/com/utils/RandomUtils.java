package com.utils;

import com.models.ShippingInfoModel;
import com.models.UserModel;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    private static RandomStringUtils r = RandomStringUtils.insecure();

    public static ShippingInfoModel getRandomShippingInfo() {
        return ShippingInfoModel
                .builder()
                .firstName(r.nextAlphabetic(5))
                .lastName(r.nextAlphabetic(7))
                .zipCode(r.nextNumeric(10))
                .build();
    }

    public static UserModel getRandomUserLoginData() {
        return UserModel
                .builder()
                .userName(r.nextAlphabetic(5))
                .password(r.nextAlphabetic(5))
                .build();
    }
}
