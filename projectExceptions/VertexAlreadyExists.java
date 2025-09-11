package projectExceptions;

public class VertexAlreadyExists extends Exception{
    public VertexAlreadyExists() {
        super();
    }

    public VertexAlreadyExists(String message) {
        super(message);
    }

    public VertexAlreadyExists(Throwable cause) {
        super(cause);
    }

    public VertexAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
