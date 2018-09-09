package se.gokopen.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.entity.Patrol;
import se.gokopen.persistence.entity.Score;
import se.gokopen.persistence.entity.Station;
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
    public void saveScore(Score score) throws ScoreNotSavedException {
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
    public List<Score> getAllScores() {

        return StreamSupport.stream(scoreRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Score> getScoreByPatrolId(Integer id) {

        Patrol patrol = null;
        try {
            patrol = patrolService.getPatrolById(id);
        } catch (PatrolNotFoundException e) {
            e.printStackTrace();
        }

        return scoreRepository.findAllByPatrol(patrol);
    }

    @Override
    @Transactional
    public void deleteScore(Score score) {
        scoreRepository.delete(score);
    }

    @Override
    @Transactional
    public void deleteScoreById(Integer id) {
        scoreRepository.delete(id);
    }

    @Override
    @Transactional
    public Score getScoreById(Integer id) throws ScoreNotFoundException {
        Score score = scoreRepository.findOne(id);

        if (score == null) {
            throw new ScoreNotFoundException("Hittar inte poäng med id: " + id);
        }

        return score;
    }
    
    private boolean hasScoreBeenSavedBefore(Score score){
        Patrol patrol = score.getPatrol();
        Station station = score.getStation();

        Score foundScore = scoreRepository.findByPatrolAndStation(patrol, station);

        if (foundScore == null) {
            System.out.println("Hittar inget poäng, ok att spara");
            return false;
        }
        
        return true;
    }

    private boolean isScoreInEditMode(Score score){
        if (score.getScoreId()==null || score.getScoreId()==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Score> getScoreOnStation(Integer stationId) {
        Station station = stationRepository.findOne(stationId);

        return scoreRepository.findAllByStation(station);
    }
}
