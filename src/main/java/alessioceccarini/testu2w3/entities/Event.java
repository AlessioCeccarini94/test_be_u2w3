package alessioceccarini.testu2w3.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Setter(AccessLevel.NONE)
	private UUID eventId;
	private String eventName;
	private String eventDescription;
	private LocalDate eventDate;
	private int numberOfParticipants;
	@ManyToOne
	@JoinColumn(name = "events_mangers")
	private User user;

	public Event(String eventName, String eventDescription, LocalDate eventDate, int numberOfParticipants) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.numberOfParticipants = numberOfParticipants;
	}
}
