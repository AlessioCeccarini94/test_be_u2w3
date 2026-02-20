package alessioceccarini.testu2w3.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EventDTO(
		@NotBlank(message = "please insert a name for the event")
		String eventName,
		String eventDescription,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		LocalDate eventDate,
		@Min(2)
		int numberOfParticipants) {
}
