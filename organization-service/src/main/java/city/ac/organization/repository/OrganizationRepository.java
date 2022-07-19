package city.ac.organization.repository;

import city.ac.organization.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<OrganizationEntity,Long> {
}
