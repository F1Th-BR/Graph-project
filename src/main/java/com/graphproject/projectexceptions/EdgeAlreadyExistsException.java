package com.graphproject.projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Exception thrown when an edge that already exists is attempted to be created.
 * This includes cases where the edge (u,v), (v,u), or {u,v} already exists in the graph.
 */
public class EdgeAlreadyExistsException extends Exception {
    
    /**
     * Default constructor for EdgeAlreadyExistsException.
     * This constructor creates the exception with no message or cause.
     */
    public EdgeAlreadyExistsException() {
        super("Edge already exists in the graph.");
    }

    /**
     * Constructor for EdgeAlreadyExistsException with a specific message.
     * 
     * @param message The detailed message explaining the exception.
     */
    public EdgeAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructor for EdgeAlreadyExistsException with a specific cause.
     * 
     * @param cause The cause of the exception (can be retrieved later).
     */
    public EdgeAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for EdgeAlreadyExistsException with both a specific message and cause.
     * 
     * @param message The detailed message explaining the exception.
     * @param cause The cause of the exception (can be retrieved later).
     */
    public EdgeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Provides a custom string representation of the exception.
     * 
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return "EdgeAlreadyExistsException: " + getMessage();
    }
} // EdgeAlreadyExistsException
