package com.shoppingcart.productserviceshoppingcart.security.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class User
{
    private String name;
    private String email;
    private String hashedPassword;
    private List<role> roles;
    private boolean isEmailVerified;
}
