package com.rooter.carportv8.rest;

import com.rooter.carportv8.dto.auth.RegisterDriver;
import com.rooter.carportv8.dto.auth.RegisterPassenger;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.payload.request.LoginRequest;
import com.rooter.carportv8.payload.request.RegisterRequest;
import com.rooter.carportv8.payload.response.JwtResponse;
import com.rooter.carportv8.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register/driver")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody RegisterDriver registerDriver){
        Driver driver = authService.registerDriver(registerDriver);
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/register/passenger")
    public ResponseEntity<?> registerPassenger(@Valid @RequestBody RegisterPassenger registerPassenger){
        Passenger passenger = authService.registerPassenger(registerPassenger);
        return ResponseEntity.ok(passenger);
    }
}
