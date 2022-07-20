package city.ac.security.dto;


import java.util.List;


public class LoginResponse {

	private String username;

	private List<Role> roles;

	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "[username=" + username + ", roles=" + roles + "]";
	}
}
