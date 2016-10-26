package spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone_number")
    private String phoneNumber;
}
