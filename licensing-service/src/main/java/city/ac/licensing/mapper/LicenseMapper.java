package city.ac.licensing.mapper;

import city.ac.licensing.dto.LicenseResponse;
import city.ac.licensing.entity.LicenseEntity;

public class LicenseMapper {

    public static LicenseResponse toResponse(LicenseEntity entity){
        LicenseResponse response = new LicenseResponse();
        response.id=entity.getId();
        return response;
    }
}
