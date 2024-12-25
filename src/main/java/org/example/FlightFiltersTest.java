package org.example;


import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFiltersTest {
    @Test
    public void testFilterDepartureBeforeNow() {
        // Создаём тестовые данные
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = new Flight(Arrays.asList(new Segment(now.plusHours(1), now.plusHours(2)))); // Нормальный перелёт
        Flight flight2 = new Flight(Arrays.asList(new Segment(now.minusHours(1), now.plusHours(1)))); // Вылет в прошлом
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Применяем фильтр
        List<Flight> filteredFlights = FlightFilter.filterDepartureBeforeNow(flights);

        // Проверяем результат
        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }

    @Test
    public void testFilterArrivalBeforeDeparture() {
        // Создаём тестовые данные
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = new Flight(Arrays.asList(new Segment(now.plusHours(1), now.plusHours(2)))); // Нормальный перелёт
        Flight flight2 = new Flight(Arrays.asList(new Segment(now.plusHours(2), now.plusHours(1)))); // Прилёт раньше вылета
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Применяем фильтр
        List<Flight> filteredFlights = FlightFilter.filterArrivalBeforeDeparture(flights);

        // Проверяем результат
        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }

    @Test
    public void testFilterGroundTimeExceedsTwoHours() {
        // Создаём тестовые данные
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = new Flight(Arrays.asList(
                new Segment(now.plusHours(1), now.plusHours(2)),
                new Segment(now.plusHours(3), now.plusHours(4)) // Время на земле: 1 час
        ));
        Flight flight2 = new Flight(Arrays.asList(
                new Segment(now.plusHours(1), now.plusHours(2)),
                new Segment(now.plusHours(5), now.plusHours(6)) // Время на земле: 3 часа
        ));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Применяем фильтр
        List<Flight> filteredFlights = FlightFilter.filterGroundTimeExceedsTwoHours(flights);

        // Проверяем результат
        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }
}
