package com.rooter.carportv8.service;

import com.rooter.carportv8.dto.auth.RegisterDriver;
import com.rooter.carportv8.dto.auth.RegisterPassenger;
import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.payload.request.LoginRequest;
import com.rooter.carportv8.payload.request.RegisterRequest;
import com.rooter.carportv8.payload.response.JwtResponse;

import java.util.List;

public interface AuthService {
    Driver registerDriver(RegisterDriver registerDriver);
    Passenger registerPassenger(RegisterPassenger registerPassenger);
    JwtResponse login(LoginRequest loginRequest);
    void deleteProfile(Long id);
}
