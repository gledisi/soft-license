package city.ac.organization.mapper;

import city.ac.organization.dto.EmployeeResponse;
import city.ac.organization.dto.EmployeeStatus;
import city.ac.organization.dto.UserResponse;
import city.ac.organization.entity.EmployeeEntity;
import city.ac.organization.entity.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EmployeeMapper {

    public  static EmployeeResponse toResponse(EmployeeEntity entity){
        EmployeeResponse response = new EmployeeResponse();
        response.id = entity.getId();
        response.status = new EmployeeStatus(entity.getStatus().getId(),entity.getStatus().getName());
        response.user = toUserResponse(entity.getUser());
        return response;
    }

    public static UserResponse toUserResponse(UserEntity userEntity) {
        UserResponse toReturn = new UserResponse();
        toReturn.id = userEntity.getId();
        toReturn.firstName= userEntity.getFirstName();
        toReturn.lastName=  userEntity.getLastName();
        toReturn.username=  userEntity.getUsername();
        toReturn.email=     userEntity.getEmail();
        toReturn.valid=     userEntity.getDeleted();
        return toReturn;
    }

    public  static List<EmployeeResponse> toResponse(Iterable<EmployeeEntity> entity){
        return StreamSupport.stream(entity.spliterator(), false).map(EmployeeMapper::toResponse).collect(Collectors.toList());
    }
}
