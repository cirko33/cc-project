package lib.others.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookRentDTO {
    @NotEmpty(message = "Title is required")
    private String title;
    @NotEmpty(message = "Author is required")
    private String author;
    @NotEmpty(message = "User ID is required")
    private int userId;
    @NotEmpty(message = "ISBN is required")
    private String isbn;
    @NotEmpty(message = "Rental date is required")
    private Date rentalDate;
}
