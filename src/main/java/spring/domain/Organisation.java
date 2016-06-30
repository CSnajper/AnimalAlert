package spring.domain;

import spring.domain.geo.County;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address Address) {
        this.Address = Address;
    }

    public County getLocation() {
        return location;
    }

    public void setLocation(County location) {
        this.location = location;
    }
}
