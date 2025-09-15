package graph;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/*
 * This class defines the edges of a graph.
 * 
 * NOTE: Needs to be used WITH the graph class
 */

public class Edge {
    /* ATRIBUTES */
    // Obs: if the edge is NOT bidirectional, connectedVertices[0] is ALWAYS the FROM vertex
    private final int[] connectedVertices = new int[2]; // used for returning the con. v.
    private String relation; // Stores the relation between two vertices (personages)
    private boolean bidirectional; // Used to define the edge as directed or not
    private int trustLevel; // weight of the edge... TODO

    /* METHODS */
    public Edge(int from, int to, String relation, boolean bidirectional) {
        this.connectedVertices[0] = from;
        this.connectedVertices[1] = to;
        this.relation = relation;
        this.bidirectional = bidirectional;
    } // Constructor

    public boolean isBidirectional() {
        return bidirectional;
    } // returns the result of a consult (bidirected or not)

    // getter
    public int[] getConnectedVertexes() {
        return connectedVertices.clone();
    } // returns the index of two connected vertices in the vertices list of it's graph

    public String getDirection() {
        String arrow = bidirectional ? "<->" : "->";
        return arrow;
    } // returns the direction of the edge

    public int getFrom() {
        return connectedVertices[0];
    } // returns the index of the FROM vertex

    public int getTo() {
        return connectedVertices[1];
    } // returns the index of the TO vertex



    public String getRelation() {
        return relation;
    } // returns the relation between the vertices (personages)

    public int getTrustLevel() {
        return this.trustLevel;
    } // returns the trust level between two personages
    
    // setter
    public void setTrustLevel(int trustLevel) {
        this.trustLevel = trustLevel;
    } // set the trust level, used to update

    @Override
    public String toString() {
        return getFrom()+" "+getDirection()+" "+getTo()+" ("+getRelation()+")";
    } // toString
} // Edge

