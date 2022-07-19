package city.ac.organization.service;

import city.ac.organization.dto.OrganizationResponse;
import city.ac.organization.mapper.Mapper;
import city.ac.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    @Autowired
    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    public OrganizationResponse getById(Long id){
        return Mapper.toResponse(repository.findById(id).orElseThrow(RuntimeException::new));
    }
}
