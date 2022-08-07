package city.ac.licensing.repository;

import city.ac.licensing.dto.SoftwareResponse;
import city.ac.licensing.entity.SoftwareEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoftwareRepository extends CrudRepository<SoftwareEntity,Long> {

    @Query("SELECT distinct s FROM SoftwareEntity s where exists (select l.id from LicenseEntity l where l.organizationId=?1)")
    List<SoftwareEntity> findByOrganization(Long organizationId);

}
