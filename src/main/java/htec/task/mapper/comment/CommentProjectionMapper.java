package htec.task.mapper.comment;

import htec.task.model.City;
import htec.task.model.Comment;
import htec.task.repository.projections.CityWithCommentProjection;
import org.springframework.stereotype.Component;


@Component
public class CommentProjectionMapper {

    public Comment toEntity(CityWithCommentProjection projection){
        Comment comment = new Comment();
        comment.setId(projection.getCommentId());
        comment.setContent(projection.getCommentContent());
        comment.setModifiedAt(projection.getModifiedAt());
        comment.setCity(new City());
        comment.getCity().setId(projection.getId());
        return comment;
    }
}
