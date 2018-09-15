package se.gokopen.model;

import java.util.List;

import se.gokopen.persistence.entity.PatrolEntity;
import se.gokopen.persistence.entity.StationEntity;

public class StartStation {
    private StationEntity station;
    private List<PatrolEntity> patrols;


    public StationEntity getStation() {
        return station;
    }

    public void setStation(StationEntity station) {
        this.station = station;
    }

    public List<PatrolEntity> getPatrols() {
        return patrols;
    }

    public void setPatrols(List<PatrolEntity> patrols) {
        this.patrols = patrols;
    }

}
