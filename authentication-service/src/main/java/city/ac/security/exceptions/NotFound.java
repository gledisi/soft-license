package city.ac.security.exceptions;

public enum NotFound {

	USER("User not found!"), 
	ROLE("Role not found!");
	private NotFound(String message) {
		this.message = message;
	}

	private final String message;

	public String getMessage() {
		return message;
	}
}