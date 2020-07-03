package htec.task.web.dto.route;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteGetDTO {

    private String sourceCity;

    private String destinationCity;

    private double price;
}
