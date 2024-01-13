package com.gridnine.testing;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FlightBuilder {

    public static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                //A flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    public static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
    public static List<Flight> filterFlights() {
        List<Flight> flights = createFlights();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isValidFlight = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(currentDateTime)) {
                    isValidFlight = false;
                    break;
                }
            }
            if (isValidFlight) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
    public static List<Flight> filterFlights1() {
        List<Flight> flights = createFlights();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isValidFlight = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(currentDateTime) || segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    isValidFlight = false;
                    break;
                }
            }
            if (isValidFlight) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    public static List<Flight> filterFlights2() {
        List<Flight> flights = createFlights();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isValidFlight = true;
            Duration totalGroundTime = Duration.ZERO;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                Segment currentSegment = flight.getSegments().get(i);
                Segment nextSegment = flight.getSegments().get(i + 1);
                if (currentSegment.getDepartureDate().isBefore(currentDateTime) ||
                        currentSegment.getArrivalDate().isBefore(currentSegment.getDepartureDate())) {
                    isValidFlight = false;
                    break;
                }
                Duration groundTime = Duration.between(currentSegment.getArrivalDate(),
                        nextSegment.getDepartureDate());
                totalGroundTime = totalGroundTime.plus(groundTime);

                if (totalGroundTime.compareTo(Duration.ofHours(2)) > 0) {
                    isValidFlight = false;
                    break;
                }
            }
            if (isValidFlight) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
}
