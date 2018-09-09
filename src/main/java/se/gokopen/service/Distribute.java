package se.gokopen.service;

import java.util.List;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;

public class Distribute {

	static int lastUsedStation;
	
	private Distribute(){
	    
	}
	
	//Det här sättet ger en flexibilitet, kan dock bli väldigt minnestungt om alla patruller och stationer ska laddas i minnet
	//En annan variant om att gå mot databasen kan vara bättre men ger svårare testbarhet, om jag inte fixar en inmemory-db för test
	public static void patrolsOnStations(List<PatrolEntity> patrols, List<StationEntity> stations){
		int noOfStations = stations.size();
		int noOfPatrols = patrols.size();
		lastUsedStation = 0;
		
		System.out.println("No of patrols: " + noOfPatrols);
		System.out.println("No of stations: " + noOfStations);

		for(PatrolEntity patrol:patrols){
			System.out.println("lastUsedStation " + lastUsedStation);
			patrol.setStartStation(stations.get(lastUsedStation));
			calculateLastUsedStation(stations);
		}
	}

	private static void calculateLastUsedStation(List<StationEntity> stations) {
		if(lastUsedStation<stations.size()-1){
			lastUsedStation++;
		}else{
			lastUsedStation = 0;
		}
	}
}