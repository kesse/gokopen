package se.gokopen.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.model.Status;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.exception.PatrolNotSavedException;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})

public class TestCreatePatrol {
    
    
    @Autowired
    private PatrolService patrolService;
    
    @Ignore
    @Test
    public void shouldCreatePatrolInDatabase() throws PatrolNotSavedException, PatrolNotFoundException{
        PatrolEntity patrol = new PatrolEntity();
        patrol.setPatrolName("Test from junit");
        patrol.setNote("Just a test from automated testing. Should not be here - remove if you see");
        patrol.setStatus(Status.REGISTERED);
        patrolService.savePatrol(patrol);
        System.out.println("patrol with id " + patrol.getPatrolId() + " has been saved");
        
        assertNotNull(patrol.getPatrolId());
        
        //Cleaning up
        patrolService.deletePatrol(patrol);
    }
}
