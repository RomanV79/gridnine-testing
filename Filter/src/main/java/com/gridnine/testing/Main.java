package com.gridnine.testing;

import com.gridnine.testing.filter.ArriveAfterDeparture;
import com.gridnine.testing.filter.DepartureAfterCurrentTime;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.TotalGroundTimeExceedsTwoHoursFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;

import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        List<Flight> originalFlightList = FlightBuilder.createFlights();

        FlightFilter departureAfterCurrentTime = new DepartureAfterCurrentTime();
        FlightFilter arriveAfterDeparture = new ArriveAfterDeparture();
        FlightFilter totalGroundTimeExceedsTwoHoursFilter = new TotalGroundTimeExceedsTwoHoursFilter();

        List<Flight> resultFlights;

        resultFlights = departureAfterCurrentTime.filterFlights(originalFlightList);
        System.out.println("Actual flights departure after 'now'");
        resultFlights.forEach(System.out::println);

        System.out.println("================================================");

        resultFlights = arriveAfterDeparture.filterFlights(originalFlightList);
        System.out.println("Excluded flights with bad segment, where departure after arriving");
        resultFlights.forEach(System.out::println);

        System.out.println("================================================");

        resultFlights = totalGroundTimeExceedsTwoHoursFilter.filterFlights(originalFlightList);
        System.out.println("Excluded flight with total ground time more then 2 hours");
        resultFlights.forEach(System.out::println);
    }
}
