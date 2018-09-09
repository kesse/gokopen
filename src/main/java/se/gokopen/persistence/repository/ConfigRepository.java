package se.gokopen.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.Config;

public interface ConfigRepository extends CrudRepository<Config, Integer> {
}
