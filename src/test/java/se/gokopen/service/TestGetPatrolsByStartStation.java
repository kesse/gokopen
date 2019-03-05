package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;

public class TestGetPatrolsByStartStation extends SpringBootTestBase {

    @Autowired
    private PatrolService patrolService;

    @Autowired
    private StationService stationService;

    @Test
    public void shouldReturnPatrolsByStartStation() {
        System.out.println("start");
        List<StationEntity> stations = stationService.getAllStations();
        System.out.println("start");
        for (StationEntity station : stations) {
            System.out.println("Kontroll: " + station.getStationName());
            List<PatrolEntity> patrols = patrolService.getAllPatrolsByStartStation(station);
            System.out.println("antal patruller på kontrollen: " + patrols.size());
        }
    }
}
