package city.ac.security.api;

import java.util.List;
import java.util.stream.Collectors;


import city.ac.security.dto.LoginRequest;
import city.ac.security.dto.LoginResponse;
import city.ac.security.dto.Role;
import city.ac.security.dto.RtsAuthority;
import city.ac.security.util.Endpoints;
import city.ac.security.util.JwtService;
import city.ac.security.util.RtsUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private JwtService jwtProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
		LOGGER.info("Try authenticate user: {}", request.getUsername());
		Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(request.getUsername(),
				request.getPassword());
		Authentication authenticationResult = authenticationManager.authenticate(authenticationRequest);
		LoginResponse response = createResponse(authenticationResult);
		LOGGER.info("Logged user: {}", response);
		return ResponseEntity.ok(response);
	}

	private LoginResponse createResponse(Authentication authentication) {
		RtsUserDetail userDetail = (RtsUserDetail) authentication.getPrincipal();
		LoginResponse response = new LoginResponse();
		response.setUsername(userDetail.getUsername());
		List<Role> roles = userDetail.getAuthorities().stream().map(p -> new Role(((RtsAuthority) p).getId(), p.getAuthority())).collect(Collectors.toList());
		response.setRoles(roles);
		String generatedToken = jwtProvider.issueToken(userDetail);
		response.setToken(generatedToken);
		return response;
	}
}
