package com.rooter.carportv8.rest;

import com.rooter.carportv8.dto.driver.UpdateDriverProfile;
import com.rooter.carportv8.hateoas.DriverProfileAssembler;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.DriverProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers/profile")
public class DriverProfileController {
    private final DriverProfileService driverProfileService;
    private final DriverProfileAssembler driverProfileAssembler;

    public DriverProfileController(DriverProfileService driverProfileService, DriverProfileAssembler driverProfileAssembler) {
        this.driverProfileService = driverProfileService;
        this.driverProfileAssembler = driverProfileAssembler;
    }

    @GetMapping
    public ResponseEntity<?> get(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Driver driver = driverProfileService.getProfile(userDetails.getId());
        return ResponseEntity.ok(driverProfileAssembler.toModel(driver));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateDriverProfile updateDriverProfile, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Driver driver = driverProfileService.update(userDetails.getId(),updateDriverProfile);
        return ResponseEntity.ok(driverProfileAssembler.toModel(driver));
    }
}
