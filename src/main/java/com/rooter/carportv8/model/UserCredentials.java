package com.rooter.carportv8.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "user_credentials")
@Getter
@Setter
public class UserCredentials {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Person person;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToMany
    @JoinColumn(name = "role_id")
    private List<Role> roles;

    @Column(name = "is_active")
    private Boolean active = true;

    public UserCredentials() {
    }

    @Builder
    public UserCredentials(String username, String password, Person person, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.roles = roles;
    }

}
