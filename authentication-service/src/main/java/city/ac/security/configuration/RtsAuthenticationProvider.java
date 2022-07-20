package city.ac.security.configuration;

import city.ac.security.util.JwtService;
import city.ac.security.util.RtsTokenDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class RtsAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private JwtService jwtService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String authenticationToken = (String) authentication.getCredentials();
		RtsTokenDetails dpshtrrTokenDetails = jwtService.parseToken(authenticationToken);
		return new RtsJwtAuthentication(dpshtrrTokenDetails);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (RtsJwtAuthentication.class.isAssignableFrom(authentication));
	}
}