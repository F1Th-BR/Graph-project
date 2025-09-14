package projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * The purpose of this exception should be self explanatory. It's throwed
 * when a vertex that already exists is tried to be added to the graph
 * 
 * extends Exception
 */

public class VertexAlreadyExistsException extends Exception{
    public VertexAlreadyExistsException() {
        super();
    }

    public VertexAlreadyExistsException(String message) {
        super(message);
    }

    public VertexAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public VertexAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
