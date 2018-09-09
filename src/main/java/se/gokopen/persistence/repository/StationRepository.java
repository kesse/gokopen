package se.gokopen.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.Station;

public interface StationRepository extends CrudRepository<Station, Integer> {
}
