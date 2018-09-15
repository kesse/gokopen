package se.gokopen.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.entity.TrackEntity;

public interface PatrolRepository extends CrudRepository<PatrolEntity, Integer> {

    List<PatrolEntity> findAllByOrderByTrack();

    List<PatrolEntity> findAllByOrderByStatus();

    List<PatrolEntity> findAllByOrderByTroop();

    List<PatrolEntity> findAllByTrack(TrackEntity track);

    List<PatrolEntity> findAllByStartStation(StationEntity station);
}
