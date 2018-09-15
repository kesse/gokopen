package se.gokopen.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.StationNotFoundException;
import se.gokopen.persistence.repository.StationRepository;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    @Transactional
    public void saveStation(StationEntity station) {
        stationRepository.save(station);
    }

    @Override
    @Transactional
    public List<StationEntity> getAllStations() {

        return StreamSupport.stream(stationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteStation(StationEntity station) {
        stationRepository.delete(station);
    }

    @Override
    @Transactional
    public void deleteStationById(Integer id) {
        stationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StationEntity getStationById(Integer id) throws StationNotFoundException {

        Optional<StationEntity> station = stationRepository.findById(id);

        if (!station.isPresent()) {
            throw new StationNotFoundException("Hittar inte kontrollen med id: " + id);
        }

        return station.get();
    }

}
