package com.mrg.onboarding.security.auth;

import com.mrg.onboarding.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    // TODO return custom AuthenticationResponse with refreshToken and expireTime
    @PostMapping("/login")
    public String token(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {

        if(AuthenticationMethod.DB.equals(authenticationRequest.authenticationMethod())){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()));
            return tokenService.generateToken(authentication);
        }

        // TODO return custom AuthenticationException
        return null;
    }
}
