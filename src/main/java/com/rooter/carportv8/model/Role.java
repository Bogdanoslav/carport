package com.rooter.carportv8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rooter.carportv8.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter @Setter
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<UserCredentials> users;
}
