package com.mrg.onboarding.security.auth.request;


public record AuthenticationRequest(String username, String password, String authenticationMethod) {
}