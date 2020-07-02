package htec.task.service.implementation;

import htec.task.mapper.route.RouteFileMapper;
import htec.task.model.Airport;
import htec.task.model.Route;
import htec.task.model.file.RouteFile;
import htec.task.repository.RouteRepository;
import htec.task.service.AirportService;
import htec.task.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AirportService airportService;

    @Autowired
    private RouteFileMapper routeFileMapper;

    public void createRoutes(MultipartFile file) {
        List<Airport> airports = airportService.findAll();
        Map<Long, Airport> airportIdMap = airportService.createAirportsIdKeyMap(airports);
        Map<String, Airport> airportIATAMap = airportService.createAirportsIATAKeyMap(airports);
        Map<String, Airport> airportICAOMap = airportService.createAirportsICAOKeyMap(airports);
        List<Route> newRoutes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = reader.readLine();
            while (line != null) {
                RouteFile routeFile = new RouteFile(line);
                Airport sourceAirport = findAirportInMaps(airportIdMap, airportIATAMap, airportICAOMap,
                        routeFile.getSourceAirportId(), routeFile.getSourceAirport());
                Airport destinationAirport = findAirportInMaps(airportIdMap, airportIATAMap, airportICAOMap,
                        routeFile.getDestinationAirportId(), routeFile.getDestinationAirport());
                if(sourceAirport != null && destinationAirport != null){
                    Route newRoute = routeFileMapper.toEntity(routeFile, sourceAirport, destinationAirport);
                    newRoutes.add(newRoute);
                }
                line = reader.readLine();
            }
            routeRepository.saveAll(newRoutes);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private Airport findAirportInMaps(Map<Long, Airport> airportIdMap,
                                     Map<String, Airport> airportIATAMap,
                                     Map<String, Airport> airportICAOMap,
                                     Long id, String airportCode){
        Airport airport = null;
        if (airportIdMap.containsKey(id)) {
            airport = airportIdMap.get(id);
        } else if (airportIATAMap.containsKey(airportCode)) {
            airport = airportIATAMap.get(airportCode);
        } else if (airportICAOMap.containsKey(airportCode)) {
            airport = airportICAOMap.get(airportCode);
        }
        return airport;
    }
}
