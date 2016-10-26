package spring.domain;

import lombok.Data;
import spring.domain.geo.County;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Data
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name="county")
    private County location;

    public Organisation() {
    }

    @OneToOne

    @JoinColumn(name = "Address_id", referencedColumnName = "id")
    private Address Address;
}
