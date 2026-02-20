package alessioceccarini.testu2w3.security;

import alessioceccarini.testu2w3.exceptions.UnauthorizedException;
import alessioceccarini.testu2w3.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTTFilter extends OncePerRequestFilter {


	private final JWTTTools jwttTools;
	private final UserService userService;

	@Autowired
	public JWTTFilter(JWTTTools jWTTools, UserService userService) {
		this.jwttTools = jWTTools;
		this.userService = userService;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Unauthorized");

		String accessToken = authHeader.replace("Bearer ", "");

		jwttTools.verifyToken(accessToken);

		chain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}
}