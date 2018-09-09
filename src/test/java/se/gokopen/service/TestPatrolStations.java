package se.gokopen.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.ScoreEntity;


public class TestPatrolStations {
    private static final String NAME_PATROL2 = "ATestPatrol2";
    private static final String NAME_PATROL1 = "BTestPatrol1";

    PatrolEntity patrol1;
    PatrolEntity patrol2;

    @Test
    public void shouldSortListofPatrolsAfterNoOfStations(){
        initTestData();

        List<PatrolEntity> patrols = new ArrayList<PatrolEntity>();
        patrols.add(patrol1);
        patrols.add(patrol2);

        Collections.sort(patrols, new BeanComparator("totalReportedStations"));
        assertThat(patrols.get(0).getPatrolName(),is(equalTo(NAME_PATROL2)));
    }

    @Test
    public void shouldSortHighestScoreHighest(){
        initTestData();

        List<PatrolEntity> patrols = new ArrayList<PatrolEntity>();
        patrols.add(patrol2);
        patrols.add(patrol1);

        Collections.sort(patrols, Collections.reverseOrder(new BeanComparator("totalScore")));
        assertThat(patrols.get(0).getPatrolName(),is(equalTo(NAME_PATROL1)));
    }

    private void initTestData() {
        patrol1 = new PatrolEntity();
        patrol2 = new PatrolEntity();

        patrol1.setPatrolName(NAME_PATROL1);
        patrol2.setPatrolName(NAME_PATROL2);

        ScoreEntity score1 = new ScoreEntity();
        score1.setPatrol(patrol1);
        score1.setScorePoint(10);

        ScoreEntity score2 = new ScoreEntity();
        score2.setPatrol(patrol1);
        score2.setScorePoint(8);

        ScoreEntity score3 = new ScoreEntity();
        score3.setPatrol(patrol1);
        score3.setScorePoint(8);

        ScoreEntity score4 = new ScoreEntity();
        score4.setPatrol(patrol1);
        score4.setScorePoint(8);

        Set<ScoreEntity> scores = new LinkedHashSet<ScoreEntity>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);

        patrol1.setScores(scores);


        ScoreEntity score5 = new ScoreEntity();
        score5.setPatrol(patrol1);
        score5.setScorePoint(8);

        ScoreEntity score6 = new ScoreEntity();
        score6.setPatrol(patrol1);
        score6.setScorePoint(8);

        ScoreEntity score7 = new ScoreEntity();
        score7.setPatrol(patrol1);
        score7.setScorePoint(8);

        Set<ScoreEntity> scores2 = new LinkedHashSet<ScoreEntity>();
        scores2.add(score5);
        scores2.add(score6);
        scores2.add(score7);

        patrol2.setScores(scores2);
    }
}
