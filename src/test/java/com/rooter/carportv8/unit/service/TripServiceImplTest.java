//package com.rooter.carportv8.unit.service;
//
//import com.querydsl.core.types.Predicate;
//import com.rooter.carportv8.dto.trip.SaveAddress;
//import com.rooter.carportv8.dto.trip.SaveTrip;
//import com.rooter.carportv8.exceptions.BusinessLogicException;
//import com.rooter.carportv8.mapper.TripMapper;
//import com.rooter.carportv8.model.Address;
//import com.rooter.carportv8.model.Booking;
//import com.rooter.carportv8.model.Car;
//import com.rooter.carportv8.model.Trip;
//import com.rooter.carportv8.model.enums.EBodyType;
//import com.rooter.carportv8.model.enums.EBookingStatus;
//import com.rooter.carportv8.model.enums.EColor;
//import com.rooter.carportv8.model.enums.ETripStatus;
//import com.rooter.carportv8.repo.interfaces.TripRepository;
//import com.rooter.carportv8.searchPredicates.TripPredicates;
//import com.rooter.carportv8.service.CarService;
//import com.rooter.carportv8.serviceImpl.TripServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
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
//public class TripServiceImplTest {
//    @Spy
//    private TripMapper tripMapper = TripMapper.INSTANCE;
//
//    @Mock
//    private TripRepository tripRepository;
//
//    @Mock
//    private CarService carService;
//
//    @InjectMocks
//    private TripServiceImpl tripService;
//
//    private static List<Trip> trips;
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
//        Address.AddressBuilder ab = Address
//                .builder()
//                .latitude(1F)
//                .longitude(1F);
//        Address address1 = ab
//                .id(1L)
//                .city("Lviv")
//                .address("Pushkina 1")
//                .build();
//        Address address2 = ab
//                .id(2L)
//                .city("Kyiv")
//                .address("Pushkina 1")
//                .build();
//        List<Car> cars = Stream.of(Car.builder().id(1L)
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
//        Booking bookingWaiting = Booking
//                .builder()
//                .status(EBookingStatus.WAITING)
//                .build();
//        Booking bookingApproved = Booking
//                .builder()
//                .status(EBookingStatus.APPROVED)
//                .build();
//        Booking bookingRejected = Booking
//                .builder()
//                .status(EBookingStatus.REJECTED)
//                .build();
//        Booking bookingCanceled = Booking
//                .builder()
//                .status(EBookingStatus.CANCELED)
//                .build();
//        List<Booking> bookings = new ArrayList<>(){{
//            add(bookingWaiting);
//            add(bookingApproved);
//            add(bookingRejected);
//            add(bookingCanceled);
//        }};
//        trips = Stream.of(Trip.builder()
//                        .id(1L)
//                        .departureAddress(address1)
//                        .arrivalAddress(address2)
//                        .about("trip 1")
//                        .carId(1L)
//                        .totalSeats(4)
//                        .seatsLeft(1)
//                        .price(BigDecimal.valueOf(250))
//                        .car(cars.get(0))
//                        .status(ETripStatus.SCHEDULED)
//                        .bookings(bookings)
//                        .build(),
//                Trip.builder()
//                        .id(2L)
//                        .departureAddress(address1)
//                        .arrivalAddress(address2)
//                        .about("trip 2")
//                        .carId(1L)
//                        .totalSeats(4)
//                        .seatsLeft(1)
//                        .price(BigDecimal.valueOf(250))
//                        .car(cars.get(0))
//                        .status(ETripStatus.CANCELED)
//                        .build()).collect(Collectors.toList());
//    }
//
//    @Test()
//    public void getById_TripExists_ReturnTrip() {
//        Trip trip = trips.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        tripService.getById(trip.getId());
//        verify(tripRepository).findOne(any(Predicate.class));
//    }
//
//
//    @Test()
//    public void getById_TripNOTExists_ThrowNoSuchElementException() {
//        Assertions.assertThrows(NoSuchElementException.class, () -> tripService.getById(1L), "NoSuchElementException was expected");
//    }
//
//    @Test()
//    public void save_ValidSaveTrip_ReturnTrip() {
//        Trip trip = trips.get(0);
//        Long userId = 1L;
//        SaveTrip saveTrip = SaveTrip.builder()
//                .departureAddress(SaveAddress
//                        .builder()
//                        .city(trip.getDepartureAddress().getCity())
//                        .address(trip.getDepartureAddress().getAddress())
//                        .latitude(trip.getDepartureAddress().getLatitude())
//                        .longitude(trip.getDepartureAddress().getLongitude())
//                        .build())
//                .arrivalAddress(SaveAddress
//                        .builder()
//                        .city(trip.getArrivalAddress().getCity())
//                        .address(trip.getArrivalAddress().getAddress())
//                        .latitude(trip.getArrivalAddress().getLatitude())
//                        .longitude(trip.getArrivalAddress().getLongitude())
//                        .build())
//                .about(trip.getAbout())
//                .departureTime(trip.getDepartureTime())
//                .arrivalTime(trip.getArrivalTime())
//                .totalSeats(trip.getTotalSeats())
//                .instantBooking(trip.getInstantBooking())
//                .carId(trip.getCarId())
//                .build();
//        given(tripRepository.save(any(Trip.class))).willReturn(trip);
//        given(carService.getCar(saveTrip.getCarId(), userId)).willReturn(cars.get(0));
//        Trip newTrip = tripService.save(saveTrip, userId);
//        verify(tripRepository).save(any(Trip.class));
//        Assertions.assertEquals(trip.getDepartureAddress(), newTrip.getDepartureAddress());
//        Assertions.assertEquals(trip.getArrivalAddress(), newTrip.getArrivalAddress());
//        Assertions.assertEquals(trip.getDepartureTime(), newTrip.getDepartureTime());
//        Assertions.assertEquals(trip.getArrivalTime(), newTrip.getArrivalTime());
//        Assertions.assertEquals(trip.getCarId(), newTrip.getCarId());
//        Assertions.assertEquals(trip.getAbout(), newTrip.getAbout());
//        Assertions.assertEquals(trip.getStatus(), newTrip.getStatus());
//        Assertions.assertEquals(trip, newTrip);
//    }
//
//    @Test()
//    public void cancel_TripHasStatusScheduled_TripAndBookingsAreCanceled() {
//        Trip trip = trips.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        Trip canceledTrip = tripService.cancel(trip.getId(), trip.getCar().getOwnerId());
//        Assertions.assertEquals(ETripStatus.CANCELED, canceledTrip.getStatus());
//        for (Booking b:
//             canceledTrip.getBookings()) {
//            Assertions.assertEquals(EBookingStatus.CANCELED,b.getStatus());
//        }
//    }
//
//    @ParameterizedTest()
//    @ArgumentsSource(UnscheduledTripsArgumentsProvider.class)
//    public void cancel_TripNotScheduled_ThrowBusinessLogicException(Trip trip) {
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, ()->tripService.cancel(trip.getId(), trip.getCar().getOwnerId()));
//        Assertions.assertEquals("You cannot cancel trip if it's not scheduled.",exception.getLocalizedMessage());
//        verify(tripRepository, times(0)).save(any(Trip.class));
//
//    }
//
//
//    static class UnscheduledTripsArgumentsProvider implements ArgumentsProvider {
//        Address address1 = Address.builder()
//                .id(1L)
//                .city("Lviv")
//                .address("Pushkina 1")
//                .latitude(1F)
//                .longitude(1F)
//                .build();
//        Address address2 = Address.builder()
//                .id(2L)
//                .city("Kyiv")
//                .address("Pushkina 1")
//                .latitude(1F)
//                .longitude(1F)
//                .build();
//        Address address3 = Address.builder()
//                .id(3L)
//                .city("Odesa")
//                .address("Pushkina 2")
//                .latitude(2F)
//                .longitude(2F)
//                .build();
//        Address address4 = Address.builder()
//                .id(4L)
//                .city("Kharkiv")
//                .address("Pushkina 2")
//                .latitude(2F)
//                .longitude(2F)
//                .build();
//        List<Car> cars = Stream.of(Car.builder().id(1L)
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
//        Trip tripCanceled = Trip.builder()
//                .id(1L)
//                .departureAddress(address1)
//                .arrivalAddress(address2)
//                .about("trip 1")
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(cars.get(0))
//                .status(ETripStatus.CANCELED)
//                .build();
//        Trip tripInProgress = Trip.builder()
//                .id(1L)
//                .departureAddress(address1)
//                .arrivalAddress(address2)
//                .about("trip 1")
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(cars.get(0))
//                .status(ETripStatus.IN_PROGRESS)
//                .build();
//        Trip tripCompleted = Trip.builder()
//                .id(1L)
//                .departureAddress(address1)
//                .arrivalAddress(address2)
//                .about("trip 1")
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(cars.get(0))
//                .status(ETripStatus.COMPLETED)
//                .build();
//
//        @Override
//        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
//            return Stream.of(
//                    Arguments.of(tripCompleted),
//                    Arguments.of(tripInProgress),
//                    Arguments.of(tripCanceled)
//            );
//        }
//    }
//}
