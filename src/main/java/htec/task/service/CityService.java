package htec.task.service;

import htec.task.model.City;

public interface CityService {

    City findCityByNameAndCountry(String name, String country);

    City save(City city);

}
