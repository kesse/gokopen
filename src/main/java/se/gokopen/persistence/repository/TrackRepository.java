package se.gokopen.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.TrackEntity;

public interface TrackRepository extends CrudRepository<TrackEntity, Integer> {
}
