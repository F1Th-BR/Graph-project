package com.graphproject.domain.graph;

import com.graphproject.domain.characters.Personage;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents a vertex in a graph, referencing a single Personage.
 * Maintains adjacency information and coordinates for graphical representation.
 */
public class Vertex {

    /* ===== ATTRIBUTES ===== */

    /** The personage associated with this vertex (immutable) */
    private final Personage PERSONAGE;

    /** Number of vertices (personages) connected to this vertex */
    private int degree;

    /** Coordinates of the vertex for graphical purposes */
    private Point coordinates;

    /** List of adjacent vertices connected to this vertex */
    private List<Vertex> adjacentVertices;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs a vertex associated with the specified personage.
     * Initializes degree to zero and adjacency list as empty.
     * 
     * @param PERSONAGE The personage this vertex references
     */
    public Vertex(Personage PERSONAGE) {
        this.PERSONAGE = PERSONAGE;
        this.degree = 0;
        this.adjacentVertices = new ArrayList<>();
    }

    /* ===== METHODS ===== */

    /**
     * Adds a vertex to the adjacency list of this vertex.
     * 
     * @param v The vertex to add as adjacent
     */
    public void addAdj(Vertex v) {
        adjacentVertices.add(v);
    }

    /**
     * Returns the list of adjacent vertices.
     * 
     * @return List of adjacent vertices
     */
    public List<Vertex> getAdjacentVertices() {
        return adjacentVertices;
    }

    /**
     * Returns the number of vertices connected to this vertex.
     * 
     * @return Degree (number of connections)
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Returns a defensive copy of this vertex’s coordinates.
     * 
     * @return Coordinates as a new Point object
     */
    public Point getCoordinates() {
        return coordinates == null ? null : new Point(coordinates);
    }

    /**
     * Returns the personage associated with this vertex.
     * 
     * @return The personage referenced by this vertex
     */
    public Personage getPersonage() {
        return PERSONAGE;
    }

    /**
     * Increments the degree by one.
     */
    public void incrementDegree() {
        degree++;
    }

    /**
     * Decrements the degree by one.
     */
    public void decrementDegree() {
        degree--;
    }

    /**
     * Sets the coordinates of this vertex.
     * Defensive copy is made to protect internal state.
     * If null is passed, coordinates are set to (0, 0).
     * 
     * @param coordinates The new coordinates to set
     */
    public void setCoordinates(Point coordinates) {
        if (coordinates != null)
            this.coordinates = new Point(coordinates);
        else
            this.coordinates = new Point(0, 0);
    }

    /**
     * Returns the string representation of this vertex,
     * which is the name of the personage it references.
     * 
     * @return Personage name as string
     */
    @Override
    public String toString() {
        return PERSONAGE.getName();
    }

} // Vertex
