package name.snosminin.authservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {

    @NotNull(message = "Id must be not null")
    private String username;

    @NotNull(message = "Id must be not null")
    private String password;
}
