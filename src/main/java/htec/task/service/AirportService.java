package htec.task.service;

import htec.task.model.Airport;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AirportService {

    void createAirports(MultipartFile file);

    List<Airport> findAll();

    Map<Long, Airport> createAirportsIdKeyMap(List<Airport> airports);

    Map<String, Airport> createAirportsIATAKeyMap(List<Airport> airports);

    Map<String, Airport> createAirportsICAOKeyMap(List<Airport> airports);

}
