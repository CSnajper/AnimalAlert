package spring.rest.dto.geo;

import java.io.Serializable;

/**
 * Created by jakub on 22.06.16.
 */
public class CoordinatsDTO implements Serializable {
    private LatLngDTO start;
    private LatLngDTO finish;

    public CoordinatsDTO() {
    }

    public CoordinatsDTO(LatLngDTO start, LatLngDTO finish) {
        this.start = start;
        this.finish = finish;
    }

    public LatLngDTO getStart() {
        return start;
    }

    public void setStart(LatLngDTO start) {
        this.start = start;
    }

    public LatLngDTO getFinish() {
        return finish;
    }

    public void setFinish(LatLngDTO finish) {
        this.finish = finish;
    }
}
