package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.exception.TrackNotFoundException;

public interface TrackService {
	void saveTrack(TrackEntity track);

	List<TrackEntity> getAllTracks();

	void deleteTrack(TrackEntity track);

	void deleteTrackById(Integer id);

	TrackEntity getTrackById(Integer id) throws TrackNotFoundException;
}
