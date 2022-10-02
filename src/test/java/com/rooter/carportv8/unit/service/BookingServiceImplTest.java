//package com.rooter.carportv8.unit.service;
//
//import com.querydsl.core.types.Predicate;
//import com.rooter.carportv8.exceptions.BusinessLogicException;
//import com.rooter.carportv8.model.*;
//import com.rooter.carportv8.model.enums.EBodyType;
//import com.rooter.carportv8.model.enums.EBookingStatus;
//import com.rooter.carportv8.model.enums.EColor;
//import com.rooter.carportv8.model.enums.ETripStatus;
//import com.rooter.carportv8.repo.interfaces.BookingRepository;
//import com.rooter.carportv8.repo.interfaces.TripRepository;
//import com.rooter.carportv8.searchPredicates.BookingPredicates;
//import com.rooter.carportv8.searchPredicates.TripPredicates;
//import com.rooter.carportv8.serviceImpl.BookingServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.access.AccessDeniedException;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class BookingServiceImplTest {
//
//
//    @Mock
//    TripRepository tripRepository;
//
//    @Mock
//    BookingRepository bookingRepository;
//
//    @InjectMocks
//    BookingServiceImpl bookingService;
//
//    private List<Trip> trips;
//    private List<User> users;
//    private List<Booking> bookings;
//
//    @BeforeEach
//    private void setUp(){
////        USERS
//        User user1 = User
//                .builder()
//                .id(1L)
//                .username("test_user_1")
//                .build();
//        User user2 = User
//                .builder()
//                .id(2L)
//                .username("test_user_2")
//                .build();
//        users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
////        CARS
//        Car car1 = Car.builder().id(1L)
//                .color(EColor.BLUE)
//                .bodyType(EBodyType.COMPACT)
//                .modelId(1L)
//                .ownerId(user1.getId())
//                .build();
////        ADDRESSES
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
////        TRIPS
//        Trip.TripBuilder tripBuilder = Trip.builder()
//                .departureAddress(address1)
//                .arrivalAddress(address2)
//                .about("trip 1")
//                .price(BigDecimal.valueOf(250));
//        Trip tripScheduledFreeSeatsInstant = tripBuilder
//                .id(1L)
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(car1)
//                .status(ETripStatus.SCHEDULED)
//                .instantBooking(true)
//                .build();
//        Trip tripFreeSeatsNotInstant = tripBuilder
//                .id(1L)
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(car1)
//                .status(ETripStatus.SCHEDULED)
//                .instantBooking(false)
//                .build();
//        Trip tripNoSeats = tripBuilder
//                .id(1L)
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(0)
//                .price(BigDecimal.valueOf(250))
//                .car(car1)
//                .status(ETripStatus.SCHEDULED)
//                .build();
//        Trip tripCanceled = tripBuilder
//                .id(1L)
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(car1)
//                .status(ETripStatus.CANCELED)
//                .build();
//        Trip tripCompleted = tripBuilder
//                .id(1L)
//                .carId(1L)
//                .totalSeats(4)
//                .seatsLeft(1)
//                .price(BigDecimal.valueOf(250))
//                .car(car1)
//                .status(ETripStatus.COMPLETED)
//                .build();
//        trips = new ArrayList<>();
//        trips.add(tripScheduledFreeSeatsInstant);
//        trips.add(tripFreeSeatsNotInstant);
//        trips.add(tripNoSeats);
//        trips.add(tripCanceled);
//        trips.add(tripCompleted);
//
////        BOOKING
//        Booking bookingWaitingTripScheduled = Booking
//                .builder()
//                .id(1L)
//                .status(EBookingStatus.WAITING)
//                .user(user1)
//                .userId(user1.getId())
//                .tripId(tripScheduledFreeSeatsInstant.getId())
//                .trip(tripScheduledFreeSeatsInstant)
//                .build();
//        Booking bookingApprovedTripScheduled = Booking
//                .builder()
//                .id(2L)
//                .status(EBookingStatus.APPROVED)
//                .user(user1)
//                .userId(user1.getId())
//                .tripId(tripScheduledFreeSeatsInstant.getId())
//                .trip(tripScheduledFreeSeatsInstant)
//                .build();
//        Booking bookingRejectedTripScheduled = Booking
//                .builder()
//                .id(3L)
//                .status(EBookingStatus.REJECTED)
//                .user(user2)
//                .userId(user2.getId())
//                .tripId(tripScheduledFreeSeatsInstant.getId())
//                .trip(tripScheduledFreeSeatsInstant)
//                .build();
//        Booking bookingTripCompleted = Booking
//                .builder()
//                .id(3L)
//                .status(EBookingStatus.APPROVED)
//                .user(user2)
//                .userId(user2.getId())
//                .tripId(tripCompleted.getId())
//                .trip(tripCompleted)
//                .build();
//        Booking bookingTripCanceled = Booking
//                .builder()
//                .id(3L)
//                .status(EBookingStatus.WAITING)
//                .user(user2)
//                .userId(user2.getId())
//                .tripId(tripCompleted.getId())
//                .trip(tripCompleted)
//                .build();
//
//        bookings = new ArrayList<>();
//        bookings.add(bookingWaitingTripScheduled);
//        bookings.add(bookingApprovedTripScheduled);
//        bookings.add(bookingRejectedTripScheduled);
//        bookings.add(bookingTripCompleted);
//        bookings.add(bookingTripCanceled);
//
//    }
//
//
//
//    @Test
//    public void book_TripScheduledFreeSeatsInstant_returnBookingApproved() {
//        Trip trip = trips.get(0);
//        User user = users.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        given(bookingRepository.findOne(
//                BookingPredicates.hasUserId(user.getId())
//                        .and(BookingPredicates.hasTripId(trip.getId()))))
//                .willReturn(Optional.empty());
//        given(bookingRepository.save(any(Booking.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
//        Booking booking = bookingService.book(trip.getId(), user.getId());
//        Assertions.assertEquals(user.getId(),booking.getUserId());
//        Assertions.assertEquals(trip.getId(),booking.getTripId());
//        Assertions.assertEquals(EBookingStatus.APPROVED,booking.getStatus());
//    }
//
//    @Test
//    public void book_TripScheduledFreeSeatsNotInstant_returnBookingWaiting() {
//        Trip trip = trips.get(1);
//        User user = users.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        given(bookingRepository.findOne(
//                BookingPredicates.hasUserId(user.getId())
//                        .and(BookingPredicates.hasTripId(trip.getId()))))
//                .willReturn(Optional.empty());
//        given(bookingRepository.save(any(Booking.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
//        Booking booking = bookingService.book(trip.getId(), user.getId());
//        Assertions.assertEquals(user.getId(),booking.getUserId());
//        Assertions.assertEquals(trip.getId(),booking.getTripId());
//        Assertions.assertEquals(EBookingStatus.WAITING,booking.getStatus());
//        verify(bookingRepository, times(1)).save(any(Booking.class));
//    }
//
//    @Test
//    public void book_NoFreeSeats_throwBusinessLogicException() {
//        Trip trip = trips.get(2);
//        User user = users.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        Throwable throwable = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        Assertions.assertEquals("No free seats available.", throwable.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//
//    @Test
//    public void book_TripCanceled_ThrowBusinessLogicException() {
//        Trip trip = trips.get(3);
//        User user = users.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        Throwable throwable = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        Assertions.assertEquals("You cannot book trip if it's not scheduled.", throwable.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void book_TripCompleted_throwBusinessLogicException() {
//        Trip trip = trips.get(4);
//        User user = users.get(0);
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        Throwable throwable = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        Assertions.assertEquals("You cannot book trip if it's not scheduled.", throwable.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void book_TripNotExists_throwNoSuchElementException() {
//        Trip trip = trips.get(3);
//        User user = users.get(0);
//        Assertions.assertThrows(NoSuchElementException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//
//    @Test
//    public void book_bookingWaiting_throwBusinessLogicException() {
//        Trip trip = trips.get(0);
//        User user = users.get(0);
//        Optional<Booking> booking = Optional.of(bookings.get(0));
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        given(bookingRepository.findOne(
//                BookingPredicates.hasUserId(user.getId())
//                        .and(BookingPredicates.hasTripId(trip.getId()))))
//                .willReturn(booking);
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        Assertions.assertEquals("You cannot book this trip. You have already booked this trip.",exception.getLocalizedMessage());        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void book_bookingAccepted_throwBusinessLogicException() {
//        Trip trip = trips.get(0);
//        User user = users.get(0);
//        Optional<Booking> booking = Optional.of(bookings.get(1));
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        given(bookingRepository.findOne(
//                BookingPredicates.hasUserId(user.getId())
//                        .and(BookingPredicates.hasTripId(trip.getId()))))
//                .willReturn(booking);
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        Assertions.assertEquals("You cannot book this trip. You have already booked this trip.",exception.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//    @Test
//    public void book_UserHasBeenRejected_throwBusinessLogicException() {
//        Trip trip = trips.get(0);
//        User user = users.get(0);
//        Optional<Booking> booking = Optional.of(bookings.get(2));
//        given(tripRepository.findOne(TripPredicates.hasId(trip.getId()))).willReturn(Optional.of(trip));
//        given(bookingRepository.findOne(
//                BookingPredicates.hasUserId(user.getId())
//                        .and(BookingPredicates.hasTripId(trip.getId()))))
//                .willReturn(booking);
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.book(trip.getId(), user.getId()));
//        Assertions.assertEquals("You cannot book this trip. You have rejected by the driver.",exception.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void cancel_BookingWaitingTripScheduled_TripSeatsLeftIncrementsByOne() {
//        Booking booking = bookings.get(0);
//        Integer seatsLeftBefore = booking.getTrip().getSeatsLeft();
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        bookingService.cancel(booking.getId(), booking.getUserId());
//        Assertions.assertEquals(seatsLeftBefore+1, booking.getTrip().getSeatsLeft());
//        verify(bookingRepository, times(1)).delete(any(Predicate.class));
//        verify(tripRepository, times(1)).save(any(Trip.class));
//    }
//
//    @Test
//    public void cancel_BookingApprovedTripScheduled_throwBusinessLogicException() {
//        Booking booking = bookings.get(1);
//        Integer seatsLeftBefore = booking.getTrip().getSeatsLeft();
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        bookingService.cancel(booking.getId(), booking.getUserId());
//        Assertions.assertEquals(seatsLeftBefore+1, booking.getTrip().getSeatsLeft());
//        verify(bookingRepository, times(1)).delete(any(Predicate.class));
//        verify(tripRepository, times(1)).save(any(Trip.class));
//    }
//
//    @Test
//    public void cancel_BookingRejected_throwBusinessLogicException() {
//        Booking booking = bookings.get(2);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.cancel(booking.getId(), booking.getUserId()));
//        Assertions.assertEquals("You cannot cancel this booking. Your booking has been rejected by the driver.",exception.getLocalizedMessage());
//    }
//
//    @Test
//    public void cancel_TripCompleted_throwBusinessLogicException() {
//        Booking booking = bookings.get(3);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () -> bookingService.cancel(booking.getId(), booking.getUserId()));
//        Assertions.assertEquals("You cannot cancel this booking. Trip is completed.",exception.getLocalizedMessage());
//    }
//
//    @Test
//    public void cancel_BookingNotExists_throwNoSuchElementException() {
//        Booking booking = bookings.get(3);
//        Assertions.assertThrows(NoSuchElementException.class, () -> bookingService.cancel(booking.getId(), booking.getUserId()));
//    }
//
//    @Test
//    public void cancel_AnotherUserBooking_throwAccessDeniedException() {
//        Booking booking = bookings.get(3);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Assertions.assertThrows(AccessDeniedException.class, () -> bookingService.cancel(booking.getId(), booking.getUserId()+1));
//    }
//
//    @Test
//    public void reject_BookingWaiting_ReturnRejectedBooking() {
//        Booking booking = bookings.get(0);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        given(bookingRepository.save(any(Booking.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
//        Booking bookingRejected = bookingService.reject(booking.getId(),booking.getTrip().getCar().getOwnerId());
//        Assertions.assertEquals(EBookingStatus.REJECTED,bookingRejected.getStatus());
//    }
//
//
//    @Test
//    public void reject_BookingApproved_throwBusinessLogicException() {
//        Booking booking = bookings.get(1);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () ->bookingService.reject(booking.getId(),booking.getTrip().getCar().getOwnerId()));
//        Assertions.assertEquals("You cannot reject this booking. Booking must be in a waiting status to reject.",exception.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void reject_TripCanceled_throwBusinessLogicException() {
//        Booking booking = bookings.get(4);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () ->bookingService.reject(booking.getId(),booking.getTrip().getCar().getOwnerId()));
//        Assertions.assertEquals("You cannot reject this booking. Trip must be scheduled to reject booking.",exception.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void reject_TripCompleted_throwBusinessLogicException() {
//        Booking booking = bookings.get(3);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () ->bookingService.reject(booking.getId(),booking.getTrip().getCar().getOwnerId()));
//        Assertions.assertEquals("You cannot reject this booking. Trip must be scheduled to reject booking.",exception.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void reject_BookingNotExists_throwNoSuchElementException() {
//        Booking booking = bookings.get(3);
//        Assertions.assertThrows(NoSuchElementException.class, () -> bookingService.reject(booking.getId(),booking.getTrip().getCar().getOwnerId()));
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//    @Test
//    public void reject_BookingForAnotherDriversTrip_throwBusinessLogicException() {
//        Booking booking = bookings.get(3);
//        given(bookingRepository.findOne(BookingPredicates.hasId(booking.getId()))).willReturn(Optional.of(booking));
//        Throwable exception = Assertions.assertThrows(BusinessLogicException.class, () ->bookingService.reject(booking.getId(),booking.getTrip().getCar().getOwnerId()+1));
//        Assertions.assertEquals("You cannot reject this booking. This booking belongs to another drivers trip.",exception.getLocalizedMessage());
//        verify(bookingRepository, times(0)).save(any(Booking.class));
//    }
//
//}
