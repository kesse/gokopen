package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.TrackEntity;
import se.gokopen.persistence.exception.TrackNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackServiceImplTest extends SpringBootTestBase {

    @Autowired
    private TrackService trackService;


    @Test(expected = TrackNotFoundException.class)
    public void testGetTrackNotFound() throws TrackNotFoundException {
        trackService.getTrackById(123);
    }


    @Test
    public void testGetTrack() throws TrackNotFoundException {
        TrackEntity track2 = new TrackEntity();
        track2.setTrackName("Track 2");
        trackService.saveTrack(track2);

        Integer track2Id = track2.getTrackId();

        // Get By id
        TrackEntity loadedTrack2 = trackService.getTrackById(track2Id);
        assertThat(loadedTrack2.getTrackName(), is("Track 2"));

        trackService.deleteTrack(track2);
    }


    @Test
    public void testGetAllTracks() {
        TrackEntity track = new TrackEntity();
        trackService.saveTrack(track);
        TrackEntity track2 = new TrackEntity();
        trackService.saveTrack(track2);

        Integer track2Id = track2.getTrackId();

        // test
        List<TrackEntity> tracks = trackService.getAllTracks();

        // Assert
        assertThat(tracks.size(), is(2));

        // Delete both
        trackService.deleteTrack(track);
        trackService.deleteTrackById(track2Id);

        // Verify deleted
        List<TrackEntity> tracksLeft = trackService.getAllTracks();

        // Assert
        assertThat(tracksLeft.size(), is(0));
    }
}
