package name.snosminin.postingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Schema(description = "Create comment dto")
public class CreateCommentDto {

    @NotNull(message = "User id must be not null")
    private Long userId;

    @NotNull(message = "User id must be not null")
    private Long postId;

    @NotNull(message = "Name must be not null")
    @Length(max = 512, message = "Text must be shorter 512 symbols")
    private String text;
}
