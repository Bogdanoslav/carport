//package com.rooter.carportv8.unit.service;
//
//import com.querydsl.core.types.Predicate;
//import com.rooter.carportv8.dto.car.PartialUpdateCar;
//import com.rooter.carportv8.dto.car.SaveCar;
//import com.rooter.carportv8.exceptions.BusinessLogicException;
//import com.rooter.carportv8.mapper.CarMapper;
//import com.rooter.carportv8.model.*;
//import com.rooter.carportv8.model.enums.EBodyType;
//import com.rooter.carportv8.model.enums.ECarStatus;
//import com.rooter.carportv8.model.enums.EColor;
//import com.rooter.carportv8.model.enums.ETripStatus;
//import com.rooter.carportv8.repo.interfaces.CarModelRepository;
//import com.rooter.carportv8.repo.interfaces.CarRepository;
//import com.rooter.carportv8.repo.interfaces.TripRepository;
//import com.rooter.carportv8.searchPredicates.CarPredicates;
//import com.rooter.carportv8.searchPredicates.TripPredicates;
//import com.rooter.carportv8.serviceImpl.CarServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mapstruct.factory.Mappers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.access.AccessDeniedException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class CarServiceImplTest {
//
//    @Spy
//    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);
//
//    @Mock
//    private CarRepository carRepository;
//
//    @Mock
//    private CarModelRepository carModelRepository;
//
//    @Mock
//    private TripRepository tripRepository;
//
//    @InjectMocks
//    private CarServiceImpl carService;
//
//    private static List<Car> cars;
//
//    @BeforeAll
//    public static void createTestData() {
//        cars = Stream.of(Car.builder().id(1L)
//                        .color(EColor.BLUE)
//                        .bodyType(EBodyType.COMPACT)
//                        .modelId(1L)
//                        .ownerId(1L)
//                        .build(),
//                Car.builder().id(2L)
//                        .color(EColor.RED)
//                        .bodyType(EBodyType.SEDAN)
//                        .modelId(2L)
//                        .ownerId(1L)
//                        .build()).collect(Collectors.toList());
//    }
//
//    @Test()
//    public void getCar_UserIsCarOwner_returnCar() {
//        Car car = cars.get(0);
//        given(carRepository.findOne(CarPredicates.hasId(1L).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        Assertions.assertEquals(car.getId(), carService.getCar(1L, 1L).getId());
//    }
//
//
//    @Test()
//    public void getCar_CarNotExists_throwNoSuchElementException() {
//        NoSuchElementException thrown = Assertions
//                .assertThrows(NoSuchElementException.class, () -> carService.getCar(1L, 2L), "NoSuchElementException was expected");
//        Assertions.assertEquals("No record of Car could be found with id 1.", thrown.getMessage());
//    }
//
//    @Test()
//    public void getCar_UserIsNotCarOwner_throwAccessDeniedException() {
//        Car car = cars.get(0);
//        given(carRepository.findOne(CarPredicates.hasId(1L).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        Assertions.assertThrows(AccessDeniedException.class, () -> carService.getCar(car.getId(), car.getOwnerId()+1));
//
//    }
//
//    @Test
//    public void saveCar_ValidSaveCarDto_ReturnCar() {
//        Long ownerId = 1L;
//        Long modelId = 1L;
//        SaveCar saveCar = new SaveCar(modelId, EColor.RED, EBodyType.SEDAN);
//        given(carRepository.save(any(Car.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
//        given(carModelRepository.existsById(modelId)).willReturn(true);
//        Car newCar = carService.saveCar(saveCar, ownerId);
//        Assertions.assertEquals(saveCar.getColor(), newCar.getColor());
//        Assertions.assertEquals(saveCar.getBodyType(), newCar.getBodyType());
//        Assertions.assertEquals(saveCar.getModelId(), newCar.getModelId());
//        verify(carRepository).save(any(Car.class));
//    }
//
//    @Test
//    public void saveCar_NOT_ValidModelId_throwNoSuchElementException() {
//        Long ownerId = 1L;
//        Long modelId = 1L;
//        SaveCar saveCar = new SaveCar(modelId, EColor.RED, EBodyType.SEDAN);
//        given(carModelRepository.existsById(modelId)).willReturn(false);
//        Assertions.assertThrows(NoSuchElementException.class, () -> carService.saveCar(saveCar, ownerId));
//    }
//
//    @Test
//    public void deleteCar_ValidData_CarStatusArchived() {
//        Car car = cars.get(0);
//        given(carRepository.findOne(CarPredicates.hasId(car.getId()).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        given(tripRepository.findAll(TripPredicates.hasCarId(car.getId())
//                .and((TripPredicates.hasStatus(ETripStatus.SCHEDULED))
//                        .or(TripPredicates.hasStatus(ETripStatus.IN_PROGRESS))))).willReturn(new ArrayList<Trip>());
//        carService.deleteCar(car.getId(), car.getOwnerId());
//        Assertions.assertEquals(ECarStatus.ARCHIVED, car.getStatus());
//        verify(carRepository, times(1)).save(any(Car.class));
//    }
//
//    @Test
//    public void deleteCar_CarAttachedToActiveTrips_BusinessLogicException() {
//        Car car = cars.get(0);
//        given(carRepository.findOne(CarPredicates.hasId(car.getId()).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        given(tripRepository.findAll(any(Predicate.class)))
//                .willReturn(new ArrayList<Trip>(){{add(new Trip());}});
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, ()->carService.deleteCar(car.getId(), car.getOwnerId()));
//        Assertions.assertEquals("You can not delete a car that is attached to an active Trip.",exception.getLocalizedMessage());
//        verify(carRepository, times(0)).save(any(Car.class));
//    }
//
//    @Test
//    public void deleteCar_NotOwner_ThrowAccessDeniedException() {
//        Car car = cars.get(0);
//        given(carRepository.findOne(CarPredicates.hasId(car.getId()).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        Assertions.assertThrows(AccessDeniedException.class, () -> carService.deleteCar(car.getId(), car.getOwnerId()+1));
//        verify(carRepository, times(0)).save(any(Car.class));
//    }
////
//    @Test
//    public void deleteCar_IdNotExists_ThrowNoSuchElementException() {
//        Car car = cars.get(0);
//        Assertions.assertThrows(NoSuchElementException.class, () -> carService.deleteCar(car.getId(), car.getOwnerId()));
//        verify(carRepository, times(0)).save(any(Car.class));
//    }
//
//    @Test
//    public void updateCar_ValidUpdateCarData_ReturnCar() {
//        Car car = cars.get(0);
//        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
//        given(carRepository.save(any(Car.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
//        given(carRepository.findOne(CarPredicates.hasId(car.getId()).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        Car updatedCar = carService.updateCar(car.getId(), partialUpdateCar, car.getOwnerId());
//        Assertions.assertEquals(partialUpdateCar.getColor(), updatedCar.getColor());
//        verify(carRepository).save(any(Car.class));
//    }
//
//    @Test
//    public void updateCar_UserNotOwner_ThrowAccessDeniedException() {
//        Car car = cars.get(0);
//        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
//        given(carRepository.findOne(CarPredicates.hasId(car.getId()).and(CarPredicates.hasStatus(ECarStatus.ACTIVE)))).willReturn(Optional.of(car));
//        Assertions.assertThrows(AccessDeniedException.class, () -> carService.updateCar(car.getId(), partialUpdateCar, car.getOwnerId()+1));
//        verify(carRepository, times(0)).save(any(Car.class));
//    }
//
//    @Test
//    public void updateCar_IdNotExists_ThrowNoSuchElementException() {
//        Car car = cars.get(0);
//        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
//        Assertions.assertThrows(NoSuchElementException.class, () -> carService.updateCar(car.getId(), partialUpdateCar, car.getOwnerId()));
//        verify(carRepository, times(0)).save(any(Car.class));
//    }
//}
