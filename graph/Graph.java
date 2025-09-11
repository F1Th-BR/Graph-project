package graph;
import java.util.*;
import projectExceptions.*;



public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        try {
            if(vertices.contains(v))
                throw new VertexAlreadyExists("O vértice/personagem já existe");
                
            vertices.add(v);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addEdge(Vertex u, Vertex v, String relation, boolean bidirectional) {
        try {    
            if(u == v)
                throw new IllegalEdgeException("Os vértices informados são iguais");
                
            int posU = vertices.indexOf(u);
            int posV = vertices.indexOf(v);
            
            for (Edge edge : edges) {
                if(vertices.get(edge.getFrom()) == u && vertices.get(edge.getTo()) == v)
                    throw new EdgeAlreadyExistsException("A aresta informada já existe");

                if(vertices.get(edge.getTo()) == u && vertices.get(edge.getFrom()) == v)
                    throw new EdgeAlreadyExistsException("A aresta é paralela a outra pré-existente");
            }
                
            Edge edge = new Edge(posU, posV, relation, bidirectional);
            edges.add(edge);
            u.incrementDegree();
            v.incrementDegree();

        
        } catch(EdgeAlreadyExistsException e) {
            System.out.println(e);
        } catch (IllegalEdgeException e) {
            System.out.println(e);
        }

    }

    public int getVerticesQuantity() {
        return vertices.size();
    }

    public int getEdgesQuantity() {
        return edges.size();
    }

    public void printVertices() {
        for (Vertex e : vertices) {
            System.out.println("N.o do vértice: "+vertices.indexOf(e)+
                " | Nome personagem: "+e.toString()
            );
        }
    }

    public void printEdges() {
        for (Edge e : edges) {
            System.out.println("Aresta ("+e.getFrom()+","+e.getTo()+") Tipo: "+e.getDirection()+" Relação: "+e.getRelation());
        }
    }
}