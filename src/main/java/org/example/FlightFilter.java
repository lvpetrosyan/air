package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class FlightFilter {

    // Исключить перелёты с вылетом до текущего времени
    public static List<Flight> filterDepartureBeforeNow(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(now)))
                .collect(Collectors.toList());
    }

    // Исключить перелёты с прилётом раньше вылета
    public static List<Flight> filterArrivalBeforeDeparture(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    // Исключить перелёты, где общее время на земле превышает два часа
    public static List<Flight> filterGroundTimeExceedsTwoHours(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() < 2) return true; // Если сегмент один, время на земле отсутствует

                    long totalGroundTime = 0; // Общее время на земле в часах
                    for (int i = 1; i < segments.size(); i++) {
                        LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
                        LocalDateTime currentDeparture = segments.get(i).getDepartureDate();
                        totalGroundTime += Duration.between(previousArrival, currentDeparture).toHours();
                    }
                    return totalGroundTime <= 2; // Проверяем, что время на земле не превышает 2 часа
                })
                .collect(Collectors.toList());
    }
}