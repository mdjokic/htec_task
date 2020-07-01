package htec.task.mapper.comment;

import htec.task.model.Comment;
import htec.task.web.dto.comment.CommentGetDTO;
import htec.task.web.dto.comment.CommentPostDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class CommentDTOMapper {

    public CommentGetDTO toDTO(Comment comment){
        CommentGetDTO commentDTO = new CommentGetDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setModifiedAt(LocalDateTime.ofInstant(comment.getModifiedAt(), ZoneOffset.systemDefault()));
        commentDTO.setCityId(comment.getCity().getId());
        return commentDTO;
    }

    public Comment toEntity(CommentPostDTO commentPostDTO){
        Comment comment = new Comment();
        comment.setContent(commentPostDTO.getContent());
        return comment;
    }
}
