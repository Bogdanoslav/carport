package com.rooter.carportv8.dto.booking;

import com.rooter.carportv8.model.Passenger;
import com.rooter.carportv8.model.enums.EBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBookingDriver {
    private Passenger passenger;
    private EBookingStatus status;
    private LocalDateTime bookedAt;
}
