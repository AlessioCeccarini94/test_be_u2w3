package alessioceccarini.testu2w3.controllers;

import alessioceccarini.testu2w3.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventRepo extends CrudRepository<Event, UUID> {
}
