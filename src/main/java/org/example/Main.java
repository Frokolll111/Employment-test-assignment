package org.example;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;

import java.util.List;

import static com.gridnine.testing.FlightBuilder.*;


public class Main {
    public static void main(String[] args) {
        System.out.println(FlightBuilder.createFlights());
        List<Flight> filteredFlights = filterFlights();
        System.out.println("Фильтрованные вылеты до текущего момента времени: ");
        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                System.out.println("Вылет: " + segment.getDepartureDate());
            }
        }
        List<Flight> filteredFlights1 = filterFlights1();
        System.out.println("Фильтрованные cегменты с датой прилёта раньше даты вылета:");
        for (Flight flight : filteredFlights1) {
            for (Segment segment : flight.getSegments()) {
                System.out.println("Прилёт: " + segment.getArrivalDate());
            }
        }
        List<Flight> filteredFlights2 = filterFlights2();
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа: ");
        for (Flight flight : filteredFlights2) {
            for (Segment segment : flight.getSegments()) {
                System.out.println("Вылет: " + segment.getDepartureDate() + " Прилёт: " + segment.getArrivalDate());
            }
        }
    }
}
