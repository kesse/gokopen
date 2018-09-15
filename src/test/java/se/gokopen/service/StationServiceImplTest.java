package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.StationNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StationServiceImplTest  extends SpringBootTestBase {

    @Autowired
    private StationService stationService;

    @Test(expected = StationNotFoundException.class)
    public void testGetStationByIdNotFound() throws StationNotFoundException {
        stationService.getStationById(123);
    }

    @Test
    public void testGetStationById() throws StationNotFoundException {
        StationEntity station = new StationEntity();
        station.setStationName("station");
        stationService.saveStation(station);

        Integer stationId = station.getStationId();

        // Get By id
        StationEntity stationLoaded = stationService.getStationById(stationId);
        assertThat(stationLoaded.getStationName(), is("station"));

        stationService.deleteStationById(stationId);
    }

    @Test
    public void testGetAllStations() {
        StationEntity station = new StationEntity();
        station.setStationName("station");
        stationService.saveStation(station);
        StationEntity station2 = new StationEntity();
        station2.setStationName("station2");
        stationService.saveStation(station2);

        Integer station2Id = station2.getStationId();

        // test
        List<StationEntity> stations = stationService.getAllStations();

        // Assert
        assertThat(stations.size(), is(2));

        // Delete both
        stationService.deleteStation(station);
        stationService.deleteStationById(station2Id);

        // Verify deleted
        List<StationEntity> stationsLeft = stationService.getAllStations();

        // Assert
        assertThat(stationsLeft.size(), is(0));
    }
}
