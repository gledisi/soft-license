package city.ac.licensing.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class OAuth2UserClientFeignConfiguration {

    @Bean(name = "userFeignClientInterceptor")
    public RequestInterceptor getUserFeignClientInterceptor() throws IOException {
        return new OAuth2FeignRequestInterceptor();
    }
}