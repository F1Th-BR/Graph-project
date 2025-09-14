package graph;
import characters.Personage;
import geom.Circle2;

import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * This class defines the vertices of a graph
 */

public class Vertex {
    /* ATRIBUTES */
    private final Personage PERSONAGE; // A vertex can only reference ONE personage
    private int degree; // Stores the quantity of vertices (char.) connected with this one 
    private Point coordinates; // Stores its coordinates

    /* METHODS */
    public Vertex(Personage PERSONAGE) {
        this.PERSONAGE = PERSONAGE;
        this.degree = 0;
    } // Constructor

    // Getter
    public int getDegree() {
        return degree;
    } // returns the degree/characters connected with this one

    public Point getCoordinates() {
        Point p = coordinates;
        return new Point(p);
    } // returns the vertex coordinates in a defensive copy;

    public Personage getPersonage() {
        return PERSONAGE;
    } // returns the personage referenced    

    public void incrementDegree() {
        degree++;
    } // increases the degree/characters connected with this one

    public void decrementDegree() {
        degree--;
    } // decreases the degree/characters connected with this one
    
    // setter
    public void setCoordinates(Point coordinates) {
        if(coordinates == null)
            coordinates = new Point(coordinates);
        else
            this.coordinates = new Point(coordinates);
    } // sets the vertex coordinates

    @Override
    public String toString() {
        return PERSONAGE.getName();
    } // toString

} // Vertex

