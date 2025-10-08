package com.graphproject.projectexceptions;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Exception thrown when an attempt is made to add a Vertex that already exists in the graph.
 * This exception is thrown when trying to add a duplicate vertex to the graph.
 */
public class VertexAlreadyExistsException extends Exception {

    /**
     * Default constructor for VertexAlreadyExistsException.
     * This constructor creates the exception with no message or cause.
     */
    public VertexAlreadyExistsException() {
        super("Vertex already exists in the graph");
    }

    /**
     * Constructor for VertexAlreadyExistsException with a specific message.
     * 
     * @param message The detailed message explaining the exception.
     */
    public VertexAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructor for VertexAlreadyExistsException with a specific cause.
     * 
     * @param cause The cause of the exception (can be retrieved later).
     */
    public VertexAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for VertexAlreadyExistsException with both a specific message and cause.
     * 
     * @param message The detailed message explaining the exception.
     * @param cause The cause of the exception (can be retrieved later).
     */
    public VertexAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Provides a custom string representation of the exception.
     * 
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return "VertexAlreadyExistsException: " + getMessage();
    }
} // VertexAlreadyExistsException
