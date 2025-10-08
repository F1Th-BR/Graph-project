package com.graphproject.domain.graph;

import com.graphproject.core.math.GeometricCalculator;
import com.graphproject.projectexceptions.*;

import java.awt.Point;
import java.util.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents a graph structure composed of vertices and edges.
 * Implements geometric calculations for distances.
 */
public class Graph implements GeometricCalculator {

    /* ===== ATTRIBUTES ===== */

    /** List of vertices in the graph */
    private final List<Vertex> vertices;

    /** List of edges connecting vertices in the graph */
    private final List<Edge> edges;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs an empty graph with no vertices or edges.
     */
    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /* ===== METHODS ===== */

    /**
     * Adds a vertex to the graph if it does not already exist.
     * Checks for duplicate vertices and duplicate personages.
     * 
     * @param v The vertex to add
     */
    public void addVertex(Vertex v) {
        try {
            if (vertices.contains(v))
                throw new VertexAlreadyExistsException("O vértice já existe no grafo");
            
            for (Vertex e : vertices) {
                if (e.getPersonage() == v.getPersonage())
                    throw new PersonageAlreadyExistsException("O personagem já existe no grafo");
            }

            vertices.add(v);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Adds an edge between two vertices with specified relation and direction.
     * Validates vertices and prevents duplicate or illegal edges.
     * 
     * @param u             The starting vertex
     * @param v             The ending vertex
     * @param relation      The relation description
     * @param bidirectional True if edge is bidirectional, false if directed
     */
    public void addEdge(Vertex u, Vertex v, String relation, boolean bidirectional) {
        try {
            if (u == v)
                throw new IllegalEdgeException("Os vértices informados são iguais");

            int posU = vertices.indexOf(u);
            int posV = vertices.indexOf(v);

            for (Edge edge : edges) {
                if ((vertices.get(edge.getFrom()) == u && vertices.get(edge.getTo()) == v)
                    || (vertices.get(edge.getTo()) == u && vertices.get(edge.getFrom()) == v))
                    throw new EdgeAlreadyExistsException("A aresta informada já existe ou é paralela a outra pré-existente");
            }

            Edge edge = new Edge(posU, posV, relation, bidirectional);
            edges.add(edge);

            u.incrementDegree();
            v.incrementDegree();

            u.addAdj(v);
            v.addAdj(u);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /* ===== GETTERS ===== */

    /**
     * Returns the total number of edges in the graph.
     * 
     * @return Number of edges
     */
    public int getEdgesQuantity() {
        return edges.size();
    }

    /**
     * Returns an unmodifiable list of edges.
     * 
     * @return List of edges
     */
    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    /**
     * Returns the vertex at the specified index.
     * 
     * @param i The index of the vertex
     * @return The vertex at position i
     */
    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    /**
     * Returns an unmodifiable list of vertices.
     * 
     * @return List of vertices
     */
    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    /**
     * Returns the total number of vertices in the graph.
     * 
     * @return Number of vertices
     */
    public int getVerticesQuantity() {
        return vertices.size();
    }

    /* ===== PRINT METHODS ===== */

    /**
     * Prints information about all vertices in the graph.
     */
    public void printVertices() {
        for (Vertex e : vertices) {
            System.out.println(
                String.format(
                    "Vértice [%d] | id: %d, Personagem: %s",
                    vertices.indexOf(e), e.getPersonage().getId(), e.getPersonage().getName()
                )
            );
        }
    }

    /**
     * Prints information about all edges in the graph.
     */
    public void printEdges() {
        for (Edge e : edges) {
            System.out.println("Aresta (" + e.getFrom() + "," + e.getTo() + ") Tipo: " + e.getDirection() + " Relação: " + e.getRelation());
        }
    }

    /* ===== INTERFACE IMPLEMENTATIONS (GeometricCalculator) ===== */

    /**
     * Calculates the Euclidean distance between two points.
     * 
     * @param p1 First point
     * @param p2 Second point
     * @return Distance between p1 and p2
     */
    @Override
    public double distanceBetweenPoints(Point p1, Point p2) {
        double x = Math.pow(p2.x - p1.x, 2);
        double y = Math.pow(p2.y - p1.y, 2);
        return Math.sqrt(x + y);
    }

    /**
     * Calculates the shortest distance between a point and a line defined by two points.
     * 
     * @param p1Line First point of the line
     * @param p2Line Second point of the line
     * @param p      The point to measure distance from the line
     * @return Distance between point p and the line
     */
    @Override
    public double distanceBetweenPointLine(Point p1Line, Point p2Line, Point p) {
        Point rDirector = new Point(directorLine(p1Line, p2Line));
        Point differenceP = new Point(p.x - p1Line.x, p.y - p1Line.y);
        double vectorProduct = differenceP.x * rDirector.y - differenceP.y * rDirector.x;
        double rLength = Math.sqrt(Math.pow(rDirector.x, 2) + Math.pow(rDirector.y, 2));

        if (vectorProduct < 0)
            vectorProduct *= -1;

        return vectorProduct / rLength;
    }

    /**
     * Generates a direction vector from two points defining a line.
     * 
     * @param p1 First point of the line
     * @param p2 Second point of the line
     * @return A Point representing the direction vector of the line
     */
    @Override
    public Point directorLine(Point p1, Point p2) {
        return new Point(p2.x - p1.x, p2.y - p1.y);
    }

} // Graph
