package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.exception.ScoreNotFoundException;
import se.gokopen.persistence.exception.ScoreNotSavedException;
import se.gokopen.persistence.entity.Score;


public interface ScoreService {
	public void saveScore(Score score) throws ScoreNotSavedException;
	public List<Score> getAllScores();
	public List<Score> getScoreByPatrolId(Integer id);
	public List<Score> getScoreOnStation(Integer stationId);
	public void deleteScore(Score score) throws ScoreNotFoundException;
	public void deleteScoreById(Integer id) throws ScoreNotFoundException;
	public Score getScoreById(Integer id) throws ScoreNotFoundException;
}
