package se.gokopen.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.gokopen.model.Status;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.repository.PatrolRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestReturnOnlyActivePatrols {

    @Mock
    private PatrolRepository patrolRepository;

    @InjectMocks
    private PatrolServiceImpl patrolService;
    
    //TODO funktionen är ändrad till att faktiskt ta med patruller som kommit i mål.
    // Testerna borde ändras för att reflektera detta bättre än att bara ändra själva equalTo
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
        
        List<PatrolEntity> patrols = new ArrayList<>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);

        when(patrolRepository.findAll()).thenReturn(patrols);
        
        List<PatrolEntity> onlyActiveAndWaitingPatrols = patrolService.getAllActivePatrolsLeftOnStation(1);
        assertThat(onlyActiveAndWaitingPatrols.size(), is(equalTo(3)));
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
        
        List<PatrolEntity> patrols = new ArrayList<>();
        patrols.add(patrol1);
        patrols.add(patrol2);
        patrols.add(patrol3);
        patrols.add(patrol4);

        when(patrolRepository.findAll()).thenReturn(patrols);

        List<PatrolEntity> onlyActiveAndWaitingPatrols = patrolService.getAllActivePatrolsLeftOnStation(1);
        assertThat(onlyActiveAndWaitingPatrols.size(), is(equalTo(3)));
    }
}
