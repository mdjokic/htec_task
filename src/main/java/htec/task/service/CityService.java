package htec.task.service;

import htec.task.model.City;

public interface CityService {

    City findById(Long id);

    City findByNameAndCountry(String name, String country);

    City save(City city);

}
