package city.ac.organization.controller;

import city.ac.organization.dto.OrganizationResponse;
import city.ac.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public OrganizationResponse getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/hello")
    public String hello(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        return "Hello! " + details.getDecodedDetails();
    }
}
