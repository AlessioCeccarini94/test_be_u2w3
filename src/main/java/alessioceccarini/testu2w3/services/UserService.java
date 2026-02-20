package alessioceccarini.testu2w3.services;

import alessioceccarini.testu2w3.entities.User;
import alessioceccarini.testu2w3.exceptions.AlreadyExistException;
import alessioceccarini.testu2w3.exceptions.NotFoundException;
import alessioceccarini.testu2w3.payload.UserDTO;
import alessioceccarini.testu2w3.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public User saveEmployee(UserDTO userDTO) {
		this.userRepo.findByEmail(userDTO.email()).ifPresent(user -> {
			throw new AlreadyExistException("User " + userDTO.email() + " already exists");
		});
		User newUser = new User(
				userDTO.firstName(),
				userDTO.lastName(),
				userDTO.email(),
				passwordEncoder.encode(userDTO.password()),
				userDTO.role());
		return this.userRepo.save(newUser);
	}

	public Page<User> findAllUsers(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "name";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return this.userRepo.findAll(pageable);
	}

	public User findUserByEmail(String email) {
		return this.userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("User " + email + " not found"));
	}

	public void deleteEmployee(UUID id) {
		this.userRepo.deleteById(id);
	}
}
