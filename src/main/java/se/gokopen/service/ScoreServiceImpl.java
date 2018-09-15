package se.gokopen.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.ScoreNotFoundException;
import se.gokopen.persistence.exception.ScoreNotSavedException;
import se.gokopen.persistence.repository.ScoreRepository;
import se.gokopen.persistence.repository.StationRepository;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PatrolService patrolService;

    @Override
    @Transactional
    public void saveScore(ScoreEntity score) throws ScoreNotSavedException {
        if (isScoreInEditMode(score) || !hasScoreBeenSavedBefore(score)) {
            Date saved = new Date();
            score.setLastSaved(saved);
            scoreRepository.save(score);
        } else {
            throw new ScoreNotSavedException("Det finns redan poäng registrerat för denna patrull på denna kontroll.");
        }
    }

    @Override
    @Transactional
    //TODO ta bort=
    public List<ScoreEntity> getAllScores() {

        return StreamSupport.stream(scoreRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ScoreEntity> getScoreByPatrolId(Integer id) {

        PatrolEntity patrol = null;
        try {
            patrol = patrolService.getPatrolById(id);
        } catch (PatrolNotFoundException e) {
            e.printStackTrace();
        }

        return scoreRepository.findAllByPatrol(patrol);
    }

    @Override
    @Transactional
    public void deleteScore(ScoreEntity score) {
        scoreRepository.delete(score);
    }

    @Override
    @Transactional
    public void deleteScoreById(Integer id) {
        scoreRepository.delete(id);
    }

    @Override
    @Transactional
    public ScoreEntity getScoreById(Integer id) throws ScoreNotFoundException {
        ScoreEntity score = scoreRepository.findOne(id);

        if (score == null) {
            throw new ScoreNotFoundException("Hittar inte poäng med id: " + id);
        }

        return score;
    }

    private boolean hasScoreBeenSavedBefore(ScoreEntity score) {
        PatrolEntity patrol = score.getPatrol();
        StationEntity station = score.getStation();

        ScoreEntity foundScore = scoreRepository.findByPatrolAndStation(patrol, station);

        if (foundScore == null) {
            System.out.println("Hittar inget poäng, ok att spara");
            return false;
        }

        return true;
    }

    private boolean isScoreInEditMode(ScoreEntity score) {
        if (score.getScoreId() == null || score.getScoreId() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<ScoreEntity> getScoreOnStation(Integer stationId) {
        StationEntity station = stationRepository.findOne(stationId);

        return scoreRepository.findAllByStation(station);
    }
}
