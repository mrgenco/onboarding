package com.mrg.onboarding.security.auth;

import com.mrg.onboarding.security.AuthenticationUserDetailsService;
import com.mrg.onboarding.security.TokenService;
import com.mrg.onboarding.security.auth.request.AuthenticationRequest;
import com.mrg.onboarding.security.auth.request.RefreshTokenRequest;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AuthenticationUserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        try{
            if(AuthenticationMethod.DB.getCode().equals(authenticationRequest.authenticationMethod())){
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()));
                return new ResponseEntity<>(tokenService.generateToken(authenticationRequest.username(), authentication.getAuthorities()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        try{
            String refreshToken = refreshTokenRequest.refreshToken();
            if(StringUtils.isNotEmpty(refreshToken) && tokenService.validateTokenExpiry(refreshToken)){
                UserDetails userDetails = userDetailsService.loadUserByUsername(tokenService.getUsername(refreshToken));
                return new ResponseEntity<>(tokenService.generateToken(userDetails.getUsername(), userDetails.getAuthorities()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
