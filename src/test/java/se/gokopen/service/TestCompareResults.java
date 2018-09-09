package se.gokopen.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;
import se.gokopen.persistence.entity.StationEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestCompareResults {
    
    private PatrolEntity patrol1;
    private PatrolEntity patrol2;
    private StationEntity station1;
    private StationEntity station2;
    private StationEntity station3;
    private StationEntity station4;
    private StationEntity station5;
    private ScoreEntity score1;
    private ScoreEntity score2;
    private ScoreEntity score3;
    private ScoreEntity score4;
    private ScoreEntity score5;
    private ScoreEntity score6;
    private ScoreEntity score7;
    private ScoreEntity score8;
    private ScoreEntity score9;
    private ScoreEntity score10;
      
    
    
    @Before
    public void init(){
        patrol1 = new PatrolEntity();
        patrol2 = new PatrolEntity();
    }
    
    @Test
    public void shouldCalucalateTheRightScore(){
        patrol1 = addScores(patrol1);
        assertThat(patrol1.getTotalScore(),is(equalTo(17)));
    }

    
    @Test
    public void shouldCalculateTheRightScoreWithStyleScore(){
        patrol1 = addScores(patrol1);
        addStylePointsToScores(patrol1.getScores());
        int totalStylePoints = patrol1.getScores().size();
        assertThat(patrol1.getTotalScore(),is(equalTo(17 + totalStylePoints)));
    }
    
    @Test
    public void shouldCompareTwoPatrolsAndFindOneWithMorePointsToBeTheWinner(){
        patrol1 = addScores(patrol1);
        patrol1 = addMoreScores(patrol1);
        patrol2 = addScores(patrol2);
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
    }
    
    @Test
    public void twoPatrolsWithTheSameScoreButOneWithMoreStylePointsShouldBeLast(){
        patrol1 = addScores(patrol1);
        patrol1 = addMoreScores(patrol1);
        patrol2 = addScores(patrol2);
        addStylePointsToScores(patrol2.getScores());
        
        //same total score
        assertThat(patrol1.getTotalScore(),is(equalTo(patrol2.getTotalScore())));
        //winner is the patrol with more scores and less style-points
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
    }
    
    @Test
    public void shouldFindAWinnerAmongPatrolsWithTheSameScoreBasedOn10Points() {
        prepareStations();
        prepareScores();
        
        score1.setStation(station1);
        score2.setStation(station2);
        score3.setStation(station3);
        score4.setStation(station1);
        score5.setStation(station2);
        score6.setStation(station3);
        
        score1.setScorePoint(10);
        score2.setScorePoint(8);
        score3.setScorePoint(10);
        
        Set<ScoreEntity> scores = new LinkedHashSet<ScoreEntity>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        patrol1.setScores(scores);
        
        score4.setScorePoint(9);
        score5.setScorePoint(9);
        score6.setScorePoint(10);
        
        Set<ScoreEntity> moreScores = new LinkedHashSet<ScoreEntity>();
        moreScores.add(score4);
        moreScores.add(score5);
        moreScores.add(score6);
        patrol2.setScores(moreScores);
        
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
        
    }

    @Test
    public void shouldFindAWinnerAmongPatrolsWithTheSameScoreBasedOn10PointsAnd9points() {
        prepareStations();
        prepareScores();
        
        score1.setStation(station1);
        score2.setStation(station2);
        score3.setStation(station3);
        score4.setStation(station1);
        score5.setStation(station2);
        score6.setStation(station3);
        score7.setStation(station4);
        score8.setStation(station4);
        
        score1.setScorePoint(10);
        score2.setScorePoint(7);
        score3.setScorePoint(10);
        score7.setScorePoint(9);
        
        Set<ScoreEntity> scores = new LinkedHashSet<ScoreEntity>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score7);
        patrol1.setScores(scores);
        System.out.println("patrull1 " + patrol1.getTotalScore());
        
        score4.setScorePoint(10);
        score5.setScorePoint(8);
        score6.setScorePoint(10);
        score8.setScorePoint(8);
        
        Set<ScoreEntity> moreScores = new LinkedHashSet<ScoreEntity>();
        moreScores.add(score4);
        moreScores.add(score5);
        moreScores.add(score6);
        moreScores.add(score8);
        patrol2.setScores(moreScores);
        System.out.println("patrull2 " + patrol2.getTotalScore());
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
        
    }
    
    @Test
    public void shouldFindAWinnerAmongPatrolsWithTheSameScoreBasedOn10PointsAnd9pointsAnd7points() {
        prepareStations();
        prepareScores();
        
        score1.setStation(station1);
        score2.setStation(station2);
        score3.setStation(station3);
        score4.setStation(station1);
        score5.setStation(station2);
        score6.setStation(station3);
        score7.setStation(station4);
        score8.setStation(station4);
        score9.setStation(station5);
        score10.setStation(station5);
        
        score1.setScorePoint(10);
        score2.setScorePoint(7);
        score3.setScorePoint(10);
        score7.setScorePoint(6);
        score9.setScorePoint(4);
        
        Set<ScoreEntity> scores = new LinkedHashSet<ScoreEntity>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score7);
        scores.add(score9);
        patrol1.setScores(scores);
        System.out.println("patrull1 " + patrol1.getTotalScore());
        
        score4.setScorePoint(10);
        score5.setScorePoint(7);
        score6.setScorePoint(10);
        score8.setScorePoint(5);
        score10.setScorePoint(5);
        Set<ScoreEntity> moreScores = new LinkedHashSet<ScoreEntity>();
        moreScores.add(score4);
        moreScores.add(score5);
        moreScores.add(score6);
        moreScores.add(score8);
        moreScores.add(score10);
        patrol2.setScores(moreScores);
        System.out.println("patrull2 " + patrol2.getTotalScore());
        assertThat(patrol1.compareTo(patrol2),is(equalTo(-1)));
        
    }
    
    private void prepareScores() {
        score1 = new ScoreEntity();
        score2 = new ScoreEntity();
        score3 = new ScoreEntity();
        score4 = new ScoreEntity();
        score5 = new ScoreEntity();
        score6 = new ScoreEntity();
        score7 = new ScoreEntity();
        score8 = new ScoreEntity();
        score9 = new ScoreEntity();
        score10 = new ScoreEntity();
    }

    private void prepareStations() {
        station1 = new StationEntity();
        station2 = new StationEntity();
        station3 = new StationEntity();
        station4 = new StationEntity();
        station5 = new StationEntity();
        
        station1.setMaxScore(10);
        station2.setMaxScore(10);
        station3.setMaxScore(10);
        station4.setMaxScore(10);
        station5.setMaxScore(10);
    }
    
    private PatrolEntity addScores(PatrolEntity patrol) {
        patrol.setScores(createBasicScores());
        return patrol;
    }
    
    private void addStylePointsToScores(Set<ScoreEntity> scores){
        for(ScoreEntity score:scores){
            score.setStylePoint(1);
        }
    }
    
    private PatrolEntity addMoreScores(PatrolEntity patrol){
        ScoreEntity score = new ScoreEntity();
        score.setScorePoint(2);
        patrol.getScores().add(score);
        
        return patrol;
    }
    
    private Set<ScoreEntity> createBasicScores(){
        ScoreEntity score1 = new ScoreEntity();
        score1.setScorePoint(9);
        ScoreEntity score2 = new ScoreEntity();
        score2.setScorePoint(8);
        
        Set<ScoreEntity> scores1 = new LinkedHashSet<ScoreEntity>();
        scores1.add(score1);
        scores1.add(score2);
        return scores1;
    }
}
