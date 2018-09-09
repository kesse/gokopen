package se.gokopen.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;


public class TestDistributePatrols {
	private static final String KONTROLL1_NAME = "Kontroll 1";
	private static final String KONTROLL2_NAME = "Kontroll 2";
	private static final String KONTROLL3_NAME = "Kontroll 3";
	List<PatrolEntity> patrols = new ArrayList<PatrolEntity>();
	PatrolEntity patrol1;
	PatrolEntity patrol2;
	PatrolEntity patrol3;
	PatrolEntity patrol4;
	PatrolEntity patrol5;
	
	List<StationEntity> stations = new ArrayList<StationEntity>();
	StationEntity station1;
	StationEntity station2;
	StationEntity station3;
	StationEntity station4;
	
	@Before
	public void init(){
		patrol1 = new PatrolEntity();
		patrol1.setPatrolName("Patrull 1");
		patrol2 = new PatrolEntity();
		patrol2.setPatrolName("Patrull 2");
		patrol3 = new PatrolEntity();
		patrol3.setPatrolName("Patrull 3");
		patrol4 = new PatrolEntity();
		patrol4.setPatrolName("Patrull 4");
		
		patrols.add(patrol1);
		patrols.add(patrol2);
		patrols.add(patrol3);
		patrols.add(patrol4);
		
		
	}
	
	@Test
	public void saveStartStation(){
	    station1 = new StationEntity();
        station1.setStationName(KONTROLL1_NAME);
        station1.setStationNumber(1);
		patrol1.setStartStation(station1);
		assertEquals(patrol1.getStartStation().getStationName(),KONTROLL1_NAME);
	}
	
	@Test
	public void shouldDistributePatrolsOnStationsEvenly(){
	    station1 = new StationEntity();
        station1.setStationName(KONTROLL1_NAME);
        station1.setStationNumber(1);
        
        station2 = new StationEntity();
        station2.setStationName(KONTROLL2_NAME);
        station2.setStationNumber(2);
        
        stations.add(station1);
        stations.add(station2);
		Distribute.patrolsOnStations(patrols, stations);
		assertEquals(patrol1.getStartStation().getStationName(),KONTROLL1_NAME);
		assertEquals(patrol2.getStartStation().getStationName(),KONTROLL2_NAME);
		assertEquals(patrol3.getStartStation().getStationName(),KONTROLL1_NAME);
		assertEquals(patrol4.getStartStation().getStationName(),KONTROLL2_NAME);
	}

	@Test
	public void shouldDistributePatrolsOnStationsOnOddNumberOfStations(){
	    station1 = new StationEntity();
        station1.setStationName(KONTROLL1_NAME);
        station1.setStationNumber(1);
        
        station2 = new StationEntity();
        station2.setStationName(KONTROLL2_NAME);
        station2.setStationNumber(2);
        
        stations.add(station1);
        stations.add(station2);
		station3 = new StationEntity();
		station3.setStationName(KONTROLL3_NAME);
		station3.setStationNumber(3);
		stations.add(station3);
		Distribute.patrolsOnStations(patrols, stations);
		assertEquals(patrol1.getStartStation().getStationName(),KONTROLL1_NAME);
		assertEquals(patrol2.getStartStation().getStationName(),KONTROLL2_NAME);
		assertEquals(patrol3.getStartStation().getStationName(),KONTROLL3_NAME);
		assertEquals(patrol4.getStartStation().getStationName(),KONTROLL1_NAME);
	}
}
