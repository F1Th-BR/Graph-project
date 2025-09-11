package projectExceptions;

public class EdgeAlreadyExistsException extends Exception{
    public EdgeAlreadyExistsException() {
        super();
    }

    public EdgeAlreadyExistsException(String message) {
        super(message);
    }

    public EdgeAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public EdgeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
