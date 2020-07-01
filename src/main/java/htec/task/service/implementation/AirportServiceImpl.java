package htec.task.service.implementation;

import htec.task.mapper.airport.AirportFileMapper;
import htec.task.model.Airport;
import htec.task.model.City;
import htec.task.repository.AirportRepository;
import htec.task.repository.CityRepository;
import htec.task.service.AirportService;
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
    private CityRepository cityRepository;

    @Autowired
    private AirportFileMapper airportFileMapper;

    public void createAirports(MultipartFile file){
        List<City> cities = cityRepository.findAll();
        List<Airport> newAirports = new ArrayList<>();
        Map<String, City> citiesMap = cities.stream().collect(Collectors.toMap(City::getNameAndCountry, city -> city));
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
}
