package city.ac.licensing.repository;

import city.ac.licensing.entity.LicenseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends CrudRepository<LicenseEntity,Long> {
}
