package com.rooter.carportv8.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;
}
