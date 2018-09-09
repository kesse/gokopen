package se.gokopen.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.Patrol;
import se.gokopen.persistence.entity.Station;
import se.gokopen.persistence.entity.Track;

public interface PatrolRepository  extends CrudRepository<Patrol, Integer> {

    List<Patrol> findAllByOrderByTrack();

    List<Patrol> findAllByOrderByStatus();

    List<Patrol> findAllByOrderByTroop();

    List<Patrol> findAllByTrack(Track track);

    List<Patrol> findAllByStartStation(Station station);
}
