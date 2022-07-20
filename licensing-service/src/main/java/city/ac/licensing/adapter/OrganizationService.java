package city.ac.licensing.adapter;

import city.ac.licensing.dto.OrganizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("organization-service")
public interface OrganizationService {

    @GetMapping("/organization/{id}")
    OrganizationResponse getById(@PathVariable Long id);
}
