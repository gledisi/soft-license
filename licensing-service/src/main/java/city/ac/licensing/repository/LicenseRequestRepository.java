package city.ac.licensing.repository;

import city.ac.licensing.entity.EmployeeLicenseRequestEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRequestRepository extends CrudRepository<EmployeeLicenseRequestEntity, Long> {

    @Query("select req From EmployeeLicenseRequestEntity req WHERE req.license.organizationId=?1")
    List<EmployeeLicenseRequestEntity> findAllByOrganization(Long organizationId);
    @Query("UPDATE EmployeeLicenseRequestEntity req SET req.status.id=?1  WHERE req.id=?2")
    @Modifying
    int approve(Long statusId,Long requestId);
}
