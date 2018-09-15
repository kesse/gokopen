package se.gokopen.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "score")
public class ScoreEntity {
    private Integer scoreId = null;
    private StationEntity station;
    private int scorePoint;
    private int stylePoint;
    private PatrolEntity patrol;
    private Date lastSaved;
    private boolean visitedWaypoint;

    @Id
    @SequenceGenerator(name = "scoreSeqGen", sequenceName = "SCORE_SEQ", initialValue = 1, allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "scoreSeqGen")
    @Column(name = "scoreid", nullable = false)
    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    @ManyToOne
    @JoinColumn(name = "fk_station")
    public StationEntity getStation() {
        return station;
    }


    public void setStation(StationEntity station) {
        this.station = station;
    }

    @Column(name = "scorepoint")
    public int getScorePoint() {
        return scorePoint;
    }

    public void setScorePoint(int scorePoint) {
        this.scorePoint = scorePoint;
    }


    @Column(name = "stylepoint")
    public int getStylePoint() {
        return stylePoint;
    }


    public void setStylePoint(int stylePoint) {
        this.stylePoint = stylePoint;
    }

    @Column(name = "lastSaved")
    public Date getLastSaved() {
        return lastSaved;
    }

    public void setLastSaved(Date lastSaved) {
        this.lastSaved = lastSaved;
    }

    public void setPatrol(PatrolEntity patrol) {
        this.patrol = patrol;
    }

    @ManyToOne
    @JoinColumn(name = "fk_patrol")
    public PatrolEntity getPatrol() {
        return patrol;
    }

    public boolean isVisitedWaypoint() {
        return visitedWaypoint;
    }

    public void setVisitedWaypoint(boolean visitedWaypoint) {
        this.visitedWaypoint = visitedWaypoint;
    }
}
