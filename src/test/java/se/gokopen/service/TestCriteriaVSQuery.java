package se.gokopen.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.gokopen.Application;
import se.gokopen.persistence.entity.PatrolEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCriteriaVSQuery {
    
    @Autowired
    private PatrolService patrolService;
    
    @Test
    public void shouldReturnTheSameResults(){
        
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
