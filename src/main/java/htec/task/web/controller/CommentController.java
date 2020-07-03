package htec.task.web.controller;

import htec.task.exception.CityDoesntExistsException;
import htec.task.exception.CommentDoesntExistsException;
import htec.task.exception.CommentOwnershipException;
import htec.task.mapper.comment.CommentDTOMapper;
import htec.task.model.City;
import htec.task.model.Comment;
import htec.task.model.User;
import htec.task.service.CityService;
import htec.task.service.CommentService;
import htec.task.service.UserService;
import htec.task.web.dto.comment.CommentGetDTO;
import htec.task.web.dto.comment.CommentPostDTO;
import htec.task.web.dto.comment.CommentPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDTOMapper commentDTOMapper;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentGetDTO> createComment(@Valid @RequestBody CommentPostDTO commentDTO){
        City databaseCity = cityService.findById(commentDTO.getCityId());
        if(databaseCity == null){
            throw new CityDoesntExistsException("City with given id doesn't exists");
        }
        Comment newComment = commentDTOMapper.toEntity(commentDTO);
        setUser(newComment);
        newComment.setCity(databaseCity);
        newComment = commentService.save(newComment);
        CommentGetDTO commentGetDTO = commentDTOMapper.toDTO(newComment);
        return new ResponseEntity<>(commentGetDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CommentGetDTO> updateComment(@PathVariable Long id,
                                                       @Valid @RequestBody CommentPutDTO commentDTO){
        Comment databaseComment = commentService.findById(id);
        if (databaseComment == null){
            throw new CommentDoesntExistsException("Comment with given id doesn't exists");
        }
        if(checkCommentOwnership(databaseComment)){
            throw new CommentOwnershipException("User doesn't own given comment");
        }
        databaseComment.update(commentDTO.getContent());
        databaseComment = commentService.save(databaseComment);
        CommentGetDTO commentGetDTO = commentDTOMapper.toDTO(databaseComment);
        return new ResponseEntity<>(commentGetDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        Comment databaseComment = commentService.findById(id);
        if (databaseComment == null){
            throw new CommentDoesntExistsException("Comment with given id doesn't exists");
        }
        if(checkCommentOwnership(databaseComment)){
            throw new CommentOwnershipException("User doesn't own given comment");
        }
        commentService.remove(databaseComment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Comment setUser(Comment comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        comment.setUser(user);
        return comment;
    }

    private boolean checkCommentOwnership(Comment comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return !username.equals(comment.getUser().getUsername());
    }
}
