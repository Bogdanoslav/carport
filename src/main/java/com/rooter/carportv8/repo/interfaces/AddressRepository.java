package com.rooter.carportv8.repo.interfaces;

import com.rooter.carportv8.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
