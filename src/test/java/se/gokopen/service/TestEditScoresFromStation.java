package se.gokopen.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})


public class TestEditScoresFromStation {


    @Autowired
    private ScoreService scoreService;

    @Ignore
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
