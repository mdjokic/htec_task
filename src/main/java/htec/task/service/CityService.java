package htec.task.service;

import htec.task.model.City;
import java.util.List;

public interface CityService {

    List<City> getCitiesWithAllComments();

    List<City> findCitiesByName(String name);

    List<City> findCitiesWithLatestComments(String name, int numberOfComments);

    City findById(Long id);

    City findByNameAndCountry(String name, String country);

    City save(City city);

}
