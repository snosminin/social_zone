package name.snosminin.postingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Schema(description = "Create user dto")
public class CreateUserDto {

    @NotNull(message = "First name must be not null")
    @Length(max = 255, message = "Name must be shorter 255 symbols")
    private String firstName;

    @NotNull(message = "Middle name must be not null")
    @Length(max = 255, message = "Name must be shorter 255 symbols")
    private String middleName;

    @NotNull(message = "Last name must be not null")
    @Length(max = 255, message = "Name must be shorter 255 symbols")
    private String lastName;

    @NotNull(message = "User name must be not null")
    @Length(max = 255, message = "Name must be shorter 255 symbols")
    private String username;

    @NotNull(message = "Mobile number must be not null")
    @Length(max = 255, message = "Name must be shorter 255 symbols")
    private String mobile;

    @NotNull(message = "Email must be not null")
    @Length(max = 255, message = "Name must be shorter 255 symbols")
    private String email;
}
