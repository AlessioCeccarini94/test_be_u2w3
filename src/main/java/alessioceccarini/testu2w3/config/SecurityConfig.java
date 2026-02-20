package alessioceccarini.testu2w3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		//LOGIN DISABLED
		httpSecurity.formLogin(form -> form.disable());
		//CSRF ATTACK SECURITY DISABLED
		httpSecurity.csrf(csrf -> csrf.disable());
		//STATLESS
		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//PERMIT ALL HTTP REQUEST
		httpSecurity.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers("/**").permitAll()
				.requestMatchers("/eventManager/**").hasRole("ADMIN").anyRequest().authenticated());
		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
