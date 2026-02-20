package alessioceccarini.testu2w3.services;

import alessioceccarini.testu2w3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


		private final UserService userService;
		private final JWTTools jwtTools;
		private final PasswordEncoder passwordEncoder;

		@Autowired
		public AuthenticationService(UserService userService, JWTTools jwtTools, PasswordEncoder passwordEncoder) {
			this.userService = userService;
			this.jwtTools = jwtTools;
			this.passwordEncoder = passwordEncoder;

		}

		public String credentialcheckAndTokenGen(LoginDTO loginDTO) {

			// Cercare employee tramite email
			User user = this.userService.();
			//confrontare se password combaciano
			if (passwordEncoder.matches(loginDTO.password(), employee.getPassword())) {
				// Generare Token (classe JWTTools)
				String token = jwtTools.generateToken(employee);
				return token;
			} else {
				throw new UnauthorizedException("Wrong password");
			}
		}
	}
}
