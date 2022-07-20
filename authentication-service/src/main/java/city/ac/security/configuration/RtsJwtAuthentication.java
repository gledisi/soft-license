package city.ac.security.configuration;

import city.ac.security.util.RtsTokenDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class RtsJwtAuthentication extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	private String authenticationToken;
	private transient RtsTokenDetails dpshtrrTokenDetails;

	public RtsJwtAuthentication(String authenticationToken) {
		super(AuthorityUtils.NO_AUTHORITIES);
		this.authenticationToken = authenticationToken;
		this.setAuthenticated(false);
	}

	public RtsJwtAuthentication(RtsTokenDetails dpshtrrTokenDetails) {
		super(dpshtrrTokenDetails.getAuthorities());
		this.eraseCredentials();
		this.dpshtrrTokenDetails = dpshtrrTokenDetails;
		super.setAuthenticated(true);
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		if (authenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted. Use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return authenticationToken;
	}

	@Override
	public Object getPrincipal() {
		return this.dpshtrrTokenDetails;
	}

	@Override
	public Object getDetails() {
		return dpshtrrTokenDetails;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.authenticationToken = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authenticationToken == null) ? 0 : authenticationToken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RtsJwtAuthentication other = (RtsJwtAuthentication) obj;
		if (authenticationToken == null) {
			if (other.authenticationToken != null)
				return false;
		} else if (!authenticationToken.equals(other.authenticationToken)) {
			return false;
		}
			
		return true;
	}


}
