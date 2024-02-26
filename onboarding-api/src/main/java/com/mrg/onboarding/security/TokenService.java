package com.mrg.onboarding.security;
import com.mrg.onboarding.security.auth.AuthenticationResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public AuthenticationResponse generateToken(Authentication authentication) {

        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant accessTokenExpireTime = now.plus(1, ChronoUnit.HOURS);
        JwtClaimsSet accessTokenClaims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(accessTokenExpireTime)
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        Instant refreshTokenExpireTime = now.plus(2, ChronoUnit.HOURS);
        JwtClaimsSet refreshTokenClaims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(refreshTokenExpireTime)
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        String accessToken = this.encoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();
        String refreshToken = this.encoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expireTime(accessTokenExpireTime)
                .build();
    }



}