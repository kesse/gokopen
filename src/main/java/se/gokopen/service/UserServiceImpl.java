package se.gokopen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.UserEntity;
import se.gokopen.persistence.exception.UserNotFoundException;
import se.gokopen.persistence.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) throws UserNotFoundException {
        userRepository.delete(id);
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUsers() {
        return userRepository.findAllByOrderByUsername();
    }

    @Override
    @Transactional
    public UserEntity getUserById(Integer id) throws UserNotFoundException {
        UserEntity user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("Hittar inte användaren med id " + id);
        }

        return user;
    }

}
