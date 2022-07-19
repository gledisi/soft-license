package city.ac.licensing.entity;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "license_type", nullable = false)
    private String licenseType;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "max_number")
    private Integer maxNumber;
    @Column(name = "allocated")
    private Integer allocated;
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

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
