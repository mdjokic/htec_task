package htec.task.service.implementation;

import htec.task.mapper.airport.AirportFileMapper;
import htec.task.model.Airport;
import htec.task.model.City;
import htec.task.repository.AirportRepository;
import htec.task.repository.CityRepository;
import htec.task.service.AirportService;
import htec.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private AirportFileMapper airportFileMapper;

    public void createAirports(MultipartFile file){
        List<Airport> newAirports = new ArrayList<>();
        Map<String, City> citiesMap = cityService.createCitiesNameAndCountryKeyMap();
        City city;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String line = reader.readLine();
            while(line != null){
                line = line.replace("\"", "");
                String[] tokens = line.split(",");
                String cityNameAndCountry = tokens[2].trim().concat(" ").concat(tokens[3].trim());
                if (citiesMap.containsKey(cityNameAndCountry)){
                    city = citiesMap.get(cityNameAndCountry);
                    newAirports.add(airportFileMapper.toEntity(tokens, city));
                }
                line = reader.readLine();
            }
            airportRepository.saveAll(newAirports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Airport> findAll(){
        return airportRepository.findAll();
    }

    public Map<Long, Airport> createAirportsIdKeyMap(List<Airport> airports){
        return airports.stream().collect(Collectors.toMap(Airport::getId, airport -> airport));
    }

    public Map<String, Airport> createAirportsIATAKeyMap(List<Airport> airports){
        return airports.stream().filter(airport -> airport.getIATACode() != null).
                collect(Collectors.toMap(Airport::getIATACode, airport -> airport));
    }

    public Map<String, Airport> createAirportsICAOKeyMap(List<Airport> airports){
        return airports.stream().filter(airport -> airport.getICAOCode() != null).
                collect(Collectors.toMap(Airport::getICAOCode, airport -> airport));
    }
}
