package name.snosminin.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import name.snosminin.authservice.dto.AuthRequest;
import name.snosminin.authservice.dto.AuthResponse;
import name.snosminin.authservice.service.AuthService;
import name.snosminin.authservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtServiceImpl jwtServiceImpl;

    @Override
    public AuthResponse login(final AuthRequest loginRequest) {

        var authResponse = new AuthResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        var user = userService.getByUsername(loginRequest.getUsername());

        authResponse.setId(user.getId());
        authResponse.setUsername(user.getUsername());

        authResponse.setAccessToken(jwtServiceImpl.createAccessToken(
                user.getId(),
                user.getUsername(),
                user.getRole()));

        authResponse.setRefreshToken(jwtServiceImpl.createRefreshToken(
                user.getId(),
                user.getUsername()));

        return authResponse;
    }

    @Override
    public AuthResponse refresh(final String refreshToken) {

        return jwtServiceImpl.refreshUserTokens(refreshToken);
    }
}