package city.ac.security.util;


import java.util.Collections;
import java.util.List;


public class LicenseTokenDetails {

	private final String id;
	private final String username;
	private final List<LicenseAuthority> authorities;
	private final boolean valid;

	public LicenseTokenDetails(String id, String username, List<LicenseAuthority> authorities, boolean valid) {
		this.id = id;
		this.username = username;
		this.authorities = authorities;
		this.valid = valid;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public List<LicenseAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isValid() {
		return valid;
	}


	public static class Builder {
		private String id;
		private String username;
		private List<LicenseAuthority> authorities;
		private boolean valid;

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withAuthorities(List<LicenseAuthority> authorities) {
			this.authorities = authorities == null ? Collections.emptyList() : authorities;
			return this;
		}

		public Builder withValid(boolean valid) {
			this.valid = valid;
			return this;
		}

		public LicenseTokenDetails build() {
			return new LicenseTokenDetails(id, username, authorities, valid);
		}
	}

}
