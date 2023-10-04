package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TotalGroundTimeExceedsTwoHoursFilter implements FlightFilter {

    private final long GROUND_TIME_LIMIT_SECONDS = 7200;

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {
            long totalGroundTime = 0;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                totalGroundTime += getDifferenceInSec(flight.getSegments().get(i).getArrivalDate(), flight.getSegments().get(i + 1).getDepartureDate());
            }
            if (totalGroundTime <= GROUND_TIME_LIMIT_SECONDS) {
                result.add(flight);
            }
        }

        return result;
    }

    private long getDifferenceInSec(LocalDateTime from, LocalDateTime to) {
        return ChronoUnit.SECONDS.between(from, to);
    }
}
