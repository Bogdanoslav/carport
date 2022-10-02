package com.rooter.carportv8.model;

import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {
    private String email;
    private Role role;

    public UserId() {
    }

    public UserId(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(email, userId.email) && Objects.equals(role, userId.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role);
    }
}
