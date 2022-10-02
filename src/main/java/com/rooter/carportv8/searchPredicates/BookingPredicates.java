package com.rooter.carportv8.searchPredicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rooter.carportv8.model.enums.EBookingStatus;
import com.rooter.carportv8.model.QBooking;

public final class BookingPredicates {
    private static final QBooking booking = QBooking.booking;

    public static BooleanExpression hasUserId(Long userId){
        return booking.passengerId.eq(userId);
    }

    public static BooleanExpression hasTripId(Long tripId){
        return booking.tripId.eq(tripId);
    }

    public static BooleanExpression hasId(Long bookingId){
        return booking.id.eq(bookingId);
    }

    public static BooleanExpression bookingIsExistsOrRejected(){
        return booking.status.eq(EBookingStatus.WAITING)
                .or(booking.status.eq(EBookingStatus.APPROVED))
                .or(booking.status.eq(EBookingStatus.REJECTED));
    }

    public static BooleanExpression hasStatus(EBookingStatus status){
        return booking.status.eq(status);
    }
}
