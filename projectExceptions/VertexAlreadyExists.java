package projectExceptions;

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
