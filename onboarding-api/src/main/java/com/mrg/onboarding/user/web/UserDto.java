package com.mrg.onboarding.user.web;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String roles;
}
