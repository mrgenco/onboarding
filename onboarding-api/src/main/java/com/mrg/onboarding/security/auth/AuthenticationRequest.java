package com.mrg.onboarding.security.auth;


public record AuthenticationRequest(String username, String password, AuthenticationMethod authenticationMethod) {
}