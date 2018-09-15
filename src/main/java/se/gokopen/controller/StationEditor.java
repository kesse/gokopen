package se.gokopen.controller;

import java.beans.PropertyEditorSupport;

import se.gokopen.persistence.entity.StationEntity;
import se.gokopen.persistence.exception.StationNotFoundException;
import se.gokopen.service.StationService;

public class StationEditor extends PropertyEditorSupport {


    private StationService stationService;

    public StationEditor(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        StationEntity station = null;
        try {
            station = stationService.getStationById(Integer.parseInt(text));
        } catch (StationNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setValue(station);
    }

    @Override
    public String getAsText() {
        StationEntity station = (StationEntity) getValue();
        if (station == null) {
            return null;
        } else {
            return station.getStationId().toString();
        }
    }
}


