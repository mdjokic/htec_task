package htec.task.web.controller;

import htec.task.mapper.route.RouteDTOMapper;
import htec.task.model.Route;
import htec.task.service.RouteService;
import htec.task.web.dto.route.RoutePostDTO;
import htec.task.web.dto.route.RouteQueryResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteDTOMapper routeDTOMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findCheapestRoute(@Valid @RequestBody RoutePostDTO cheapestRouteDTO){
        List<Route> results;
        /*
        results = routeService.findCheapestRoute(cheapestRouteDTO.getSourceCity(), cheapestRouteDTO.getSourceCountry(),
                                cheapestRouteDTO.getDestinationCity(), cheapestRouteDTO.getDestinationCountry());

        RouteQueryResultsDTO retVal = routeDTOMapper.toDTO(results);
        */
        RouteQueryResultsDTO retVal = new RouteQueryResultsDTO();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> importFile(@RequestParam("file")MultipartFile routesFile){
        routeService.createRoutes(routesFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
