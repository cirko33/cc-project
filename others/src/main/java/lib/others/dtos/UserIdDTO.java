package lib.others.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserIdDTO {
    @NotEmpty(message = "User id must not be empty")
    private String userId;
}
