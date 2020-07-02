package htec.task.web.controller;

import htec.task.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> importFile(@RequestParam("file")MultipartFile routesFile){
        routeService.createRoutes(routesFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
