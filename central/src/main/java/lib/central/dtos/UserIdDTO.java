package lib.central.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserIdDTO {
    @NotEmpty(message = "User Id is required")
    private Long userId;
}
