package com.rooter.carportv8.dto.booking;

import com.rooter.carportv8.model.Driver;
import com.rooter.carportv8.model.Trip;
import com.rooter.carportv8.model.enums.EBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetBookingPassenger {
    private Trip trip;
    private Driver driver;
    private EBookingStatus status;
    private LocalDateTime bookedAt;
}
