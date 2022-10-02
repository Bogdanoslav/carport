package com.rooter.carportv8.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rooter.carportv8.model.BaseEntity;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private Integer age;
    private String about;
    private Set<Role> roles;
    private Set<com.rooter.carportv8.model.Car> cars;
}
