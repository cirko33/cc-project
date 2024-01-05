package lib.central.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserIdDTO {
    @NotNull(message = "User ID is required")
    private Long userId;
}
