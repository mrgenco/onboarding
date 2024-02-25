package com.mrg.onboarding.security.auth;

import java.util.Optional;

public enum AuthenticationMethod {

    DB("DB"),
    LDAP("LDAP"),
    GITHUB("GITHUB"),
    GOOGLE("GOOGLE");

    private final String code;
    AuthenticationMethod(String code) {
        this.code=code;
    }

    public String getCode(){
        return this.code;
    }

    public static Optional<AuthenticationMethod> findByValue(String code){
        for(AuthenticationMethod status : AuthenticationMethod.values()){
            if(status.getCode().equals(code))
                return Optional.of(status);
        }
        return Optional.empty();
    }
}
