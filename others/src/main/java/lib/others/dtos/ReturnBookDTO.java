package lib.others.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReturnBookDTO {
    @NotNull(message = "Book ID is required")
    public Long bookId;
}
