package city.ac.licensing.mapper;

import city.ac.licensing.dto.LicenseRequest;
import city.ac.licensing.dto.LicenseRequestResponse;
import city.ac.licensing.dto.LicenseResponse;
import city.ac.licensing.dto.UserResponse;
import city.ac.licensing.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LicenseMapper {

    public static LicenseResponse toResponse(LicenseEntity entity){
        LicenseResponse response = new LicenseResponse();
        response.id=entity.getId();
        response.allocated= entity.getAllocated();
        response.annualCostPerLicense = entity.getAnnualCostPerLicense();
        response.expiryDate = entity.getExpiryDate();
        response.purchasedDate = entity.getPurchasedDate();
        response.licenseType = entity.getLicenseType().getName();
        response.maxNumber = entity.getMaxNumber();
        response.status  = entity.getStatus().getName();

        return response;
    }

    public static EmployeeLicenseRequestEntity toRequestEntity(LicenseRequest request){
        EmployeeLicenseRequestEntity entity = new EmployeeLicenseRequestEntity();
        entity.setEmployee(new EmployeeEntity(request.employeeId));
        entity.setLicense(new LicenseEntity(request.licenseId));
        entity.setReason(request.reason);
        entity.setRequestDate(LocalDate.now());
        entity.setStatus(new LicenseRequestStatusEntity(1L));
        return entity;
    }

    public static LicenseRequestResponse toResponse(EmployeeLicenseRequestEntity entity){
        LicenseRequestResponse response = new LicenseRequestResponse();
        response.id = entity.getId();
        response.license = LicenseMapper.toResponse(entity.getLicense());
        response.reason = entity.getReason();
        response.requestDate = entity.getRequestDate();
        response.approvedDate = entity.getApprovedDate();
        response.status = entity.getStatus().getName();
        response.employee = toEmployee(entity.getEmployee());
        response.approvedBy = toEmployee(entity.getEmployee());
        return response;
    }
    public static UserResponse toEmployee(EmployeeEntity entity) {
        UserEntity userEntity = entity.getUser();
        UserResponse toReturn = new UserResponse();
        toReturn.employeeId= entity.getId();
        toReturn.userId = userEntity.getId();
        toReturn.firstName= userEntity.getFirstName();
        toReturn.lastName=  userEntity.getLastName();
        toReturn.username=  userEntity.getUsername();
        toReturn.email=     userEntity.getEmail();
        toReturn.valid=     userEntity.getDeleted();
        return toReturn;

    }
    public static List<LicenseRequestResponse> toResponse(List<EmployeeLicenseRequestEntity> entity){
        return entity.stream().map(LicenseMapper::toResponse).collect(Collectors.toList());
    }

}
