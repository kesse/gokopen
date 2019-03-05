package se.gokopen.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.PatrolNotSavedException;
import se.gokopen.persistence.exception.ScoreNotSavedException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ScoreServiceImplTest extends SpringBootTestBase {

    private StationEntity station1;
    private StationEntity station2;
    private PatrolEntity patrol1;
    private PatrolEntity patrol2;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private PatrolService patrolService;

    @Autowired
    private StationService stationService;

    @Test
    public void testSaveScore() throws ScoreNotSavedException {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreService.saveScore(scoreEntity);

        assertNotNull(scoreEntity.getScoreId());
    }

    @Test
    public void shouldNotSaveIfScoreAlreadySaved() throws PatrolNotSavedException {
        patrol1 = new PatrolEntity();
        patrol2 = new PatrolEntity();
        station1 = new StationEntity();
        station2 = new StationEntity();

        patrol1.setPatrolName("TestPatrol1");
        patrol1.setLeaderContactPhone("123");
        patrol1.setLeaderContactMail("123@a.se");
        patrol1.setLeaderContact("contact");
        patrol1.setTroop("kår");

        patrol2.setPatrolName("TestPatrol2");
        patrol2.setLeaderContactPhone("123");
        patrol2.setLeaderContactMail("123@a.se");
        patrol2.setLeaderContact("contact");
        patrol2.setTroop("kår");

        patrolService.savePatrol(patrol1);
        patrolService.savePatrol(patrol2);

        station1.setStationName("TestStation88");
        stationService.saveStation(station1);

//        station1.setStationNumber(88);
        station2.setStationName("TestStation99");
//        station2.setStationId(99);

        stationService.saveStation(station2);

        ScoreEntity score1 = new ScoreEntity();
        score1.setPatrol(patrol1);
        score1.setStation(station1);
        score1.setScorePoint(10);

        try {
            scoreService.saveScore(score1);
        } catch (ScoreNotSavedException e) {
            System.out.println("meddelande " + e.getErrorMsg());
        }


        assertNotNull(score1.getScoreId());

        ScoreEntity score2 = new ScoreEntity();
        score2.setPatrol(patrol2);
        score2.setStation(station1);
        score2.setScorePoint(8);
        try {
            scoreService.saveScore(score2);
        } catch (ScoreNotSavedException e) {
            e.printStackTrace();
        }

        assertNotNull(score2.getScoreId());

        ScoreEntity score3 = new ScoreEntity();
        score3.setPatrol(patrol2);
        score3.setStation(station1);
        score3.setScorePoint(9);
        try {
            scoreService.saveScore(score3);
        } catch (ScoreNotSavedException e) {
            assertNotNull(e);
        }

        assertNull(score3.getScoreId());
    }

}