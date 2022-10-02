package com.rooter.carportv8.rest;

import com.rooter.carportv8.hateoas.DriverAssembler;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("drivers")
public class DriverController {

    private final DriverService driverService;
    private final DriverAssembler driverAssembler;

    @Autowired
    public DriverController(DriverService driverService, DriverAssembler driverAssembler) {
        this.driverService = driverService;
        this.driverAssembler = driverAssembler;
    }

    @GetMapping("/{id}}")
    @PreAuthorize("hasRole('ROLE_PASSENGER') or hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> getById(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Driver driver = driverService.getById(id);
        return ResponseEntity.ok(driverAssembler.toModel(driver));
    }

}
