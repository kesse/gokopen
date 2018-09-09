package se.gokopen.controller;

import java.beans.PropertyEditorSupport;

import se.gokopen.persistence.exception.PatrolNotFoundException;
import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.service.PatrolService;

public class PatrolEditor extends PropertyEditorSupport {


    private PatrolService patrolService;

    public PatrolEditor(PatrolService patrolService) {
        this.patrolService = patrolService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        PatrolEntity patrol = null;
        try {
            patrol = patrolService.getPatrolById(Integer.parseInt(text));
        } catch (PatrolNotFoundException e) {
            e.printStackTrace();
        }
        setValue(patrol);
    }

    @Override
    public String getAsText() {
        PatrolEntity patrol = (PatrolEntity) getValue();
        if (patrol == null) {
            return null;
        } else {
            return patrol.toString();
        }
    }
}




