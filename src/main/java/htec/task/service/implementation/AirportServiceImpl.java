package htec.task.service.implementation;

import htec.task.mapper.airport.AirportFileMapper;
import htec.task.model.Airport;
import htec.task.model.City;
import htec.task.model.file.AirportFile;
import htec.task.repository.AirportRepository;
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
                AirportFile airportFile = new AirportFile(line);
                String cityNameAndCountry = airportFile.getCity().trim().concat(" ").concat(airportFile.getCountry().trim());
                if (citiesMap.containsKey(cityNameAndCountry)){
                    city = citiesMap.get(cityNameAndCountry);
                    newAirports.add(airportFileMapper.toEntity(airportFile, city));
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

    public List<Airport> findByCity(City city){
        return airportRepository.findAirportByCity(city);
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
