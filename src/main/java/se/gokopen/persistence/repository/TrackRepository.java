package se.gokopen.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.Track;

public interface TrackRepository extends CrudRepository<Track, Integer> {
}
