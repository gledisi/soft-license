package city.ac.licensing.mapper;


import city.ac.licensing.dto.SoftwareResponse;
import city.ac.licensing.entity.SoftwareEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SoftwareMapper {
    public static SoftwareResponse toResponse(SoftwareEntity entity) {
        SoftwareResponse response = new SoftwareResponse();
        response.id = entity.getId();
        response.name = entity.getName();
        response.description = entity.getDescription();
        response.logo = entity.getLogo();
        return response;
    }

    public static List<SoftwareResponse> toResponse(List<SoftwareEntity> entity) {
        return entity.stream().map(SoftwareMapper::toResponse).collect(Collectors.toList());
    }

}
