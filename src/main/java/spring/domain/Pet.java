package spring.domain;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Pet {

    private enum Status {FOUND, FOR_SELL, FOR_ADOPTION};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Profile profile;

    @Size(max=50)
    @Column(length = 50)
    private String name;

    private int age;

    @Column(name="fur_color", nullable = false)
    private String furColor;

    private String race;

    private String sex;

    private String species;

    @Enumerated(EnumType.STRING)
    private Status status;
}
