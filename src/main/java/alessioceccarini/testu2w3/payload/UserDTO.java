package alessioceccarini.testu2w3.payload;

import alessioceccarini.testu2w3.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
		@NotBlank(message = "please insert your name")
		String firstName,
		@NotBlank(message = "please insert your surname")
		String lastName,
		@Email(message = "please insert a valid e-mail address")
		String email,
		@Size(min = 7, max = 20, message = "please insert a valid password. The password must be at least 7 character long and shorter than 20")
		String password,
		@NotNull
		Role role) {
}
