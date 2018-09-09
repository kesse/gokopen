package se.gokopen.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import se.gokopen.persistence.entity.ConfigEntity;

public class TestRegistrationLogic {

    
    @Test
    public void shouldReturnTrueIfAllParametersAreGood() throws ParseException {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(true);      
        config.setLastRegisterDay(new Date()); 
        config.setMaxPatrols(200);
        int noOfPatrols = 100;
        
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    
    @Test
    public void shouldReturnFalseSinceRegistrationIsOff() throws ParseException {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(false);
        config.setLastRegisterDay(new Date()); 
        int noOfPatrols = 100;
        
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnFalseSinceDateIsHistory() throws ParseException {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(true);
        config.setLastRegisterDay(yesterday()); 
        int noOfPatrols = 100;
        
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnTrueSinceDateIsTomorrow() throws ParseException {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(true);
        config.setLastRegisterDay(tomorrow()); 
        config.setMaxPatrols(200);
        int noOfPatrols = 100;
        
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnFalseSinceFull() throws ParseException {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(true);
        config.setLastRegisterDay(tomorrow()); 
        config.setMaxPatrols(200);
        int noOfPatrols = 200;
        
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnTrueSinceFull() throws ParseException {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(true);
        config.setLastRegisterDay(tomorrow()); 
        config.setMaxPatrols(300);
        int noOfPatrols = 200;
        
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    //Test där inte alla värden är satta
    @Test
    public void shouldReturnTrueSinceNoEndDateIsSet() {
        ConfigEntity config = new ConfigEntity();
        config.setAllowPublicRegistration(true);
        int noOfPatrols = 200;
        assertTrue(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    @Test
    public void shouldReturnFalseSinceNoAllowIsSet() {
        ConfigEntity config = new ConfigEntity();
        int noOfPatrols = 200;
        assertFalse(RegistrationChecker.isOpenForRegistration(config, noOfPatrols));
    }
    
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    
    private Date tomorrow() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
