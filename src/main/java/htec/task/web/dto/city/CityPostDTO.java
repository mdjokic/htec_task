package htec.task.web.dto.city;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CityPostDTO {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "Country can't be empty")
    private String country;

    @NotBlank(message = "Description can't be empty")
    private String description;

    public CityPostDTO(){ }

}
