package name.snosminin.authservice.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
