package com.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String userName;
    private String password;
}
