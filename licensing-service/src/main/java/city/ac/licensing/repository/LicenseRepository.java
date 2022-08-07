package city.ac.licensing.repository;

import city.ac.licensing.entity.LicenseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface LicenseRepository extends CrudRepository<LicenseEntity,Long> {

    @Query("UPDATE LicenseEntity l SET l.maxNumber=l.maxNumber+?2 where l.id=?1")
    @Modifying
    @Transactional
    Boolean updateCount(Long licenseId,Long amount);

    @Modifying
    @Query(value = "insert into license_type(name,software_id) VALUES (?2,?1)",nativeQuery = true)
    @Transactional
    int addLicenseType(Long softwareId, String type);

    @Modifying
    @Query(value = "insert into license_allocation(name,license_id,assigned_employee_id,allocation_date,status_id) " +
            "VALUES (?1,?2,?3,?4)",nativeQuery = true)
    @Transactional
    Boolean assignLicense(Long licenseId, Long employeeId, LocalDate date,Long statusId);

    @Modifying
    @Query(value = "UPDATE LicenseAllocationEntity la SET la.deletedDate=?2,la.status=?3 where la.id=?1")
    @Transactional
    Boolean deallocateLicense(Long id,LocalDate date,Long statusId);
}
