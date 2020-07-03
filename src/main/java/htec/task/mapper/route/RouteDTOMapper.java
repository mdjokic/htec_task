package htec.task.mapper.route;

import htec.task.model.Route;
import htec.task.web.dto.route.RouteGetDTO;
import htec.task.web.dto.route.RouteQueryResultsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteDTOMapper {

    public RouteQueryResultsDTO toDTO(List<Route> routeList){
        RouteQueryResultsDTO retVal = new RouteQueryResultsDTO();
        double totalPrice = 0.0;
        for (Route route: routeList) {
            RouteGetDTO routeDTO = toDTO(route);
            totalPrice += routeDTO.getPrice();
            retVal.getRouteList().add(route);
        }
        retVal.setPrice(totalPrice);
        return retVal;
    }

    public RouteGetDTO toDTO(Route route){
        RouteGetDTO routeDTO = new RouteGetDTO();
        routeDTO.setSourceCity(route.getSourceAirport().getCity().getName());
        routeDTO.setDestinationCity(route.getDestinationAirport().getCity().getName());
        routeDTO.setPrice(route.getPrice().doubleValue());
        return routeDTO;
    }
}
