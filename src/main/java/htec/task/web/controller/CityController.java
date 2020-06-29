package htec.task.web.controller;

import htec.task.exception.CityAlreadyExistsException;
import htec.task.mapper.CityMapper;
import htec.task.model.City;
import htec.task.service.CityService;
import htec.task.web.dto.CityPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityMapper cityMapper;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<City> createCity(@Valid @RequestBody CityPostDTO cityDTO){
        City databaseCity = cityService.findCityByNameAndCountry(cityDTO.getName(), cityDTO.getCountry());
        if(databaseCity != null){
            throw new CityAlreadyExistsException("City with given name and country already exists");
        }
        City newCity = cityMapper.toEntity(cityDTO);
        newCity = cityService.save(newCity);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }
}
