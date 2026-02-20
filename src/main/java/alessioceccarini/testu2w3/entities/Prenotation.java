package alessioceccarini.testu2w3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prenotation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Event event;

	public Prenotation(User user, Event event) {
		this.user = user;
		this.event = event;
	}
}
