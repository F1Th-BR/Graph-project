package projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

 /*
 * The purpose of this exception should be self explanatory. It's throwed
 * when a loop is tried to be created (edge = (v,v) or {v,v})
 * 
 * extends Exception
 */

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
