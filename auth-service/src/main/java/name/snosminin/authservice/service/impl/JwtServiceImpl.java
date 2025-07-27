package name.snosminin.authservice.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import name.snosminin.authservice.dto.AuthResponse;
import name.snosminin.authservice.exception.AccessDeniedException;
import name.snosminin.authservice.model.Role;
import name.snosminin.authservice.service.JwtService;
import name.snosminin.authservice.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final String key = "sdfypfo37yo2o73vf2qf7bbfjgdfkjghdl45dfgsdDFHKDFIEUGKVJy34545j34khwefaev74qv3bbb";

    private final long access = 3_000_000_000L;
    private final long refresh = 3_000_000_000L;

    public String createAccessToken(Long userId, String username, Role role) {

        var claims = Jwts
                .claims()
                .subject(username)
                .add("id", userId)
                .add("role", role.name())
                .build();

        var now = new Date();
        var validity = new Date(now.getTime() + access);

        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(getSignKey())
                .compact();
    }

    public String createRefreshToken(Long userId, String username) {

        var claims = Jwts
                .claims()
                .subject(username)
                .add("id", userId)
                .build();

        var now = new Date();
        var validity = new Date(now.getTime() + refresh);

        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(getSignKey())
                .compact();
    }

    public AuthResponse refreshUserTokens(String refreshToken) {

        var authResponse = new AuthResponse();
        if (!validateToken(refreshToken)) {
            throw new AccessDeniedException();
        }

        var userId = Long.valueOf(getId(refreshToken));
        var user = userService.getById(userId);
        authResponse.setId(userId);
        authResponse.setUsername(user.getUsername());
        authResponse.setAccessToken(createAccessToken(userId, user.getUsername(), user.getRole()));

        return authResponse;
    }

    public boolean validateToken(String token) {

        var claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);

        return !claims.getPayload().getExpiration().before(new Date());
    }

    private String getId(String token) {

        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id").toString();
    }

    private String getUsername(String token) {

        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {

        var username = getUsername(token);
        var userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities());
    }

    private Key getSignKey() {
        var keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getSecretKey() {
        var keyBytes = Base64.getDecoder().decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
