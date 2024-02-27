package com.mrg.onboarding.security;
import com.mrg.onboarding.security.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public AuthenticationResponse generateToken(String username, Collection<? extends GrantedAuthority> authorities) {

        Instant now = Instant.now();
        String scope = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Creating Access Token
        Instant accessTokenExpireTime = now.plus(1, ChronoUnit.HOURS);
        JwtClaimsSet accessTokenClaims = getJwtClaimsSet(username, now, accessTokenExpireTime, scope);
        String accessToken = this.encoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();

        // Creating Refresh Token
        Instant refreshTokenExpireTime = now.plus(2, ChronoUnit.HOURS);
        JwtClaimsSet refreshTokenClaims = getJwtClaimsSet(username, now, refreshTokenExpireTime, scope);
        String refreshToken = this.encoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expireTime(accessTokenExpireTime)
                .build();
    }

    private static JwtClaimsSet getJwtClaimsSet(String username, Instant issuedAt, Instant expireTime, String scope) {
        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(issuedAt)
                .expiresAt(expireTime)
                .subject(username)
                .claim("scope", scope)
                .build();
    }

    public boolean validateTokenExpiry(String token) {
        try {
            Jwt refreshToken = decoder.decode(token);
            Instant expirationTime = refreshToken.getExpiresAt();
            return expirationTime!=null && !expirationTime.isBefore(Instant.now());
        } catch (Exception e) {
            return false;
        }
    }


    public String getUsername(String token) {
        try {
            Jwt refreshToken = decoder.decode(token);
            return refreshToken.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}