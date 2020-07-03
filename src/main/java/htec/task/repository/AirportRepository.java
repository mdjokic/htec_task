package htec.task.repository;

import htec.task.model.Airport;
import htec.task.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAirportByCity(City city);
}
