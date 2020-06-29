package htec.task.web.dto.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CommentPutDTO {

    @NotEmpty(message = "Comment content can't be empty")
    private String content;

}
