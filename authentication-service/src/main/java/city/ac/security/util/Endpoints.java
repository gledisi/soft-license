package city.ac.security.util;

public class Endpoints {

	private Endpoints() {
		super();
	}

	//All
	public static final String ALL = "/**";
	//Authentication Endpoints
	public static final String AUTH= "authentication-service/";
	//USER
	public static final String LOGIN = "/login";
	public static final String USERS = "/users";



	//resources
	public static final String CSS_RESOURCES = "/css/**";
	public static final String IMG_RESOURCES = "/img/**";
	public static final String JS_RESOURCES = "/js/**";
	public static final String API_DOCS = "/v2/api-docs";
	public static final String SWAGGER_RESOURCES = "/swagger-resources/**";
	public static final String CONFIGURATION_UI = "/configuration/ui/**";
	public static final String CONFIGURATION_SECURITY = "/configuration/security";
	public static final String SWAGGER = "/swagger-ui.html";
	public static final String WEBJARS = "/webjars/**";

}
