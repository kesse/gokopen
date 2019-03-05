package se.gokopen.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;

public interface ScoreRepository extends CrudRepository<ScoreEntity, Integer> {


    List<ScoreEntity> findAllByStation(StationEntity station);

    List<ScoreEntity> findAllByPatrol(PatrolEntity patrol);

    ScoreEntity findByPatrolAndStation(PatrolEntity patrol, StationEntity station);
}
