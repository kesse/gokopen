package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.PatrolEntity;

public class TestCriteriaVSQuery extends SpringBootTestBase {

    @Autowired
    private PatrolService patrolService;

    @Test
    public void shouldReturnTheSameResults() {

        long startCriteria = System.currentTimeMillis();
        List<PatrolEntity> patrolsCriteria = patrolService.getAllPatrolsCriteria();
        long stopCriteria = System.currentTimeMillis();
        long startQuery = System.currentTimeMillis();
        List<PatrolEntity> patrolsQuery = patrolService.getAllPatrols();
        long stopQuery = System.currentTimeMillis();


        long totalQuery = stopQuery - startQuery;
        long totalCriteria = stopCriteria - startCriteria;

        System.out.println("query time: " + totalQuery + " " + patrolsQuery.size());
        System.out.println("criteria time: " + totalCriteria + " " + patrolsCriteria.size());
    }

}
