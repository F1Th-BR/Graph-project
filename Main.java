import graph.*;
import graphviewer.GraphViewer;

import java.awt.Point;

import characters.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

// testing the classes
public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        Personage character1 = new Personage("Rosemary", 22, "Responsável", "LB");
        Personage character2 = new Personage("Saulo", 21, "Explosivo", "N");
        Personage character3 = new Personage("Olga", 250, "Informada", "CB");
        Personage character4 = new Personage("Wilhelm", 120, "Centrado", "LB");
        Personage character5 = new Personage("Amélia", 12, "Inocente", "LB");

        Vertex v1 = new Vertex(character1);
        Vertex v2 = new Vertex(character2);
        Vertex v3 = new Vertex(character3);
        Vertex v4 = new Vertex(character4);
        Vertex v5 = new Vertex(character5);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);


        g.addEdge(v1, v2, "Companheiros de missão", true);
        g.addEdge(v1, v3, "Informantes", true);
        g.addEdge(v1, v4, "Mestre/aprendiz", true);
        g.addEdge(v3, v2, "Contato", false);
        g.addEdge(v2, v4, "Amigos", true);
        g.addEdge(v3, v4, "Conhece", false);
        g.addEdge(v1, v5, "Irmãs", true);

        Point p1 = new Point(300, 100);
        Point p2 = new Point(441, 181);
        Point p3 = new Point(382, 340);
        Point p4 = new Point(218, 340);
        Point p5 = new Point(159, 181);
    

        v1.setCoordinates(p1);
        v2.setCoordinates(p2);
        v3.setCoordinates(p3);
        v4.setCoordinates(p4);
        v5.setCoordinates(p5);

        GraphViewer.display(g);

    }


}
