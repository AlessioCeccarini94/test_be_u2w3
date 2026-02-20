package alessioceccarini.testu2w3.repositories;

import alessioceccarini.testu2w3.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventRepo extends CrudRepository<Event, UUID> {
}
