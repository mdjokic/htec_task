package htec.task.web.dto.city;

import htec.task.web.dto.comment.CommentGetDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityGetDTO {

    private Long id;

    private String name;

    private String country;

    private String description;

    private List<CommentGetDTO> comments;

    public CityGetDTO () { }
}
