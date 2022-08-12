package city.ac.licensing.dto;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LicenseResponse {
    public Long id;
    public Integer maxNumber;
    public Integer allocated;
    public LocalDate purchasedDate;
    public LocalDate expiryDate;
    public BigDecimal annualCostPerLicense;
    public String licenseType;
    public String status;
    public OrganizationResponse organization;
}
