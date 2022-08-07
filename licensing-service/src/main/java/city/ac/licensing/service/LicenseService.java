package city.ac.licensing.service;

import city.ac.licensing.adapter.OrganizationService;
import city.ac.licensing.dto.LicenseRequest;
import city.ac.licensing.dto.LicenseRequestResponse;
import city.ac.licensing.dto.LicenseResponse;
import city.ac.licensing.entity.EmployeeLicenseRequestEntity;
import city.ac.licensing.mapper.LicenseMapper;
import city.ac.licensing.repository.LicenseRepository;
import city.ac.licensing.repository.LicenseRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LicenseService {
    private final LicenseRepository repository;

    private final LicenseRequestRepository requestRepository;
    private final OrganizationService organizationService;
    @Autowired
    public LicenseService(LicenseRepository repository, LicenseRequestRepository requestRepository, OrganizationService organizationService) {
        this.repository = repository;
        this.requestRepository = requestRepository;
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

    public List<LicenseRequestResponse> getAllLicenseRequests(Long organizationId){
        return LicenseMapper
                .toResponse(requestRepository.findAllByOrganization(organizationId));
    }

    public EmployeeLicenseRequestEntity requestLicense(LicenseRequest request) {
        return requestRepository.save(LicenseMapper.toRequestEntity(request));
    }

    public Boolean approveLicense(Long requestLicenseId) {
        return requestRepository.approve(2L,requestLicenseId)>0;
    }

    public Boolean updateLicenseCount(Long licenseId,Long amount) {
        return repository.updateCount(licenseId,amount);
    }

    public Boolean newLicenseType(Long softwareId, String type) {
        return repository.addLicenseType(softwareId,type)>0;
    }

    public Boolean assignLicense(Long licenseId, Long employeeId) {
        return repository.assignLicense(licenseId,employeeId, LocalDate.now(),1L);
    }

    public Boolean deallocateLicense(Long id) {
        return repository.deallocateLicense(id,LocalDate.now(),2L);
    }
}
