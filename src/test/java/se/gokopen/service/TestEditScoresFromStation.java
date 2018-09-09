package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.gokopen.Application;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestEditScoresFromStation {

    @Autowired
    private ScoreService scoreService;

    @Test
    public void shouldReturnAllScoresOnOneStation(){
        List<ScoreEntity> scores = scoreService.getScoreOnStation(2);
        System.out.println("antal poäng: " + scores.size());
        for(ScoreEntity score:scores){
            System.out.println("poäng: " + score.getScorePoint());
            PatrolEntity patrol = score.getPatrol();
            if(patrol==null){
                System.out.println("patrullen är ingen");
            }else{
                System.out.println("patrullen är " + patrol.getPatrolName());
            }

        }
        
    }

}
