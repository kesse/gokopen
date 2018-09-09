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
import se.gokopen.persistence.entity.Patrol;
import se.gokopen.persistence.entity.Score;
import se.gokopen.persistence.entity.Station;
import se.gokopen.persistence.entity.Track;
import se.gokopen.persistence.repository.PatrolRepository;

@Service
public class PatrolServiceImpl implements PatrolService {

    @Autowired
    private PatrolRepository patrolRepository;

    @Override
    @Transactional
    public void savePatrol(Patrol patrol) throws PatrolNotSavedException {
        if(isNewPatrol(patrol)) {
            Date registered = new Date();
            patrol.setDateRegistered(registered);
        }
        patrolRepository.save(patrol);
    }

    private boolean isNewPatrol(Patrol patrol) {
        if (patrol.getPatrolId()==null || patrol.getPatrolId()==0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrols() {
        return StreamSupport.stream(patrolRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePatrol(Patrol patrol) throws PatrolNotFoundException {
        patrolRepository.delete(patrol);
    }

    @Override
    @Transactional
    public void deletePatrolById(Integer id) throws PatrolNotFoundException {
        patrolRepository.delete(id);
    }

    @Override
    @Transactional
    public Patrol getPatrolById(Integer id) throws PatrolNotFoundException {
        Patrol patrol = patrolRepository.findOne(id);

        if (patrol == null) {
            throw new PatrolNotFoundException("Hittar inte patrullen med id: " + id);
        }

        return patrol;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsByTrack(Track track) {
        List<Patrol> patrols = patrolRepository.findAllByTrack(track);
        Collections.sort(patrols); //sorterar efter högst poäng (standardsortering för patrolsklassen)
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsLeftOnStation(Integer stationId) {
        List<Patrol> allPatrols = getAllPatrols();

        Iterator<Patrol> itt = allPatrols.iterator();
        while(itt.hasNext()){
            Patrol patrol = (Patrol) itt.next();
            Iterator<Score> scores = patrol.getScores().iterator();
            while(scores.hasNext()){
                Score score = scores.next();
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
    public List<Patrol> getAllPatrolsSortedByStatus() {
        return patrolRepository.findAllByOrderByStatus();
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByTroop() {
        return patrolRepository.findAllByOrderByTroop();
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByNumberOfStations() {
        List<Patrol> patrols = getAllPatrols();
        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByTrack() {
        return patrolRepository.findAllByOrderByTrack();
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsSortedByScore() {
        List<Patrol> patrols = getAllPatrols();
        Collections.sort(patrols, Collections.reverseOrder(new BeanComparator("totalScore")));
        return patrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllActivePatrolsLeftOnStation(Integer stationId) {
        List<Patrol> patrols = getAllPatrolsLeftOnStation(stationId);

        return getActiveAndWaitingPatolsFromList(patrols);
    }

    public List<Patrol> getActiveAndWaitingPatolsFromList(List<Patrol> patrols) {
        List<Patrol> onlyActivePatrols = new ArrayList<Patrol>();
        for(Patrol patrol:patrols){
            if(patrol.getStatus()!=null && (patrol.getStatus().equals(Status.REGISTERED) || patrol.getStatus().equals(Status.ACTIVE) || patrol.getStatus().equals(Status.FINISHED) )){
                onlyActivePatrols.add(patrol);
            }
        }
        return onlyActivePatrols;
    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsCriteria() {
        return getAllPatrols();
    }

    @Override
    @Transactional
    public void saveAllpatrols(List<Patrol> patrols) throws PatrolNotSavedException {
        for(Patrol patrol:patrols){
            this.savePatrol(patrol);
        }

    }

    @Override
    @Transactional
    public List<Patrol> getAllPatrolsByStartStation(Station station) {
        return patrolRepository.findAllByStartStation(station);
    }
}
