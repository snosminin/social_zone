package name.snosminin.postingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(description = "Create like dto")
public class CreateLikeDto {

    @NotNull(message = "User id must be not null")
    private Long userId;

    @NotNull(message = "User id must be not null")
    private Long postId;
}
