package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;
import se.gokopen.persistence.entity.Patrol;
import se.gokopen.persistence.entity.Station;
import se.gokopen.persistence.entity.Track;

public interface PatrolService {

	 void savePatrol(Patrol patrol) throws PatrolNotSavedException;
	 void saveAllpatrols(List<Patrol> patrols) throws PatrolNotSavedException;
	 List<Patrol> getAllPatrols();
	 void deletePatrol(Patrol patrol) throws PatrolNotFoundException;
	 void deletePatrolById(Integer id) throws PatrolNotFoundException;
	 Patrol getPatrolById(Integer id) throws PatrolNotFoundException;
	 List<Patrol> getAllPatrolsByTrack(Track track);
	 List<Patrol> getAllPatrolsLeftOnStation(Integer stationId);
	 List<Patrol> getAllPatrolsSortedByStatus();
	 List<Patrol> getAllPatrolsSortedByTroop();
	 List<Patrol> getAllPatrolsSortedByNumberOfStations();
     List<Patrol> getAllPatrolsSortedByTrack();
     List<Patrol> getAllPatrolsSortedByScore();
     List<Patrol> getAllActivePatrolsLeftOnStation(Integer stationId);
     List<Patrol> getActiveAndWaitingPatolsFromList(List<Patrol> patrols);
     List<Patrol> getAllPatrolsCriteria();
	 List<Patrol> getAllPatrolsByStartStation(Station station);
}

