package city.ac.security.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import city.ac.security.exceptions.ApiError;
import city.ac.security.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		ApiError errorDetails;

		if (authException instanceof InvalidTokenException) {
			errorDetails = new ApiError("InvalidTokenException", HttpStatus.UNAUTHORIZED);
		} else {
			errorDetails = new ApiError(authException.getMessage(), HttpStatus.FORBIDDEN);
		}
		response.setStatus(errorDetails.getStatus().value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		mapper.writeValue(response.getWriter(), errorDetails);
	}
}
