package alessioceccarini.testu2w3.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record LoginDTO(
		@Email
		String email,
		@Size(min = 7, max = 20, message = "please insert a valid password. The password must be at least 7 character long and shorter than 20")
		String password) {
}
