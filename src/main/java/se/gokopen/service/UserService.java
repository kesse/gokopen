package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.exception.UserNotFoundException;
import se.gokopen.persistence.entity.User;

public interface UserService {
     User getUserById(Integer id) throws UserNotFoundException;
     User getUser(String username) throws UserNotFoundException;
     void deleteUser(User user);
     void deleteUserByUsername(String username) throws UserNotFoundException;
     void saveUser(User user);
     List<User> getAllUsers();
     void deleteUserById(int id) throws UserNotFoundException;
}
