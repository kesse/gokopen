package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.StationNotFoundException;

public interface StationService {
    void saveStation(StationEntity station);

    List<StationEntity> getAllStations();

    void deleteStation(StationEntity station);

    void deleteStationById(Integer id);

    StationEntity getStationById(Integer id) throws StationNotFoundException;
}
