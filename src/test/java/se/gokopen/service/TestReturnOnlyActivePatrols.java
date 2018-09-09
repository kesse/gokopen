package se.gokopen.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.model.Status;

public class TestReturnOnlyActivePatrols {
    
    //TODO funktionen är ändrad till att faktiskt ta med patruller som kommit i mål. Testerna borde ändras för att reflektera detta bättre än att bara ändra själva equalTo
    @Test
    public void shouldReturnOnlyActivePatrolsFromList(){
        PatrolEntity patrol1 = new PatrolEntity();
        PatrolEntity patrol2 = new PatrolEntity();
        PatrolEntity patrol3 = new PatrolEntity();
        PatrolEntity patrol4 = new PatrolEntity();
        
        patrol1.setStatus(Status.ACTIVE);
        patrol2.setStatus(Status.FINISHED);
        patrol3.setStatus(Status.REGISTERED);
        patrol4.setStatus(Status.RESIGNED);
        
        List<PatrolEntity> patrols = new ArrayList<PatrolEntity>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);
        
        PatrolService patrolService = new PatrolServiceImpl();
        
        List<PatrolEntity> onlyActiveAndWaitingPatrols = patrolService.getActiveAndWaitingPatolsFromList(patrols);
        assertThat(onlyActiveAndWaitingPatrols.size(),is(equalTo(3)));
        
    }

    @Test
    public void shouldReturnOnlyActivePatrolsFromListAndWorkWithNullStatus(){
        PatrolEntity patrol1 = new PatrolEntity();
        PatrolEntity patrol2 = new PatrolEntity();
        PatrolEntity patrol3 = new PatrolEntity();
        PatrolEntity patrol4 = new PatrolEntity();
        
        patrol1.setStatus(Status.ACTIVE);
        patrol2.setStatus(Status.FINISHED);
        patrol3.setStatus(Status.REGISTERED);
        
        
        List<PatrolEntity> patrols = new ArrayList<PatrolEntity>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);
        
        PatrolService patrolService = new PatrolServiceImpl();
        
        List<PatrolEntity> onlyActiveAndWaitingPatrols = patrolService.getActiveAndWaitingPatolsFromList(patrols);
        assertThat(onlyActiveAndWaitingPatrols.size(),is(equalTo(3)));
        
    }
}
