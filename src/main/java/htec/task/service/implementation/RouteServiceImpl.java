package htec.task.service.implementation;

import htec.task.mapper.route.RouteFileMapper;
import htec.task.model.Airport;
import htec.task.model.City;
import htec.task.model.Route;
import htec.task.model.file.RouteFile;
import htec.task.repository.RouteRepository;
import htec.task.service.AirportService;
import htec.task.service.CityService;
import htec.task.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AirportService airportService;

    @Autowired
    private CityService cityService;

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

    public List<Route> findCheapestRoute(String sourceCityName, String sourceCountry,
                                  String destinationCityName, String destinationCountry){

        City sourceCity = cityService.findByNameAndCountry(sourceCityName, sourceCountry);
        City destinationCity = cityService.findByNameAndCountry(destinationCityName, destinationCountry);

        List<Airport> sourceAirports = airportService.findByCity(sourceCity);
        List<Airport> destinationAirports = airportService.findByCity(destinationCity);
        List<Airport> allAirports = airportService.findAll();

        double bestPrice = Double.MAX_VALUE;
        List<Long> bestPath = new ArrayList<>();

        for (Airport sourceAirport: sourceAirports) {
            // setting all distances to infinity
            Map<Long, Double> distances = allAirports.stream()
                    .collect(Collectors.toMap(Airport::getId, airport -> Double.MAX_VALUE));
            // updating starting node distance
            distances.put(sourceAirport.getId(), 0.0);

            // setting all paths to empty array
            Map<Long, List<Long>> prev = allAirports.stream()
                    .collect(Collectors.toMap(Airport::getId, airport -> new ArrayList<>()));

            Set<Airport> settled = new HashSet<>();
            Set<Airport> unsettled = new HashSet<>();
            unsettled.add(sourceAirport);

            // Dijkstra Algorithm
            while (unsettled.size() != 0){
                Airport currentAirport = getLowestDistanceAirport(unsettled, distances);
                unsettled.remove(currentAirport);
                for (Route route: currentAirport.getRoutes()) {
                    Airport destinationAirport = route.getDestinationAirport();
                    if(! settled.contains(destinationAirport)){
                        double alt = distances.get(currentAirport.getId()) + route.getPrice().doubleValue();
                        if (alt < distances.get(destinationAirport.getId())){
                            distances.put(destinationAirport.getId(), alt);
                            List<Long> updatedPath = prev.get(destinationAirport.getId());
                            updatedPath.add(currentAirport.getId());
                            prev.put(destinationAirport.getId(), updatedPath);
                        }
                        unsettled.add(route.getDestinationAirport());
                    }
                }
                settled.add(currentAirport);
            }
            for (Airport destinationAirport: destinationAirports) {
                Double destAirportBestPrice = distances.get(destinationAirport.getId());
                if(destAirportBestPrice != Double.MAX_VALUE && destAirportBestPrice < bestPrice){
                    bestPrice = destAirportBestPrice;
                    bestPath = prev.get(destinationAirport.getId());
                }
            }
        }
        return routeRepository.findAllById(bestPath);
    }

    private Airport getLowestDistanceAirport(Set<Airport> unsettledNodes, Map<Long, Double> distances) {
        Airport lowestDistanceAirport = null;
        double lowestDistance = Double.MAX_VALUE;
        for (Airport airport: unsettledNodes) {
            double airportDistance = distances.get(airport.getId());
            if (airportDistance < lowestDistance) {
                lowestDistance = airportDistance;
                lowestDistanceAirport = airport;
            }
        }
        return lowestDistanceAirport;
    }
}
