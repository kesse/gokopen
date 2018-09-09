package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.Track;
import se.gokopen.persistence.exception.TrackNotFoundException;

public interface TrackService {
	void saveTrack(Track track);

	List<Track> getAllTracks();

	void deleteTrack(Track track);

	void deleteTrackById(Integer id);

	Track getTrackById(Integer id) throws TrackNotFoundException;
}
