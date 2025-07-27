package name.snosminin.authservice.service;

import name.snosminin.authservice.dto.AuthResponse;
import name.snosminin.authservice.model.Role;
import org.springframework.security.core.Authentication;

public interface JwtService {

    String createAccessToken(Long userId, String username, Role role);

    String createRefreshToken(Long userId, String username);

    AuthResponse refreshUserTokens(String refreshToken);

    boolean validateToken(String token);

    Authentication getAuthentication(String token);
}
