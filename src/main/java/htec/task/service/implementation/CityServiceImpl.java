package htec.task.service.implementation;

import htec.task.mapper.city.CityProjectionMapper;
import htec.task.model.City;
import htec.task.repository.CityRepository;
import htec.task.repository.projections.CityWithCommentProjection;
import htec.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityProjectionMapper cityMapper;

    public List<City> getCitiesWithAllComments(){
        return cityRepository.findAll();
    }

    public List<City> findCitiesByName(String name){
        return cityRepository.findCitiesByNameLikeIgnoreCase(name);
    }

    public List<City> findCitiesWithLatestComments(String name, int numberOfComments){
        List<CityWithCommentProjection> citiesProjection = cityRepository.findCitiesWithLatestComments(numberOfComments, name);
        return cityMapper.toEntity(citiesProjection);
    }


    public City findById(Long id){
        return cityRepository.findById(id).orElse(null);
    }

    public City findByNameAndCountry(String name, String country) {
        return cityRepository.findCityByNameAndCountry(name, country);
    }

    public City save(City city){
        return cityRepository.save(city);
    }

    public Map<String, City> createCitiesNameAndCountryKeyMap(){
        List<City> cities = cityRepository.findAll();
        return cities.stream().collect(Collectors.toMap(City::getNameAndCountry, city -> city));
    }
}
