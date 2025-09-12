package graph;
import java.util.*;
import projectExceptions.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * This class defines the graph
 */

public class Graph {
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
                throw new VertexAlreadyExists("O vértice já existe no grafo");
            
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
    public int getVerticesQuantity() {
        return vertices.size();
    } // returns the amount of vertices

    public int getEdgesQuantity() {
        return edges.size();
    } // returns the amount of edges


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
}