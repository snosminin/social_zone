package name.snosminin.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String passwordConfirmation;
}
