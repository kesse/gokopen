package se.gokopen.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.gokopen.persistence.entity.Patrol;
import se.gokopen.persistence.entity.Score;
import se.gokopen.persistence.entity.Station;

public interface ScoreRepository extends CrudRepository<Score, Integer> {


    List<Score> findAllByStation(Station station);

    List<Score> findAllByPatrol(Patrol patrol);

    Score findByPatrolAndStation(Patrol patrol, Station station);
}
