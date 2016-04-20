package eu.programisci.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by PC on 2016-04-20.
 */

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 35, nullable = false)
    @Size(min=3, max=35)
    private String username;

    @Column(length = 35, nullable = false)
    @Size(min=6, max=35)
    private String password;

    @Column(length = 100, nullable = false)
    @Size(max=100)
    @Email
    private String email;

    
}
