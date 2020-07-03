package htec.task.web.dto.route;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RoutePostDTO {

    @NotEmpty(message = "Source city can't be empty")
    private String sourceCity;

    @NotEmpty(message = "Source country can't be empty")
    private String sourceCountry;

    @NotEmpty(message = "Destination city can't be empty")
    private String destinationCity;

    @NotEmpty(message = "Destination country can't be empty")
    private String destinationCountry;

}
