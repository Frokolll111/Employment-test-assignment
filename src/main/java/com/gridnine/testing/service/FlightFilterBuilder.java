package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilterBuilder {
    //метод для вывода всех сегментов из списка перелетов
    List<Flight> build();
    //метод для получения перелета до текущего момента времени
    FlightFilterBuilder filterDepartureBeforeNow();
    //метод для получения перелета с датой прилёта раньше даты вылета
    FlightFilterBuilder filterArrivalBeforeDeparture();
    //метод для получения перелета с общим временем, проведённым на земле превышающим два часа
    FlightFilterBuilder filterSumTimeOnGroundMoreThanTwoHours();
}
