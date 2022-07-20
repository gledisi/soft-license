package city.ac.security.mapper;


import city.ac.security.dto.Role;
import city.ac.security.dto.User;
import city.ac.security.dto.UserCreate;
import city.ac.security.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

	private UserMapper() {
		throw new UnsupportedOperationException("Constructor nto allowed!");
	}

	public static User toDTO(UserEntity entity) {
		User toReturn = new User();
		toReturn.id=        entity.getId();
		toReturn.firstName= entity.getFirstName();
		toReturn.lastName=  entity.getLastName();
		toReturn.username=  entity.getUsername();
		toReturn.email=     entity.getEmail();
		toReturn.valid=     entity.getDeleted();
		toReturn.role= new Role(entity.getRole().getId(),entity.getRole().getType().getValue());
		return toReturn;

	}

	public static List<User> toDTO(List<UserEntity> entities) {
		return entities.stream().map(UserMapper::toDTO).collect(Collectors.toList());
	}

	public static UserEntity toEntity(UserCreate user) {
		UserEntity toReturn = new UserEntity();
		toReturn.setFirstName(user.firstName);
		toReturn.setLastName(user.lastName);
		toReturn.setEmail(user.email);
		toReturn.setUsername(user.username);
		toReturn.setPassword(user.password);
		toReturn.setDeleted(Boolean.FALSE);
		toReturn.setCreatedOn(LocalDateTime.now());
		toReturn.setCreatedBy(1L);
		return toReturn;
	}
}
