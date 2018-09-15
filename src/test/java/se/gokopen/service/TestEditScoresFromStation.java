package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;

public class TestEditScoresFromStation extends SpringBootTestBase {

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
