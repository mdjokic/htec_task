package htec.task.web.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentGetDTO {

    private Long id;

    private String content;

    private LocalDateTime modifiedAt;

    private Long cityId;

    public CommentGetDTO() { }
}
