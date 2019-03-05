package se.gokopen.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "track")
public class TrackEntity {
    private Integer trackId;
    private String trackName;

    @Id
    @SequenceGenerator(name = "trackSeqGen", sequenceName = "TRACK_SEQ", initialValue = 100, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "trackSeqGen")
    @Column(name = "trackid", nullable = false)
    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    @Column(name = "trackname", length = 50)
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + trackId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrackEntity other = (TrackEntity) obj;
        if (trackId != other.trackId)
            return false;
        return true;
    }


}
