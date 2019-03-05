package se.gokopen.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.ConfigEntity;

public interface ConfigRepository extends CrudRepository<ConfigEntity, Integer> {
}
