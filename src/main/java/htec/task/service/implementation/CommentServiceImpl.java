package htec.task.service.implementation;

import htec.task.model.Comment;
import htec.task.repository.CommentRepository;
import htec.task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment findById(Long id){
        return commentRepository.findById(id).orElse(null);
    }

    public Comment save (Comment comment){
        return commentRepository.save(comment);
    }

    public void remove(Comment comment){
        commentRepository.delete(comment);
    }
}
