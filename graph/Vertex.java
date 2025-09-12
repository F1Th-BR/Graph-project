package graph;
import characters.Personage;
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
    private final Personage personage; // A vertex can only reference ONE personage
    private int degree; // Stores the quantity of vertices (char.) connected with this one 
    private Point coordinates; // Stores it's coordinates
    private Point[] bounds = new Point[2];

    /* METHODS */
    public Vertex(Personage personage) {
        this.personage = personage;
        this.degree = 0;
    } // Constructor

    // Getter
    public int getDegree() {
        return degree;
    } // returns the degree/characters connected with this one

    public Point[] getBounds() {
        return this.bounds;
    } // returns the bounds of the vertex

    public Point getCoordinates() {
        return coordinates;
    } // returns the vertex coordinates;

    public Personage getPersonage() {
        return personage;
    } // returns the personage referenced    

    public void incrementDegree() {
        degree++;
    } // increases the degree/characters connected with this one

    public void decrementDegree() {
        degree--;
    } // decreases the degree/characters connected with this one
    
    // setter
    public void setBounds() {
        bounds[0].x = coordinates.x-10;
        bounds[0].y = coordinates.y+10;
        bounds[1].x = coordinates.x+10;
        bounds[1].y = coordinates.y-10;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    } // sets the vertex coordinates

    @Override
    public String toString() {
        return personage.getName();
    } // toString

} // Vertex

