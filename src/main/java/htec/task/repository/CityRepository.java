package htec.task.repository;

import htec.task.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    City findCityByNameAndCountry(String name, String Country);
}
