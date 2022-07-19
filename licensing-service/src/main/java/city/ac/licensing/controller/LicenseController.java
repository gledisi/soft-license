package city.ac.licensing.controller;

import city.ac.licensing.dto.LicenseResponse;
import city.ac.licensing.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    @Value("${example.property:}")
    private String personName;
    private final LicenseService service;

    @Autowired
    public LicenseController(LicenseService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public LicenseResponse getById(@PathVariable Long id){
        return service.getById(id);
    }
}
