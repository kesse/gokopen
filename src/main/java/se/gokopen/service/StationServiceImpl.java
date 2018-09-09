package se.gokopen.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.exception.StationNotFoundException;
import se.gokopen.persistence.entity.Station;
import se.gokopen.persistence.repository.StationRepository;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationRepository stationRepository;

	@Override
	@Transactional
	public void saveStation(Station station) {
		stationRepository.save(station);
	}

	@Override
	@Transactional
	public List<Station> getAllStations() {

		return StreamSupport.stream(stationRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteStation(Station station) {
		stationRepository.delete(station);
	}

	@Override
	@Transactional
	public void deleteStationById(Integer id) {
		stationRepository.delete(id);
	}

	@Override
	@Transactional
	public Station getStationById(Integer id) throws StationNotFoundException {

		Station station = stationRepository.findOne(id);

		if (station == null) {
			throw new StationNotFoundException("Hittar inte kontrollen med id: " + id);
		}

		return station;
	}

}
