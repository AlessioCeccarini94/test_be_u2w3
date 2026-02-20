package alessioceccarini.testu2w3.repositories;

import alessioceccarini.testu2w3.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends CrudRepository<User, UUID> {
	Optional<User> findByEmail(String email);

}
