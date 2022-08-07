package city.ac.organization.service;

import city.ac.organization.dto.EmployeeResponse;
import city.ac.organization.entity.EmployeeEntity;
import city.ac.organization.mapper.EmployeeMapper;
import city.ac.organization.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;


    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeResponse> getAll(Long organizationId) {
        return EmployeeMapper.toResponse(repository.findByOrganization(organizationId));
    }

    public EmployeeResponse getById(Long id) {
        EmployeeEntity entity = repository.findById(id).orElseThrow(RuntimeException::new);
        return EmployeeMapper.toResponse(entity);
    }

}
