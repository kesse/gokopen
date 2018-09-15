package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.exception.ScoreNotFoundException;
import se.gokopen.persistence.exception.ScoreNotSavedException;


public interface ScoreService {
    void saveScore(ScoreEntity score) throws ScoreNotSavedException;

    List<ScoreEntity> getScoreByPatrolId(Integer id);

    List<ScoreEntity> getScoreOnStation(Integer stationId);

    void deleteScore(ScoreEntity score) throws ScoreNotFoundException;

    void deleteScoreById(Integer id) throws ScoreNotFoundException;

    ScoreEntity getScoreById(Integer id) throws ScoreNotFoundException;
}
