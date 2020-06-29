package htec.task.service;

import htec.task.model.Comment;

public interface CommentService {

    Comment findById(Long id);

    Comment save (Comment comment);

    void remove(Comment comment);
}
