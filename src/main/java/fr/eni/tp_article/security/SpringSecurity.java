package fr.eni.tp_article.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// Rédéfinir les habilitations des urls/routes
		http.authorizeHttpRequests(
				(authorize) -> authorize 
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated()
		);
				
		// Configurer l'authentification de Spring Security
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf(csrf -> {
			csrf.disable();
		});
		
		
		return http.build();
	}
}