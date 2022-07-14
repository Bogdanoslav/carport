package com.rooter.carportv8.controller;

import com.rooter.carportv8.dto.PartialUpdateCar;
import com.rooter.carportv8.dto.SaveCar;
import com.rooter.carportv8.hateoas.BrandAssembler;
import com.rooter.carportv8.hateoas.CarAssembler;
import com.rooter.carportv8.model.*;
import com.rooter.carportv8.service.CarInputsDataService;
import com.rooter.carportv8.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarsController {
    private final CarService carService;
    private final CarAssembler carAssembler;
    private final BrandAssembler brandAssembler;
    private final CarInputsDataService carInputsDataService;

    @Autowired
    public CarsController(BrandAssembler brandAssembler, CarService carService, CarAssembler carAssembler, CarInputsDataService carInputsDataService) {
        this.carService = carService;
        this.brandAssembler = brandAssembler;
        this.carAssembler = carAssembler;
        this.carInputsDataService = carInputsDataService;
    }

    @GetMapping("{id}")
    public EntityModel<Car> getById(@PathVariable Long id) {
        Car car = carService.getCar(id);
        return carAssembler.toModel(car);
    }

    @GetMapping
    public CollectionModel<EntityModel<Car>> getAll() {
        List<Car> cars = carService.getCars();
        List<EntityModel<Car>> carsEntities = cars.stream().map(carAssembler::toModel).toList();
        return CollectionModel.of(carsEntities, linkTo(methodOn(CarsController.class).getAll()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid SaveCar saveCar) {
        Car car = carService.saveCar(saveCar);
        car = carService.getCar(car.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody PartialUpdateCar partialUpdateCar) {
        Car car = carService.updateCar(id, partialUpdateCar);
        return ResponseEntity.ok().build();
    }


    @GetMapping("colors")
    public List<CarColor> getColors() {
        return carInputsDataService.getColors();
    }

    @GetMapping("bodyTypes")
    public List<CarBodyType> getBodyTypes() {
        return carInputsDataService.getCarBodyTypes();
    }

    @GetMapping("brands")
    public CollectionModel<EntityModel<CarBrand>> getBrands() {
        List<CarBrand> carBrands = carInputsDataService.getBrands();
        List<EntityModel<CarBrand>> carBrandsEntities = carBrands.stream().map(brandAssembler::toModel).toList();
        return CollectionModel.of(carBrandsEntities, linkTo(methodOn(CarsController.class).getBrands()).withSelfRel());
    }

    @GetMapping("models")
    public List<CarModel> getModels(@RequestParam("brand_id") Long brandId) {
        return carInputsDataService.getCarModels(brandId);
    }


}
