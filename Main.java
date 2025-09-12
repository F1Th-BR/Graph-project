import graph.*;
import characters.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

// testing the classes
public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        Personage character1 = new Personage("Hytalo", 19, "Student", "FACOM");
        Personage character2 = new Personage("Diego", 0, "Professor", "FACOM");
        Personage character3 = new Personage("Cassiano", 21, "Student", "FACOM");
        Personage character4 = new Personage("Feijo", 18, "Student", "FACOM");


        Vertex v1 = new Vertex(character1);
        Vertex v2 = new Vertex(character2);
        Vertex v3 = new Vertex(character3);
        Vertex v4 = new Vertex(character4);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);


        g.getVerticesQuantity();
        g.printVertices();

        g.addEdge(v1, v2, "relação", false);
        g.addEdge(v1, v3, "relação", true);
        g.addEdge(v1, v4, "relação", false);
        g.addEdge(v2, v3, "relação", true);
        g.addEdge(v2, v4, "relação", true);
        g.addEdge(v3, v4, "relação", true);

        g.getEdgesQuantity();
        g.printEdges();

    }


}
