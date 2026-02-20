package alessioceccarini.testu2w3.services;

import alessioceccarini.testu2w3.entities.User;
import alessioceccarini.testu2w3.exceptions.UnauthorizedException;
import alessioceccarini.testu2w3.payload.LoginDTO;
import alessioceccarini.testu2w3.security.JWTTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


	private final UserService userService;
	private final JWTTTools jwttTools;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthenticationService(UserService userService, JWTTTools jwttTools, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.jwttTools = jwttTools;
		this.passwordEncoder = passwordEncoder;

	}

	public String credentialcheckAndTokenGen(LoginDTO loginDTO) {

		// Cercare user tramite email
		User user = this.userService.findUserByEmail(loginDTO.email());
		//confrontare se password combaciano
		if (passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
			// Generare Token (classe JWTTools)
			String token = jwttTools.generateToken(user);
			return token;
		} else {
			throw new UnauthorizedException("Wrong password");
		}
	}
}
