package city.ac.security.dto;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class RtsAuthority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;

	public RtsAuthority() {
		super();
	}

	public RtsAuthority(Long id, String role) {
		super();
		this.id = id;
		this.authority = role;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}

}
