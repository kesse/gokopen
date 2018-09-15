package se.gokopen.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.model.Status;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PatrolServiceImplTest extends SpringBootTestBase {

    @Autowired
    private PatrolService patrolService;
    @Autowired
    private StationService stationService;

    @Test
    public void shouldCreatePatrolInDatabase() throws PatrolNotSavedException {
        PatrolEntity patrol = getPatrol(Status.REGISTERED);

        patrolService.savePatrol(patrol);

        assertNotNull(patrol.getPatrolId());
        assertNotNull(patrol.getDateRegistered());

        //Cleaning up
        patrolService.deletePatrol(patrol);
    }

    @Test(expected = PatrolNotFoundException.class)
    public void testDeletePatrolById() throws PatrolNotFoundException, PatrolNotSavedException {
        PatrolEntity patrol = getPatrol(Status.REGISTERED);
        patrolService.savePatrol(patrol);

        Integer patrolid = patrol.getPatrolId();

        patrolService.deletePatrolById(patrolid);

        patrolService.getPatrolById(patrolid);
    }


    @Test
    public void testGetAllPatrolsLeftOnStation() throws PatrolNotSavedException {
        // Add patrols
        PatrolEntity patrol = getPatrol(Status.ACTIVE);
        patrol.setPatrolName("Active patrol");
        patrolService.savePatrol(patrol);
        patrolService.savePatrol(getPatrol(Status.RESIGNED));

        PatrolEntity patrol2 = getPatrol(Status.REGISTERED);
        patrolService.savePatrol(patrol2);

        // Add station
        StationEntity stationEntity = new StationEntity();
        stationService.saveStation(stationEntity);

        // Set score on station and patrol2
        ScoreEntity score = new ScoreEntity();
        score.setPatrol(patrol2);
        score.setStation(stationEntity);

        Set<ScoreEntity> scores = new HashSet<>();
        scores.add(score);
        patrol2.setScores(scores);
        patrolService.savePatrol(patrol2);

        Integer stationId = stationEntity.getStationId();

        // Test
        List<PatrolEntity> patrols = patrolService.getAllPatrolsLeftOnStation(stationId);

        // Assert
        assertEquals(2, patrols.size());
        assertEquals(patrol.getPatrolName(), patrols.get(0).getPatrolName());


        // Test
        List<PatrolEntity> onlyActives = patrolService.getAllActivePatrolsLeftOnStation(stationId);

        // Assert
        assertEquals(1, onlyActives.size());
        assertEquals(patrol.getPatrolName(), patrols.get(0).getPatrolName());
    }

    private PatrolEntity getPatrol(Status status) {
        PatrolEntity patrol = new PatrolEntity();
        patrol.setPatrolName("Test from junit");
        patrol.setLeaderContactPhone("123");
        patrol.setLeaderContactMail("123@a.se");
        patrol.setLeaderContact("contact");
        patrol.setTroop("kår");
        patrol.setNote("Just a test from automated testing. Should not be here - remove if you see");
        patrol.setStatus(status);

        return patrol;
    }

}
