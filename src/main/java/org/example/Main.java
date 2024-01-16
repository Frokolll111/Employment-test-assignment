package org.example;


import com.gridnine.testing.Dao.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FlightFilterBuilderImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> flightsDepartureBeforeNow = new FlightFilterBuilderImpl(flights)
                .filterDepartureBeforeNow()
                .build();
        List<Flight> flightsArrivalBeforeDeparture = new FlightFilterBuilderImpl(flights)
                .filterArrivalBeforeDeparture()
                .build();
        List<Flight> flightsTimeOnGroundMoreThanTwoHours = new FlightFilterBuilderImpl(flights)
                .filterSumTimeOnGroundMoreThanTwoHours()
                .build();

        System.out.println("Нефильтрованные полёты:\n" + flights);
        System.out.println("Вылет до текущего момента времени:\n" + flightsDepartureBeforeNow);
        System.out.println("Сегменты с датой прилёта раньше даты вылета:\n" + flightsArrivalBeforeDeparture);
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа:\n" + flightsTimeOnGroundMoreThanTwoHours);
    }
}
