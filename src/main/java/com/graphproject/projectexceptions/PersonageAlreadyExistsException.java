package com.graphproject.projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Exception thrown when an attempt is made to create a Personage (character) that already exists.
 * This exception is thrown when trying to add a duplicate character to the graph.
 */
public class PersonageAlreadyExistsException extends Exception {

    /**
     * Default constructor for PersonageAlreadyExistsException.
     * This constructor creates the exception with no message or cause.
     */
    public PersonageAlreadyExistsException() {
        super("Personage already exists in the graph");
    }

    /**
     * Constructor for PersonageAlreadyExistsException with a specific message.
     * 
     * @param message The detailed message explaining the exception.
     */
    public PersonageAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructor for PersonageAlreadyExistsException with a specific cause.
     * 
     * @param cause The cause of the exception (can be retrieved later).
     */
    public PersonageAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for PersonageAlreadyExistsException with both a specific message and cause.
     * 
     * @param message The detailed message explaining the exception.
     * @param cause The cause of the exception (can be retrieved later).
     */
    public PersonageAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Provides a custom string representation of the exception.
     * 
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return "PersonageAlreadyExistsException: " + getMessage();
    }
} // PersonageAlreadyExistsException

