package se.lexicon.api.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String title;

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName, String email, String title) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
    }

    public Person(String firstName, String lastName, String email, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
    }
}

