package com.enums;

import lombok.Getter;

public enum SortingEnum {
    A_TO_Z("Name (A to Z)"),
    Z_TO_A("Name (Z to A)");

    @Getter
    private String value;

    SortingEnum(final String value) {
        this.value = value;
    }
}
