package city.ac.security;

import city.ac.security.configuration.JwtAuthenticationEntryPoint;
import city.ac.security.util.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${auth.cors.allowed-origins}")
    private String allowedOrigins;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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

        http.authorizeRequests().antMatchers(Endpoints.LOGIN).permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);


        http.headers().frameOptions().disable();

        // @formatter:on
    }


    private String[] getDisabledUrlPaths() {
        return new String[]{Endpoints.CSS_RESOURCES, Endpoints.IMG_RESOURCES, Endpoints.JS_RESOURCES,
                Endpoints.API_DOCS, Endpoints.SWAGGER_RESOURCES, Endpoints.CONFIGURATION_UI,
                Endpoints.CONFIGURATION_SECURITY, Endpoints.SWAGGER, Endpoints.WEBJARS};
    }
}
