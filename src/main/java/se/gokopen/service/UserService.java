package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.exception.UserNotFoundException;
import se.gokopen.persistence.entity.UserEntity;

public interface UserService {
     UserEntity getUserById(Integer id) throws UserNotFoundException;
     UserEntity getUser(String username) throws UserNotFoundException;
     void deleteUser(UserEntity user);
     void deleteUserByUsername(String username) throws UserNotFoundException;
     void saveUser(UserEntity user);
     List<UserEntity> getAllUsers();
     void deleteUserById(int id) throws UserNotFoundException;
}
