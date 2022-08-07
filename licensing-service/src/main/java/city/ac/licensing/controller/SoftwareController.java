package city.ac.licensing.controller;

import city.ac.licensing.dto.SoftwareResponse;
import city.ac.licensing.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/software")
public class SoftwareController {

    private final SoftwareService service;

    @Autowired
    public SoftwareController(SoftwareService service) {
        this.service = service;
    }

    @GetMapping
    public List<SoftwareResponse> getById(@RequestParam Long id) {
        return service.getByOrganization(id);
    }
}
