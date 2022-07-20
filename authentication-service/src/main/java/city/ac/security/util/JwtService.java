package city.ac.security.util;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import city.ac.security.dto.RtsAuthority;
import city.ac.security.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtService {

	@Value("${auth.jwt.secret}")
	private String secret;

	@Value("${auth.jwt.valid-for}")
	private Long validFor;

	@Autowired
	private ObjectMapper objectMapper;

	public String issueToken(RtsUserDetail dpshtrrUserDetail) {
		String id = generateTokenIdentifier();
		ZonedDateTime issuedDate = ZonedDateTime.now();
		ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);
		// @formatter:off
			return Jwts.builder()
					.setId(id)
					.setSubject(dpshtrrUserDetail.getUsername())
					.setIssuedAt(Date.from(issuedDate.toInstant()))
					.setExpiration(Date.from(expirationDate.toInstant()))
					.claim(RtsClaim.ROLES, dpshtrrUserDetail.getAuthorities())
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
			// @formatter:on
	}

	private ZonedDateTime calculateExpirationDate(ZonedDateTime issuedDate) {
		return issuedDate.plusSeconds(validFor);
	}

	private String generateTokenIdentifier() {
		return UUID.randomUUID().toString();
	}

	public RtsTokenDetails parseToken(String token) {
		// @formatter:off
			try {
				Claims claims = Jwts.parser()
						.setSigningKey(secret)
						.parseClaimsJws(token)
						.getBody();
				return new RtsTokenDetails.Builder()
						.withId(extractTokenIdFromClaims(claims))	
						.withUsername(extractUsernameFromClaims(claims))
						.withAuthorities(extractAuthoritiesFromClaims(claims))
						.build();

			} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
				throw new InvalidTokenException("Invalid token", e);
			} catch (ExpiredJwtException e) {
				throw new InvalidTokenException("Expired token", e);
			} catch (InvalidClaimException e) {
				throw new InvalidTokenException("Invalid value for claim \"" + e.getClaimName() + "\"", e);
			} catch (Exception e) {
				throw new InvalidTokenException("Invalid token", e);
			}
			// @formatter:on
	}

	@SuppressWarnings({ "unchecked" })
	private List<RtsAuthority> extractAuthoritiesFromClaims(Claims claims) {
		List<Object> parsedObject = (ArrayList<Object>) claims.getOrDefault(RtsClaim.ROLES, new ArrayList<>());
		return parsedObject.stream().map(obj -> objectMapper.convertValue(obj, RtsAuthority.class))
				.collect(Collectors.toList());

	}

	private String extractTokenIdFromClaims(Claims claims) {
		return (String) claims.get(Claims.ID);
	}

	private String extractUsernameFromClaims(Claims claims) {
		return claims.getSubject();
	}

	private String extractLocationFromClaims(Claims claims, String dpshtrrClaim) {
		return (String) claims.get(dpshtrrClaim);
	}

}
