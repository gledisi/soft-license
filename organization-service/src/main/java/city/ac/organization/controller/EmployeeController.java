package city.ac.organization.controller;

import city.ac.organization.dto.EmployeeResponse;
import city.ac.organization.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeResponse> getAll(@RequestParam Long organizationId) {
        return service.getAll(organizationId);
    }

    @GetMapping(value = "/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
