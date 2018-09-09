package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.gokopen.Application;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGetPatrolsByStartStation {
	
	@Autowired
	private PatrolService patrolService;

	@Autowired
	private StationService stationService;

	@Test
	public void shouldReturnPatrolsByStartStation() {
		System.out.println("start");
		List<StationEntity> stations = stationService.getAllStations();
		System.out.println("start");
		for(StationEntity station:stations) {
			System.out.println("Kontroll: " + station.getStationName());
			List<PatrolEntity> patrols = patrolService.getAllPatrolsByStartStation(station);
			System.out.println("antal patruller på kontrollen: " + patrols.size());
		}
	}
}
