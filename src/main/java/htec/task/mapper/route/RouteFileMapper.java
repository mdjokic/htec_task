package htec.task.mapper.route;

import htec.task.model.Airport;
import htec.task.model.Route;
import htec.task.model.file.RouteFile;
import org.springframework.stereotype.Component;

@Component
public class RouteFileMapper {

    public Route toEntity(RouteFile routeFile, Airport source, Airport destination){
        Route route = new Route();
        route.setAirline(routeFile.getAirline());
        route.setAirlineId(routeFile.getAirlineId());
        route.setSourceAirport(source);
        route.setDestinationAirport(destination);
        route.setCodeshare(routeFile.getCodeshare());
        route.setStops(routeFile.getStops());
        route.setEquipment(routeFile.getEquipment());
        route.setPrice(routeFile.getPrice());
        return route;
    }

}
