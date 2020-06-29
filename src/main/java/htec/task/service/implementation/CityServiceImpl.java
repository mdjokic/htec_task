package htec.task.service.implementation;

import htec.task.model.City;
import htec.task.repository.CityRepository;
import htec.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    public City findCityByNameAndCountry(String name, String country) {
        return cityRepository.findCityByNameAndCountry(name, country);
    }

    public City save(City city){
        return cityRepository.save(city);
    }
}
