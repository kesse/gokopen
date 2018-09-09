package se.gokopen.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findAllByOrderByUsername();
}
