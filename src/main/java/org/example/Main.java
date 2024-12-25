package org.example;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        // Выводим все перелёты
        System.out.println("=== Все перелёты ===");
        flights.forEach(flight -> System.out.println("Перелёт: " + flight));

        // Фильтруем и выводим результаты
        System.out.println("\n=== Перелёты после фильтрации по вылету до текущего времени ===");
        List<Flight> filteredFlights1 = FlightFilter.filterDepartureBeforeNow(flights);
        filteredFlights1.forEach(flight -> System.out.println("Перелёт: " + flight));

        System.out.println("\n=== Перелёты после фильтрации по прилёту раньше вылета ===");
        List<Flight> filteredFlights2 = FlightFilter.filterArrivalBeforeDeparture(flights);
        filteredFlights2.forEach(flight -> System.out.println("Перелёт: " + flight));

        System.out.println("\n=== Перелёты после фильтрации по времени на земле более 2 часов ===");
        List<Flight> filteredFlights3 = FlightFilter.filterGroundTimeExceedsTwoHours(flights);
        filteredFlights3.forEach(flight -> System.out.println("Перелёт: " + flight));


        }
}