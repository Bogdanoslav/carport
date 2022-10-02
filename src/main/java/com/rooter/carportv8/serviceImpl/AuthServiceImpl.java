package com.rooter.carportv8.serviceImpl;

import com.rooter.carportv8.dto.auth.RegisterDriver;
import com.rooter.carportv8.dto.auth.RegisterPassenger;
import com.rooter.carportv8.model.*;
import com.rooter.carportv8.model.enums.ERole;
import com.rooter.carportv8.payload.request.LoginRequest;
import com.rooter.carportv8.payload.response.JwtResponse;
import com.rooter.carportv8.repo.interfaces.RoleRepository;
import com.rooter.carportv8.repo.interfaces.UserCredentialsRepository;
import com.rooter.carportv8.searchPredicates.PassengerPredicates;
import com.rooter.carportv8.searchPredicates.UserCredentialsPredicates;
import com.rooter.carportv8.security.JwtUtils;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserCredentialsRepository userCredentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(JwtUtils jwtUtils,
                           AuthenticationManager authenticationManager,
                           UserCredentialsRepository userCredentialsRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userCredentialsRepository = userCredentialsRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Driver registerDriver(RegisterDriver registerDriver) {
        String username = registerDriver.getUsername();

        if (userCredentialsRepository.exists(UserCredentialsPredicates.hasUsername(username))) {
            throw new BadCredentialsException("This username is already taken");
        }

        List<Role> rolesDriver = new ArrayList<>();
        rolesDriver.add(roleRepository.findByName(ERole.ROLE_DRIVER).orElseThrow(() -> new NoSuchElementException("Role not found")));

        String encodedPassword = passwordEncoder.encode(registerDriver.getPassword());
        Driver driver = Driver.builder()
                .firstName(registerDriver.getFirstName())
                .lastName(registerDriver.getLastName())
                .age(registerDriver.getAge())
                .about(registerDriver.getAbout())
                .drivingExperience(registerDriver.getDrivingExperience())
                .email(registerDriver.getEmail())
                .build();
        UserCredentials userCredentials = UserCredentials
                .builder()
                .username(registerDriver.getUsername())
                .password(encodedPassword)
                .person(driver)
                .roles(rolesDriver)
                .build();
        userCredentialsRepository.save(userCredentials);
       return driver;
    }

    @Override
    public Passenger registerPassenger(RegisterPassenger registerPassenger) {
        String username = registerPassenger.getUsername();

        if (userCredentialsRepository.exists(UserCredentialsPredicates.hasUsername(username))) {
            throw new BadCredentialsException("This username is already taken");
        }

        List<Role> rolesPassenger = new ArrayList<>();
        rolesPassenger.add(roleRepository.findByName(ERole.ROLE_PASSENGER).orElseThrow(() -> new NoSuchElementException("Role not found")));

        String encodedPassword = passwordEncoder.encode(registerPassenger.getPassword());
        Passenger passenger = Passenger.builder()
                .firstName(registerPassenger.getFirstName())
                .lastName(registerPassenger.getLastName())
                .age(registerPassenger.getAge())
                .about(registerPassenger.getAbout())
                .email(registerPassenger.getEmail())
                .build();
        UserCredentials userCredentials = UserCredentials
                .builder()
                .username(registerPassenger.getUsername())
                .password(encodedPassword)
                .person(passenger)
                .roles(rolesPassenger)
                .build();
        userCredentialsRepository.save(userCredentials);
        return passenger;
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public void deleteProfile(Long id) {
        UserCredentials userCredentials = userCredentialsRepository.findOne(PassengerPredicates.hasId(id))
                .orElseThrow(()->new NoSuchElementException(String.format("No record of %s could be found with id %d.", UserCredentials.class.getSimpleName(), id)));
        userCredentials.setDeletedAt(LocalDateTime.now());
        userCredentials.setActive(false);
        userCredentialsRepository.save(userCredentials);
    }
}
