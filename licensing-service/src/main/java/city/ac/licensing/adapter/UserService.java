package city.ac.licensing.adapter;

import city.ac.licensing.config.AuthorizedUserFeignClient;
import city.ac.licensing.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AuthorizedUserFeignClient(name = "authentication-service")
public interface UserService {

    @GetMapping("/users/{id}")
    UserResponse getById(@PathVariable Long id);

}
