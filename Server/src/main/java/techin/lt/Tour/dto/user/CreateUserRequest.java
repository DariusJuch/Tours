package techin.lt.Tour.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotNull(message = "Username cannot be null")
        @NotBlank(message = "Username cannot be empty or consist only space")
        @Size(min = 3, max = 80, message = "Username can only contain letters and numbers")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain letters and numbers")
        String username,

        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be empty or consist only of spaces")
        @Pattern(regexp = "^(?=.{3,254}$)(?=.{1,64}@)(?!\\.)(?!.*\\.\\.)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]{1,253}\\.[A-Za-z]{2,}$",
                message = "Invalid email format. It must be in the format 'user@example.com'")
        String email,
        @NotNull(message = "Password cannot be null")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+-=\\[\\]{};:'\"\\\\|,.<>/?])[A-Za-z\\d!@#$%^&*()_+-=\\[\\]{};:'\"\\\\|,.<>/?]+$",
                message = "Password must contain at least one lowercase letter, one uppercase letter, " +
                        "one number, one special character, and be 8-255 characters long"
        )
        String password

) {
}
