package city.ac.licensing.service;

import city.ac.licensing.adapter.OrganizationService;
import city.ac.licensing.dto.LicenseResponse;
import city.ac.licensing.mapper.LicenseMapper;
import city.ac.licensing.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    private final LicenseRepository repository;
    private final OrganizationService organizationService;
    @Autowired
    public LicenseService(LicenseRepository repository, OrganizationService organizationService) {
        this.repository = repository;
        this.organizationService = organizationService;
    }
    public LicenseResponse getById(Long id){
        LicenseResponse response = LicenseMapper.
                toResponse(repository.
                        findById(id).
                        orElseThrow(RuntimeException::new));
        response.organization = organizationService.getById(1L);
        return response;
    }
}
