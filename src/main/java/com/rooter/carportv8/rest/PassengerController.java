package com.rooter.carportv8.rest;

import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/{id}}")
    @PreAuthorize("hasRole('ROLE_PASSENGER') or hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> getById(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Passenger passenger = passengerService.getById(id);
        return ResponseEntity.ok(passenger);
//        return ResponseEntity.ok(userAssembler.toModel(user));
    }
}
