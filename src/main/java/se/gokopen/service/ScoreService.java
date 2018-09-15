package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.exception.ScoreNotFoundException;
import se.gokopen.persistence.exception.ScoreNotSavedException;


public interface ScoreService {
    public void saveScore(ScoreEntity score) throws ScoreNotSavedException;

    public List<ScoreEntity> getAllScores();

    public List<ScoreEntity> getScoreByPatrolId(Integer id);

    public List<ScoreEntity> getScoreOnStation(Integer stationId);

    public void deleteScore(ScoreEntity score) throws ScoreNotFoundException;

    public void deleteScoreById(Integer id) throws ScoreNotFoundException;

    public ScoreEntity getScoreById(Integer id) throws ScoreNotFoundException;
}
