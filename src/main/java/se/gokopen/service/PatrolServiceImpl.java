package se.gokopen.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.model.Status;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;
import se.gokopen.persistence.repository.PatrolRepository;

@Service
public class PatrolServiceImpl implements PatrolService {

    private static List<Status> activeStatuses = Arrays.asList(Status.REGISTERED, Status.ACTIVE, Status.FINISHED);

    @Autowired
    private PatrolRepository patrolRepository;

    @Override
    @Transactional
    public void savePatrol(PatrolEntity patrol) throws PatrolNotSavedException {

        try {
            patrolRepository.save(patrol);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new PatrolNotSavedException(e.getMessage());
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
    public void deletePatrol(PatrolEntity patrol) {
        patrolRepository.delete(patrol);
    }

    @Override
    @Transactional
    public void deletePatrolById(Integer id) {
        patrolRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PatrolEntity getPatrolById(Integer id) throws PatrolNotFoundException {
        Optional<PatrolEntity> patrol = patrolRepository.findById(id);

        if (!patrol.isPresent()) {
            throw new PatrolNotFoundException("Hittar inte patrullen med id: " + id);
        }

        return patrol.get();
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

        return allPatrols.stream()
                .filter(p -> p.getScores().stream()
                            .noneMatch(s -> s.getStation().getStationId().equals(stationId))
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllActivePatrolsLeftOnStation(Integer stationId) {
        List<PatrolEntity> patrols = getAllPatrolsLeftOnStation(stationId);

        return patrols.stream()
                .filter(p -> p.getStatus() != null && activeStatuses.contains(p.getStatus()))
                .collect(Collectors.toList());
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
    public List<PatrolEntity> getAllPatrolsCriteria() {
        return getAllPatrols();
    }

    @Override
    @Transactional
    public void saveAllpatrols(List<PatrolEntity> patrols) throws PatrolNotSavedException {
        try {
            patrolRepository.saveAll(patrols);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new PatrolNotSavedException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<PatrolEntity> getAllPatrolsByStartStation(StationEntity station) {
        return patrolRepository.findAllByStartStation(station);
    }
}
