package lib.others.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentedBookDTO {
    private Long id;
    private String title;
    private String author;
    private Long userId;
    private String isbn;
    private LocalDate rentalDate;
}
