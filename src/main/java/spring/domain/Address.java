package spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="city", nullable = false)
    private String city;

    @Column(name="street", nullable = false)
    private String street;

    @Column(name="street_number", nullable = false)
    private int streetNumber;

    @Column(name="home_number", nullable = true)
    private int homeNumber;

    @Column(name="zip_code", nullable = true)
    private String zipCode;
}
