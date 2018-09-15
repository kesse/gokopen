package se.gokopen.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    List<UserEntity> findAllByOrderByUsername();
}
