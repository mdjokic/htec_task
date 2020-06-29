package htec.task.exception;

public class CityAlreadyExistsException extends RuntimeException{

    public CityAlreadyExistsException() {
        super();
    }
    public CityAlreadyExistsException(String message) {
        super(message);
    }

}
