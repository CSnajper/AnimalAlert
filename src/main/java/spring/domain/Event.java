package spring.domain;

import lombok.Data;
import spring.domain.geo.Geolocalization;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class Event {

    public enum Type {LOST("LOST"), HOMELESS("HOMELESS");
        private String typeS;
        Type(String typeS) {
            this.typeS = typeS;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name="geolocalization_id")
    private Geolocalization geolocalization;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "is_finished")
    private boolean isFinished;
}
