package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArriveAfterDepartureTest {

    @Test
    public void arriveAfterDepartureOnly() {

        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        List<Segment> segments1 = new ArrayList<>();
        Segment seg1 = new Segment(currentLocalDateTime.plusHours(1), currentLocalDateTime.plusHours(3));
        Segment seg2 = new Segment(currentLocalDateTime.plusHours(5), currentLocalDateTime.plusHours(7));
        segments1.add(seg1);
        segments1.add(seg2);
        Flight flightGoodSegments = new Flight(segments1);

        List<Segment> segments2 = new ArrayList<>();
        Segment seg3 = new Segment(currentLocalDateTime.plusHours(1), currentLocalDateTime.plusHours(3));
        Segment seg4 = new Segment(currentLocalDateTime.plusHours(7), currentLocalDateTime.plusHours(5));
        segments2.add(seg3);
        segments2.add(seg4);
        Flight flightBadSegments = new Flight(segments2);

        List<Flight> originalListFlights = new ArrayList<>(List.of(flightGoodSegments, flightBadSegments));
        FlightFilter arriveAfterDeparture = new ArriveAfterDeparture();
        List<Flight> resultListFlights = arriveAfterDeparture.filterFlights(originalListFlights);
        originalListFlights.removeAll(resultListFlights);

        Assertions.assertEquals(flightBadSegments, originalListFlights.get(0));
    }
}