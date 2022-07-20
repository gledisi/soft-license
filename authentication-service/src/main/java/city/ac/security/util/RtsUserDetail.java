package city.ac.security.util;

import java.util.Collection;
import java.util.Collections;

import city.ac.security.dto.RtsAuthority;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class RtsUserDetail implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;
	private final String username;
	private String password;
	private final RtsAuthority authority;
	private final boolean valid;
	private final String location;

	private RtsUserDetail(String username, String password, RtsAuthority authority, boolean valid,
						  String location) {
		this.username = username;
		this.password = password;
		this.authority =authority;
		this.valid = valid;
		this.location = location;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return valid;
	}

	public String getLocation() {
		return location;
	}

	public static class Builder {
		private String username;
		private String password;
		private RtsAuthority authority;
		private boolean valid;
		private String location;

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withAuthorities(RtsAuthority authority) {
			this.authority = authority;
			return this;
		}

		public Builder withValid(boolean valid) {
			this.valid = valid;
			return this;
		}
		
		public Builder withLocation(String location) {
			this.location = location;
			return this;
		}

		public RtsUserDetail build() {
			return new RtsUserDetail(username, password, authority, valid, location);
		}
	}

}
