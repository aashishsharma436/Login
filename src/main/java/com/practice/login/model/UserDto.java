package com.practice.login.model;

import com.practice.login.utils.ValidCountryCode;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserDto {

    @NotEmpty(message = "password can not be empty")
    @Pattern(
            regexp = "^(?=(.*[A-Z]){2})(?=(.*\\d){2})(?=(.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]){2})[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{12,20}$",
            message = "Password must be between 12 and 20 characters long, and contain at least 2 uppercase letters, 2 digits, and 2 special characters."
    )
    private String password;

    @NotEmpty(message = "First Name can not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name can not be empty")
    private String lastName;

    @NotNull(message = "Date of Birth can not be empty")
    @Past(message = "Date of Birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Mobile can not empty")
    @Pattern(regexp = "^[0-9]+$", message = "Mobile number must contain only digits")
    @Size(min = 5, max = 17, message = "Mobile number length should be in between 5 to 17")
    private String mobile;

    @NotEmpty(message = "Address can not empty")
    private String address;

    @NotNull(message = "Country code cannot be null")
    @ValidCountryCode(message = "Country code must start with a '+' followed by 1 to 3 digits")
    private String countryCode;
}
