package city.ac.security.util;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LicenseUserDetail implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;
	private final String username;
	private String password;
	private final LicenseAuthority authority;
	private final boolean valid;
	private LicenseUserDetail(String username, String password, LicenseAuthority authority, boolean valid) {
		this.username = username;
		this.password = password;
		this.authority =authority;
		this.valid = valid;
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

	public static class Builder {
		private String username;
		private String password;
		private LicenseAuthority authority;
		private boolean valid;

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withAuthorities(LicenseAuthority authority) {
			this.authority = authority;
			return this;
		}

		public Builder withValid(boolean valid) {
			this.valid = valid;
			return this;
		}


		public LicenseUserDetail build() {
			return new LicenseUserDetail(username, password, authority, valid);
		}
	}

}
