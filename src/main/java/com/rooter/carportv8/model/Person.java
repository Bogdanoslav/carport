package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Person extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "about")
    private String about;

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private UserCredentials userCredentials;

    public Person() {

    }

    public Person(Long id, String firstName, String lastName, String email, Integer age, String about, UserCredentials userCredentials) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.about = about;
        this.userCredentials = userCredentials;
    }
}
