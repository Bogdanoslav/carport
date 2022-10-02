package com.rooter.carportv8.rest;

import com.rooter.carportv8.dto.car.GetCar;
import com.rooter.carportv8.dto.car.PartialUpdateCar;
import com.rooter.carportv8.dto.car.SaveCar;
import com.rooter.carportv8.hateoas.CarAssembler;
import com.rooter.carportv8.mapper.CarMapper;
import com.rooter.carportv8.model.Car;
import com.rooter.carportv8.security.UserDetailsImpl;
import com.rooter.carportv8.service.CarService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/cars")
public class CarsController {
    private final CarService carService;
    private final CarAssembler carAssembler;
    private final CarMapper carMapper;

    @Autowired
    public CarsController(
            CarService carService,
            CarAssembler carAssembler,
            CarMapper carMapper){
        this.carService = carService;
        this.carMapper = carMapper;
        this.carAssembler = carAssembler;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> getById(@PathVariable("id") Long carId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Car car = carService.getCar(carId, userDetails.getId());
        return ResponseEntity.ok(carAssembler.toModel(car));
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> getAllByOwnerId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Car> cars = carService.getCarsByOwnerId(userDetails.getId());
        List<EntityModel<GetCar>> carsEntities = cars.stream().map(carAssembler::toModel).toList();
        CollectionModel<EntityModel<GetCar>> collectionModel =
                CollectionModel.of(carsEntities,
                        linkTo(methodOn(CarsController.class).getAllByOwnerId(userDetails))
                                .withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @ApiResponse(responseCode = "405", description = "Validation exception")
    public ResponseEntity<?> save(@RequestBody @Valid SaveCar saveCar,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Car car = carService.saveCar(saveCar, userDetails.getId());
        car = carService.getCar(car.getId(), userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(carAssembler.toModel(car));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long carId,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        carService.deleteCar(carId, userDetails.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<?> patch(@PathVariable("id") Long carId,
                                   @RequestBody @Valid PartialUpdateCar partialUpdateCar,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        carService.updateCar(carId, partialUpdateCar, userDetails.getId());
        Car car = carService.getCar(carId, userDetails.getId());
        return ResponseEntity.ok(carAssembler.toModel(car));
    }
}
