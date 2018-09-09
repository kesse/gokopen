package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.Station;
import se.gokopen.persistence.exception.StationNotFoundException;

public interface StationService {
	void saveStation(Station station);

	List<Station> getAllStations();

	void deleteStation(Station station);

	void deleteStationById(Integer id);

	Station getStationById(Integer id) throws StationNotFoundException;
}
