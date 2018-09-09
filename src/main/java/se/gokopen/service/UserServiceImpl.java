package se.gokopen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.User;
import se.gokopen.persistence.exception.UserNotFoundException;
import se.gokopen.persistence.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional
    public User getUser(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("Hittar inte användaren med användarnamnet " + username);
        }

        return user;
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        User delUser = getUser(username);
        deleteUser(delUser);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByUsername();
    }

    @Override
    @Transactional
    public User getUserById(Integer id) throws UserNotFoundException {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("Hittar inte användaren med id "+ id);
        }

        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(int id) throws UserNotFoundException {
        userRepository.delete(id);
    }
}
