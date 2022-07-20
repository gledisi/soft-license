package city.ac.security;

import java.util.Arrays;
import java.util.stream.Collectors;


import city.ac.security.configuration.JwtAuthenticationEntryPoint;
import city.ac.security.configuration.JwtAuthenticationFilter;
import city.ac.security.configuration.RtsAuthenticationProvider;
import city.ac.security.util.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class RtsSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${auth.cors.allowed-origins}")
	private String allowedOrigins;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private RtsAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		return new JwtAuthenticationFilter(authenticationManagerBean(), authenticationEntryPoint, getRequestMatcher());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	public void configure(WebSecurity http) {
		http.ignoring().antMatchers(getDisabledUrlPaths());
		http.ignoring().antMatchers(HttpMethod.OPTIONS);
	}

	@Bean
	public CorsFilter corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin(allowedOrigins);
		configuration.setAllowedHeaders(
				Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.CACHE_CONTROL));
		configuration.setAllowedMethods(
				Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration(Endpoints.ALL, configuration);
		return new CorsFilter(source);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers(Endpoints.LOGIN).permitAll().antMatchers(Endpoints.USERS).permitAll()
				.anyRequest().authenticated();

		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		http.headers().frameOptions().disable();

		// @formatter:on
	}

	private RequestMatcher getRequestMatcher() {
		return new OrRequestMatcher(new AntPathRequestMatcher(Endpoints.LOGIN, HttpMethod.POST.toString()),
				new AntPathRequestMatcher(Endpoints.USERS, HttpMethod.POST.toString()));
	}

	private String[] getDisabledUrlPaths() {
		return new String[] { Endpoints.CSS_RESOURCES, Endpoints.IMG_RESOURCES, Endpoints.JS_RESOURCES,
				Endpoints.API_DOCS, Endpoints.SWAGGER_RESOURCES, Endpoints.CONFIGURATION_UI,
				Endpoints.CONFIGURATION_SECURITY, Endpoints.SWAGGER, Endpoints.WEBJARS };
	}
}
