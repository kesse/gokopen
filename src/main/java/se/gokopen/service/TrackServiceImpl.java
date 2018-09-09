package se.gokopen.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.exception.TrackNotFoundException;
import se.gokopen.persistence.entity.Track;
import se.gokopen.persistence.repository.TrackRepository;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository trackRepository;
	
	@Override
	@Transactional
	public void saveTrack(Track track) {
		trackRepository.save(track);
	}

	@Override
	@Transactional
	public List<Track> getAllTracks() {

		return StreamSupport.stream(trackRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteTrack(Track track) {
		trackRepository.delete(track);
	}

	@Override
	@Transactional
	public void deleteTrackById(Integer id) {
		trackRepository.delete(id);
	}

	@Override
	@Transactional
	public Track getTrackById(Integer id) throws TrackNotFoundException {

		Track track = trackRepository.findOne(id);

		if (track == null) {
			throw new TrackNotFoundException("Hittar inte spåret med id: " + id);
		}

		return track;
	}

}
