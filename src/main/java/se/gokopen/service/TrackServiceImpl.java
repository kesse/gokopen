package se.gokopen.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.exception.TrackNotFoundException;
import se.gokopen.persistence.repository.TrackRepository;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    @Transactional
    public void saveTrack(TrackEntity track) {
        trackRepository.save(track);
    }

    @Override
    @Transactional
    public List<TrackEntity> getAllTracks() {

        return StreamSupport.stream(trackRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteTrack(TrackEntity track) {
        trackRepository.delete(track);
    }

    @Override
    @Transactional
    public void deleteTrackById(Integer id) {
        trackRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TrackEntity getTrackById(Integer id) throws TrackNotFoundException {

        Optional<TrackEntity> track = trackRepository.findById(id);

        if (!track.isPresent()) {
            throw new TrackNotFoundException("Hittar inte spåret med id: " + id);
        }

        return track.get();
    }

}
