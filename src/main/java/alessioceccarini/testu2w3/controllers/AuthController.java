package alessioceccarini.testu2w3.controllers;

import alessioceccarini.testu2w3.payload.LoginDTO;
import alessioceccarini.testu2w3.payload.LoginResDTO;
import alessioceccarini.testu2w3.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationService authService;

	@Autowired
	public AuthController(AuthenticationService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public LoginResDTO login(@RequestBody LoginDTO loginDTO) throws Exception {

		return new LoginResDTO(this.authService.credentialcheckAndTokenGen(loginDTO));
	}
}

