package htec.task.exception;

public class CityDoesntExistsException extends RuntimeException {

    public CityDoesntExistsException() {
        super();
    }
    public CityDoesntExistsException(String message) {
        super(message);
    }
}
