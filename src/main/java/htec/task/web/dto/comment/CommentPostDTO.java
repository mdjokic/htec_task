package htec.task.web.dto.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CommentPostDTO {

    @NotNull(message = "CityId can't be null")
    private long cityId;

    @NotBlank(message = "Comment can't be empty")
    private String content;

    public CommentPostDTO() { }
}
