package se.gokopen.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestScore {
    
    @Test
    public void should_calculate_score_for_patrol() {
        PatrolEntity patrol = new PatrolEntity();
        patrol.setPatrolName("Eriks patrull");
        
        StationEntity station1 = new StationEntity();
        station1.setMaxScore(10);
        station1.setMinScore(0);
        station1.setStationName("Station 1");
        station1.setStationNumber(1);
        
        StationEntity station2 = new StationEntity();
        station2.setStationName("Station 2 - waypoint");
        station2.setStationNumber(2);
        station2.setWaypoint(true);
        
        StationEntity station3 = new StationEntity();
        station3.setStationName("Station 3");
        station3.setStationNumber(3);
        station3.setMaxScore(10);
        station3.setMinScore(0);
        
        Set<ScoreEntity> scores = new LinkedHashSet<ScoreEntity>();
        
        ScoreEntity score1 = new ScoreEntity();
        score1.setScorePoint(5);
        score1.setStation(station1);
        score1.setPatrol(patrol);
        score1.setStylePoint(1);
        scores.add(score1);
        
        ScoreEntity score2 = new ScoreEntity();
        score2.setPatrol(patrol);
        score2.setStation(station2);
        score2.setVisitedWaypoint(true);
        scores.add(score2);
        
        ScoreEntity score3 = new ScoreEntity();
        score3.setPatrol(patrol);
        score3.setStation(station3);
        score3.setScorePoint(6);
        score3.setStylePoint(1);
        scores.add(score3);
        
        patrol.setScores(scores);
        
        assertThat(patrol.getTotalScore(),equalTo(13));
        assertThat(patrol.getTotalReportedStations(),equalTo(3));

    }
}
