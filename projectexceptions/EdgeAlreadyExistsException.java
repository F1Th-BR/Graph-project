package projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * The purpose of this exception should be self explanatory. It's throwed
 * when an edge that already exists is tried to be created
 * ((u,v), (v,u), {u,v} is tried to be created but it already exists)
 * 
 * extends Exception
 */

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
