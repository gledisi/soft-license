package city.ac.licensing.controller;

import city.ac.licensing.dto.LicenseRequest;
import city.ac.licensing.dto.LicenseResponse;
import city.ac.licensing.entity.EmployeeLicenseRequestEntity;
import city.ac.licensing.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    private final LicenseService service;

    @Autowired
    public LicenseController(LicenseService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public LicenseResponse getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping(value = "/new-request")
    public EmployeeLicenseRequestEntity requestLicense(@RequestBody LicenseRequest request) {
        return service.requestLicense(request);
    }

    @PostMapping(value = "/addLicenseType")
    public Boolean requestLicense(@RequestParam Long softwareId,@RequestParam String type) {
        return service.newLicenseType(softwareId,type);
    }

    @PostMapping(value = "/assign")
    public Boolean assignLicense(@RequestParam Long licenseId,@RequestParam Long employeeId) {
        return service.assignLicense(licenseId,employeeId);
    }

    @PostMapping(value = "/deallocate")
    public Boolean assignLicense(@RequestParam Long id) {
        return service.deallocateLicense(id);
    }

    @PutMapping(value = "/approve")
    public Boolean approveLicense(@RequestParam Long requestLicenseId) {
        return service.approveLicense(requestLicenseId);
    }

    @PutMapping(value = "/new-purchase")
    public Boolean newPurchase(@RequestParam Long licenseId,@RequestParam Long amount) {
        return service.updateLicenseCount(licenseId,amount);
    }
}
