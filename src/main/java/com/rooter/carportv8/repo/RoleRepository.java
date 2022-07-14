package com.rooter.carportv8.repo;

import com.rooter.carportv8.model.ERole;
import com.rooter.carportv8.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
