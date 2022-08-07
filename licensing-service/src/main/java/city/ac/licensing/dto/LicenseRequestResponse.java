package city.ac.licensing.dto;

import java.time.LocalDate;

public class LicenseRequestResponse {

    public Long id;
    public LocalDate requestDate;
    public LocalDate approvedDate;
    public String reason;
    public LicenseResponse license;
    public String status;
    public UserResponse employee;
    public UserResponse approvedBy;
}
