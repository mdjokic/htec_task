package htec.task.web.controller;

import htec.task.exception.CityAlreadyExistsException;
import htec.task.mapper.city.CityDTOMapper;
import htec.task.model.City;
import htec.task.service.CityService;
import htec.task.web.dto.city.CityGetDTO;
import htec.task.web.dto.city.CityPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityDTOMapper cityMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCities(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "commentsNumber", required = false) Integer commentNumber) {
        List<City> cities;
        name = name == null ? null : name + "%";
        if(name != null && commentNumber != null){
            cities = cityService.findCitiesWithLatestComments(name, commentNumber);
        }else if(name != null){
            cities = cityService.findCitiesByName(name);
        }else if(commentNumber != null){
            cities = cityService.findCitiesWithLatestComments("%", commentNumber);
        }else{
            cities = cityService.getCitiesWithAllComments();
        }
        List<CityGetDTO> citiesDTO = cities.stream().map(cityMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(citiesDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<City> createCity(@Valid @RequestBody CityPostDTO cityDTO){
        City databaseCity = cityService.findByNameAndCountry(cityDTO.getName(), cityDTO.getCountry());
        if(databaseCity != null){
            throw new CityAlreadyExistsException("City with given name and country already exists");
        }
        City newCity = cityMapper.toEntity(cityDTO);
        newCity = cityService.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }
}
