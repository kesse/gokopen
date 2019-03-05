package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;

public interface PatrolService {

    void savePatrol(PatrolEntity patrol) throws PatrolNotSavedException;

    void saveAllpatrols(List<PatrolEntity> patrols) throws PatrolNotSavedException;

    List<PatrolEntity> getAllPatrols();

    void deletePatrol(PatrolEntity patrol);

    void deletePatrolById(Integer id);

    PatrolEntity getPatrolById(Integer id) throws PatrolNotFoundException;

    List<PatrolEntity> getAllPatrolsByTrack(TrackEntity track);

    List<PatrolEntity> getAllPatrolsLeftOnStation(Integer stationId);

    List<PatrolEntity> getAllPatrolsSortedByStatus();

    List<PatrolEntity> getAllPatrolsSortedByTroop();

    List<PatrolEntity> getAllPatrolsSortedByNumberOfStations();

    List<PatrolEntity> getAllPatrolsSortedByTrack();

    List<PatrolEntity> getAllPatrolsSortedByScore();

    List<PatrolEntity> getAllActivePatrolsLeftOnStation(Integer stationId);

    List<PatrolEntity> getAllPatrolsCriteria();

    List<PatrolEntity> getAllPatrolsByStartStation(StationEntity station);
}

