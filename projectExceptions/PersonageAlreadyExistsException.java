package projectExceptions;
/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * The purpose of this exception should be self explanatory. It's throwed
 * when a Personage (character) that already exists is created.
 * 
 * extends Exception
 */


public class PersonageAlreadyExistsException extends Exception{
    public PersonageAlreadyExistsException(){
        super();
    }

    public PersonageAlreadyExistsException(String message){
        super(message);
    }
    
    public PersonageAlreadyExistsException(Throwable cause){
        super(cause);
    }
    
    public PersonageAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
} // PersonageAlreadyExistsException
