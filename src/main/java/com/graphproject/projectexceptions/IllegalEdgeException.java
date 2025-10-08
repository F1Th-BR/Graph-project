package com.graphproject.projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Exception thrown when an illegal edge (a loop) is attempted to be created.
 * This includes cases where the edge is of the form (v,v) or {v,v}.
 */
public class IllegalEdgeException extends Exception {
    
    /**
     * Default constructor for IllegalEdgeException.
     * This constructor creates the exception with no message or cause.
     */
    public IllegalEdgeException() {
        super("Illegal edge creation: Cannot create a loop (v,v).");
    }

    /**
     * Constructor for IllegalEdgeException with a specific message.
     * 
     * @param message The detailed message explaining the exception.
     */
    public IllegalEdgeException(String message) {
        super(message);
    }

    /**
     * Constructor for IllegalEdgeException with a specific cause.
     * 
     * @param cause The cause of the exception (can be retrieved later).
     */
    public IllegalEdgeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for IllegalEdgeException with both a specific message and cause.
     * 
     * @param message The detailed message explaining the exception.
     * @param cause The cause of the exception (can be retrieved later).
     */
    public IllegalEdgeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Provides a custom string representation of the exception.
     * 
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return "IllegalEdgeException: " + getMessage();
    }
} // IllegalEdgeException

