package lib.others.dtos;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRentDTO {
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Author is required")
    private String author;
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotEmpty(message = "ISBN is required")
    private String isbn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;
}
