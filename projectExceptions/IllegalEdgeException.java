package projectExceptions;

public class IllegalEdgeException extends Exception{
    public IllegalEdgeException() {
        super();
    }

    public IllegalEdgeException(String message) {
        super(message);
    }

    public IllegalEdgeException(Throwable cause) {
        super(cause);
    }

    public IllegalEdgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
