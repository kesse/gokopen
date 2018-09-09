package se.gokopen.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.gokopen.Application;
import se.gokopen.model.Status;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCreatePatrol {
    
    @Autowired
    private PatrolService patrolService;
    
    @Test
    public void shouldCreatePatrolInDatabase() throws PatrolNotSavedException, PatrolNotFoundException{
        PatrolEntity patrol = new PatrolEntity();
        patrol.setPatrolName("Test from junit");
        patrol.setNote("Just a test from automated testing. Should not be here - remove if you see");
        patrol.setStatus(Status.REGISTERED);
        patrol.setLeaderContactPhone("123");
        patrol.setLeaderContactMail("123@a.se");
        patrol.setLeaderContact("contact");
        patrol.setTroop("kår");

        patrolService.savePatrol(patrol);
        System.out.println("patrol with id " + patrol.getPatrolId() + " has been saved");
        
        assertNotNull(patrol.getPatrolId());
        
        //Cleaning up
        patrolService.deletePatrol(patrol);
    }
}
