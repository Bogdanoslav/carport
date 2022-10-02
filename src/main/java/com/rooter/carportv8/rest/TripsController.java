package com.rooter.carportv8.rest;

import com.rooter.carportv8.dto.trip.SaveTrip;
import com.rooter.carportv8.hateoas.TripAssembler;
import com.rooter.carportv8.mapper.TripMapper;
import com.rooter.carportv8.model.Trip;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
public class TripsController {
    private final TripService tripService;
    private final TripAssembler tripAssembler;
    private final PagedResourcesAssembler<Trip> pagedResourcesAssembler;

    @Autowired
    public TripsController(TripService tripService,
                           TripAssembler tripAssembler,
                           PagedResourcesAssembler<Trip> pagedResourcesAssembler) {
        this.tripService = tripService;
        this.tripAssembler = tripAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_PASSENGER') or hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> search(@RequestParam(value = "search", required = false)
                                            String search,
                                    Pageable pageable) {
        Page<Trip> trips = tripService.search(search, pageable);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(trips, tripAssembler));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_PASSENGER') or hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> getById(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Trip trip = tripService.getById(id);
        return ResponseEntity.ok(tripAssembler.toModel(trip));
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @PostMapping()
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Validated SaveTrip saveTrip, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Trip trip = tripService.save(saveTrip, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tripAssembler.toModel(trip));
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<?> cancel(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Trip trip = tripService.cancel(id, userDetails.getId());
        return ResponseEntity.ok(tripAssembler.toModel(trip));
    }


}
