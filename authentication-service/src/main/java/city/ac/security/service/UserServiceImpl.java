package city.ac.security.service;

import city.ac.security.dto.User;
import city.ac.security.dto.UserCreate;
import city.ac.security.entity.RoleEntity;
import city.ac.security.entity.UserEntity;
import city.ac.security.exceptions.NotFound;
import city.ac.security.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
@Transactional
class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final Long ADMIN_ID = 1L;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserEntity findWithUsername(String username) {
        LOGGER.debug("Finding user: {}", username);
        String query = "SELECT user FROM UserEntity user WHERE user.username=?1 AND user.deleted=:deleted ";
        try {
            return entityManager.createQuery(query, UserEntity.class).setParameter(1, username)
                    .setParameter("deleted", Boolean.FALSE).getSingleResult();
        } catch (NoResultException nre) {
            throw new EntityNotFoundException(NotFound.USER.getMessage());

        }
    }

    @Override
    public User add(UserCreate user) {
        user.password = passwordEncoder.encode(user.password);
        UserEntity entity = UserMapper.toEntity(user);
        entity.setCreatedBy(1L);
        entity.setRole(findRole(1L));
        entityManager.persist(entity);
        return UserMapper.toDTO(entity);
    }

    private RoleEntity findRole(Long id) {
        LOGGER.debug("Finding role: {}", id);
        try {
            return entityManager.createQuery("SELECT p FROM RoleEntity p WHERE p.id=?1", RoleEntity.class)
                    .setParameter(1, id).getSingleResult();
        } catch (NoResultException nre) {
            LOGGER.debug("No role found with id: {}", id);
            throw new EntityNotFoundException(NotFound.ROLE.getMessage());

        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT user FROM UserEntity user WHERE user.deleted=false ";
        List<UserEntity> result = entityManager.createQuery(query, UserEntity.class)
                .getResultList();
        return UserMapper.toDTO(result);
    }

    @Override
    public User getById(Long id) {
        String query = "SELECT user FROM UserEntity user WHERE user.deleted=false and user.id=?1";
        UserEntity result = entityManager.createQuery(query, UserEntity.class)
                .setParameter(1,id)
                .getSingleResult();
        return UserMapper.toDTO(result);
    }


}
