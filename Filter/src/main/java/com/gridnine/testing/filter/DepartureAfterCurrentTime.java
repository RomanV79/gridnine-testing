package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureAfterCurrentTime implements FlightFilter {
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {

        return flights.stream().filter(flight -> !flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
}
