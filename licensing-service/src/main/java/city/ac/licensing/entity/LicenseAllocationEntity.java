package city.ac.licensing.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "license_allocation", schema = "public")
public class LicenseAllocationEntity implements Serializable {
    private static final long serialVersionUID = -4820756067888551977L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "allocation_date", nullable = false)
    private LocalDate allocationDate;

    @Column(name = "deleted_date")
    private LocalDate deletedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "license_id")
    private LicenseEntity license;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private LicenseAllocationStatusEntity status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_employee_id")
    private EmployeeEntity employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    public LocalDate getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDate deletedDate) {
        this.deletedDate = deletedDate;
    }

    public LicenseEntity getLicense() {
        return license;
    }

    public void setLicense(LicenseEntity license) {
        this.license = license;
    }

    public LicenseAllocationStatusEntity getStatus() {
        return status;
    }

    public void setStatus(LicenseAllocationStatusEntity status) {
        this.status = status;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicenseAllocationEntity)) return false;
        LicenseAllocationEntity that = (LicenseAllocationEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LicenseAllocationEntity{" +
                "id=" + id +
                '}';
    }
}
