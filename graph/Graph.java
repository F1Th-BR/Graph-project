package graph;
import java.awt.Point;
import java.util.*;

import math.GeometricCalculator;
import projectexceptions.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * This class defines the graph
 */

public class Graph implements GeometricCalculator {
    /* ATTRIBUTES */
    private final List<Vertex> vertices; // list of vertices
    private final List<Edge> edges; // list of edges

    /* METHODS */
    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    } // Constructor

    // add methods
    public void addVertex(Vertex v) {
        try { // checks if the vertex is valid
            if(vertices.contains(v)) // if the vertex v is already in the vertices list:
                throw new VertexAlreadyExistsException("O vértice já existe no grafo");
            
            for (Vertex e : vertices) { // checks if the personage is already in a vertex
                if(e.getPersonage() == v.getPersonage()) // if it's in a vextex:
                    throw new PersonageAlreadyExistsException("O personagem já existe no grafo");
            }

            vertices.add(v); // adds v into the vertices list if the vertex is valid
        } catch (Exception e) {
            System.out.println(e); // prints the exception
        }
    } // adds a vertex

    public void addEdge(Vertex u, Vertex v, String relation, boolean bidirectional) {
        try { // checks if the edge is valid
            if(u == v) // if the two vertices are the same:
                throw new IllegalEdgeException("Os vértices informados são iguais");
                
            int posU = vertices.indexOf(u); // from
            int posV = vertices.indexOf(v); // to
            
            for (Edge edge : edges) { // checks if the edge already exists, if so:
                if(vertices.get(edge.getFrom()) == u && vertices.get(edge.getTo()) == v)
                    throw new EdgeAlreadyExistsException("A aresta informada já existe");
                
                // or

                if(vertices.get(edge.getTo()) == u && vertices.get(edge.getFrom()) == v)
                    throw new EdgeAlreadyExistsException("A aresta é paralela a outra pré-existente");
            }
                
            Edge edge = new Edge(posU, posV, relation, bidirectional); // creates the edge
            edges.add(edge); // adds the edge into the list

            u.incrementDegree();
            v.incrementDegree(); // increments the degree of both vertices

        
        } catch(Exception e) {
            System.out.println(e); // prints the exception
        }

    } // adds an edge

    // getter
    public int getEdgesQuantity() {
        return edges.size();
    } // returns the amount of edges

    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public List<Vertex> getVertices(){
        return Collections.unmodifiableList(vertices);
    } // returns the list of vertices
    
    public int getVerticesQuantity() {
        return vertices.size();
    } // returns the amount of vertices
    
    
    public void printVertices() {
        for (Vertex e : vertices) {
            System.out.println("N.o do vértice: "+vertices.indexOf(e)+
                " | Nome personagem: "+e.toString()
            );
        }
    } // prints all the vertices

    public void printEdges() {
        for (Edge e : edges) {
            System.out.println("Aresta ("+e.getFrom()+","+e.getTo()+") Tipo: "+e.getDirection()+" Relação: "+e.getRelation());
        }
    } // prints all the edges

    /** INTERFACE METHODS **/
    @Override
    public double distanceBetweenPoints(Point p1, Point p2) {
        double x = Math.pow(p2.x - p1.x, 2);
        double y = Math.pow(p2.y - p1.y, 2);

        return Math.sqrt(x+y);
    }

    @Override
    public double distanceBetweenPointLine(Point p1Line, Point p2Line, Point p) {
        Point rDirector = new Point(directorLine(p1Line, p2Line));
        Point differenceP = new Point(p.x - p1Line.x, p.y - p1Line.y);
        double vectorProduct = differenceP.x*rDirector.y - differenceP.y*rDirector.x;
        double rLength = Math.sqrt(Math.pow(rDirector.x, 2) + Math.pow(rDirector.y,2));
        
        if(vectorProduct < 0)
            vectorProduct *= -1;

        double distance = vectorProduct/rLength;
        return distance;
    }

    @Override
    public Point directorLine(Point p1, Point p2) {
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        Point directorLine = new Point(x, y);
        return directorLine;
    }
}