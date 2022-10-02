package com.rooter.carportv8.repo.interfaces;

import com.querydsl.core.types.Predicate;
import com.rooter.carportv8.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends CustomCrudRepository<Booking>{
    boolean exists(Predicate predicate);
}
