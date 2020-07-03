package htec.task.web.dto.route;

import htec.task.model.Route;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class RouteQueryResultsDTO {

    private List<Route> routeList;

    private double price;

    public RouteQueryResultsDTO(){
        this.routeList = new ArrayList<>();
    }
}
