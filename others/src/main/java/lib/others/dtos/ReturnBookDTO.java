package lib.others.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReturnBookDTO {
    @NotEmpty(message = "ISBN is required")
    public String isbn;
    @NotEmpty(message = "User ID is required")
    public Long userId;
}
