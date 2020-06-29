package htec.task.exception;

public class CommentDoesntExistsException extends RuntimeException {

    public CommentDoesntExistsException() {
        super();
    }

    public CommentDoesntExistsException(String message) {
        super(message);
    }

}
