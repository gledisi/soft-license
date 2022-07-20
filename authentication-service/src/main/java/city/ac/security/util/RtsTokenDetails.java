package city.ac.security.util;


import city.ac.security.dto.RtsAuthority;

import java.util.Collections;
import java.util.List;


public class RtsTokenDetails {

	private final String id;
	private final String username;
	private final List<RtsAuthority> authorities;
	private final boolean valid;
	private final String location;

	public RtsTokenDetails(String id, String username, List<RtsAuthority> authorities, boolean valid,
						   String location) {
		this.id = id;
		this.username = username;
		this.authorities = authorities;
		this.valid = valid;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public List<RtsAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isValid() {
		return valid;
	}

	public String getLocation() {
		return location;
	}


	public static class Builder {
		private String id;
		private String username;
		private List<RtsAuthority> authorities;
		private boolean valid;
		private String location;

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withAuthorities(List<RtsAuthority> authorities) {
			this.authorities = authorities == null ? Collections.emptyList() : authorities;
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

		public RtsTokenDetails build() {
			return new RtsTokenDetails(id, username, authorities, valid, location);
		}
	}

}
