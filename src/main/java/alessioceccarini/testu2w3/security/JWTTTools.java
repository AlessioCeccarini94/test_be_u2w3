package alessioceccarini.testu2w3.security;

import alessioceccarini.testu2w3.entities.User;
import alessioceccarini.testu2w3.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTTTools {

	@Value("${jwtt.secret}")
	private String secret;

	public String generateToken(User user) {
		return Jwts.builder()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.subject(String.valueOf(user.getId())).claim("role", user.getRole().name())
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.compact();
	}

	public void verifyToken(String token) {
		try {
			Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid token");
		}
	}

	public UUID extractUserId(String token) {
		return UUID.fromString(
				Jwts.parser()
						.setSigningKey(secret.getBytes())
						.build()
						.parseClaimsJws(token)
						.getBody()
						.getSubject()
		);
	}
}
