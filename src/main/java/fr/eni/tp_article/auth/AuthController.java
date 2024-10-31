package fr.eni.tp_article.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.tp_article.ServiceResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@RestController
public class AuthController {

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	public Key getKey() {
		// Creer une clé
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@GetMapping("/login")
	public String login() {
		
		// Pour genere un token
		// -- claims => pour stocker les clé valeur à crypter
		// -- la date ou ca été crée (IssueAt)
		// -- une date d'expiration (Expiration)
		// -- une donnée subjectif (Subject)
		// -- l'algo pour crypter (HS256)
		// -- la clé secrête
	
		Map<String, Object> claims = new HashMap<String, Object>();
		
		// -- temps de vie du token
		Date tokenLifetime = new Date(System.currentTimeMillis() + 1000 * 60 * 24);
		
		// Le code qui genere le token
		String token = Jwts.builder().setClaims(claims)
			.setSubject("ismael@gmail.com")
			.issuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(tokenLifetime)
			.signWith(getKey(), SignatureAlgorithm.HS256)
			.compact();
		
		// Retourner le token généré
		
		return token;
	}
	
	@GetMapping("/check")
	public ServiceResponse<Boolean> check(@RequestHeader("Authorization") String authorization) {
				
		// Récupérer le token dans le header authorization
		// On substring 7 caractères car le header contient "Bearer tontoken"
		String token = authorization.substring(7);
		
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(getKey()).build()
					.parseClaimsJws(token)
					.getBody();
			
			
			// -- récupérer la date d'expiration
			Function<Claims, Date> expirationFunction = Claims::getExpiration;
			Date expirationData = expirationFunction.apply(claims);
			
			// -- récupérer l'email
			String email = claims.getSubject();
			
			// -- tester que le date ne dépasse le temps actuel
			// -- si la date d'expiration est inférieur à tout de suite
			// -- true : donc invalide
			if (expirationData.before(new Date())){
				return ServiceResponse.buildReponse("769", "Token expiré", false);
			}
		} catch (Exception e) {
			return ServiceResponse.buildReponse("767", "Token invalide", false);
		}
	
		return ServiceResponse.buildReponse("204", "Token valide", true);
	}
}
