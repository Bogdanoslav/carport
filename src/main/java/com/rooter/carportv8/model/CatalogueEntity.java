package com.rooter.carportv8.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class CatalogueEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    public CatalogueEntity(String name) {
        this.name = name;
    }
}
