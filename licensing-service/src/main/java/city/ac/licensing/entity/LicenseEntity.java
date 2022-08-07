package city.ac.licensing.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "licenses", schema = "public")
public class LicenseEntity implements Serializable {
    private static final long serialVersionUID = 4743502137550973944L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;
    @Column(name = "max_number")
    private Integer maxNumber;
    @Column(name = "allocated")
    private Integer allocated;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private LicenseStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "license_type_id")
    private LicenseTypeEntity licenseType;

    public LicenseEntity(Long id) {
        this.id = id;
    }

    public LicenseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public LicenseTypeEntity getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseTypeEntity licenseType) {
        this.licenseType = licenseType;
    }


    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Integer getAllocated() {
        return allocated;
    }

    public void setAllocated(Integer allocated) {
        this.allocated = allocated;
    }


    public LicenseStatusEntity getStatus() {
        return status;
    }

    public void setStatus(LicenseStatusEntity status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicenseEntity)) return false;
        LicenseEntity that = (LicenseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
