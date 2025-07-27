package name.snosminin.postingservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(description = "Create follow dto")
public class CreateFollowDto {

    @NotNull(message = "Follower id must be not null")
    private Long followerId;

    @NotNull(message = "Followee id must be not null")
    private Long followeeId;
}
