package se.gokopen.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.StationEntity;

public interface StationRepository extends CrudRepository<StationEntity, Integer> {
}
