package city.ac.security.service;


import city.ac.security.dto.User;
import city.ac.security.dto.UserCreate;
import city.ac.security.entity.UserEntity;

import java.util.List;


public interface UserService {

    UserEntity findWithUsername(String username);

    public User add(UserCreate user);

    List<User> getAll();
    User getById(Long id);
}
