package name.snosminin.authservice.service;

import name.snosminin.authservice.dto.AuthRequest;
import name.snosminin.authservice.dto.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    AuthResponse refresh(String refreshToken);
}
