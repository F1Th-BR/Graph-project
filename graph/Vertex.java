package graph;
import characters.Personage;

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

    /* METHODS */
    public Vertex(Personage personage) {
        this.personage = personage;
        this.degree = 0;
    } // Constructor

    // Getter
    public Personage getPersonage() {
        return personage;
    } // returns the personage referenced

    public int getDegree() {
        return degree;
    } // returns the degree/characters connected with this one

    public void incrementDegree() {
        degree++;
    } // increases the degree/characters connected with this one

    public void decrementDegree() {
        degree--;
    } // decreases the degree/characters connected with this one
    
    @Override
    public String toString() {
        return personage.getName();
    } // toString

} // Vertex

