package se.gokopen.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;
import se.gokopen.model.Status;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.repository.PatrolRepository;

@Service
public class PatrolServiceImpl implements PatrolService {

    @Autowired
    private PatrolRepository patrolRepository;

    @Override
    @Transactional
    public void savePatrol(PatrolEntity patrol) throws PatrolNotSavedException {
        if(isNewPatrol(patrol)) {
            Date registered = new Date();
            patrol.setDateRegistered(registered);
        }
        patrolRepository.save(patrol);
    }

    private boolean isNewPatrol(PatrolEntity patrol) {
        if (patrol.getPatrolId()==null || patrol.getPatrolId()==0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrols() {
        return StreamSupport.stream(patrolRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePatrol(PatrolEntity patrol) throws PatrolNotFoundException {
        patrolRepository.delete(patrol);
    }

    @Override
    @Transactional
    public void deletePatrolById(Integer id) throws PatrolNotFoundException {
        patrolRepository.delete(id);
    }

    @Override
    @Transactional
    public PatrolEntity getPatrolById(Integer id) throws PatrolNotFoundException {
        PatrolEntity patrol = patrolRepository.findOne(id);

        if (patrol == null) {
            throw new PatrolNotFoundException("Hittar inte patrullen med id: " + id);
        }

        return patrol;
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsByTrack(TrackEntity track) {
        List<PatrolEntity> patrols = patrolRepository.findAllByTrack(track);
        Collections.sort(patrols); //sorterar efter högst poäng (standardsortering för patrolsklassen)
        return patrols;
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsLeftOnStation(Integer stationId) {
        List<PatrolEntity> allPatrols = getAllPatrols();

        Iterator<PatrolEntity> itt = allPatrols.iterator();
        while(itt.hasNext()){
            PatrolEntity patrol = (PatrolEntity) itt.next();
            Iterator<ScoreEntity> scores = patrol.getScores().iterator();
            while(scores.hasNext()){
                ScoreEntity score = scores.next();
                if(score.getStation().getStationId()==stationId){
                    itt.remove();
                    break;
                }
            }
        }

        return allPatrols;
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsSortedByStatus() {
        return patrolRepository.findAllByOrderByStatus();
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsSortedByTroop() {
        return patrolRepository.findAllByOrderByTroop();
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsSortedByNumberOfStations() {
        List<PatrolEntity> patrols = getAllPatrols();
        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        return patrols;
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsSortedByTrack() {
        return patrolRepository.findAllByOrderByTrack();
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsSortedByScore() {
        List<PatrolEntity> patrols = getAllPatrols();
        Collections.sort(patrols, Collections.reverseOrder(new BeanComparator("totalScore")));
        return patrols;
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllActivePatrolsLeftOnStation(Integer stationId) {
        List<PatrolEntity> patrols = getAllPatrolsLeftOnStation(stationId);

        return getActiveAndWaitingPatolsFromList(patrols);
    }

    public List<PatrolEntity> getActiveAndWaitingPatolsFromList(List<PatrolEntity> patrols) {
        List<PatrolEntity> onlyActivePatrols = new ArrayList<PatrolEntity>();
        for(PatrolEntity patrol:patrols){
            if(patrol.getStatus()!=null && (patrol.getStatus().equals(Status.REGISTERED) || patrol.getStatus().equals(Status.ACTIVE) || patrol.getStatus().equals(Status.FINISHED) )){
                onlyActivePatrols.add(patrol);
            }
        }
        return onlyActivePatrols;
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsCriteria() {
        return getAllPatrols();
    }

    @Override
    @Transactional
    public void saveAllpatrols(List<PatrolEntity> patrols) throws PatrolNotSavedException {
        for(PatrolEntity patrol:patrols){
            this.savePatrol(patrol);
        }

    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsByStartStation(StationEntity station) {
        return patrolRepository.findAllByStartStation(station);
    }
}
