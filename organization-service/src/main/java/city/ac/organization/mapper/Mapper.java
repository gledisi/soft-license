package city.ac.organization.mapper;

import city.ac.organization.dto.OrganizationResponse;
import city.ac.organization.entity.OrganizationEntity;

public class Mapper {

    public static OrganizationResponse toResponse(OrganizationEntity entity){
        OrganizationResponse response = new OrganizationResponse();
        response.id = entity.getId();
        response.name=entity.getName();
        response.contactName = entity.getContactName();
        response.contactEmail = entity.getContactEmail();;
        response.contactPhone = entity.getContactPhone();
        return response;
    }
}
