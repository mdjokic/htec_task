package htec.task.service;

import htec.task.model.Route;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RouteService {

    void createRoutes(MultipartFile file);

    List<Route> findCheapestRoute(String sourceCityName, String sourceCountry,
                                  String destinationCityName, String destinationCountry);
}
