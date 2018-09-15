package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.UserEntity;
import se.gokopen.persistence.exception.UserNotFoundException;

public interface UserService {
    UserEntity getUserById(Integer id) throws UserNotFoundException;

    void deleteUser(UserEntity user);

    void saveUser(UserEntity user);

    List<UserEntity> getAllUsers();

    void deleteUserById(int id) throws UserNotFoundException;
}
