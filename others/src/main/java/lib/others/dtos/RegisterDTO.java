package lib.others.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotEmpty(message = "Address is required")
    private String address;
    @NotEmpty(message = "JMBG is required")
    @Size(min = 13, max = 13, message = "JMBG must be 13 characters long")
    private String jmbg;
}
