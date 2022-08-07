package city.ac.licensing.service;


import city.ac.licensing.dto.SoftwareResponse;
import city.ac.licensing.mapper.SoftwareMapper;
import city.ac.licensing.repository.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService {
    private final SoftwareRepository repository;

    @Autowired
    public SoftwareService(SoftwareRepository repository) {
        this.repository = repository;
    }

    public SoftwareResponse getById(Long id){
        return SoftwareMapper.toResponse(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    public List<SoftwareResponse> getByOrganization(Long id){
        return SoftwareMapper.toResponse(repository.findByOrganization(id));
    }
}
