package htec.task.mapper;

import htec.task.model.City;
import htec.task.web.dto.CityPostDTO;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public City toEntity(CityPostDTO cityPostDTO){
        return new City(cityPostDTO.getName(), cityPostDTO.getCountry(), cityPostDTO.getDescription());
    }
}
