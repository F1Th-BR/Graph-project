package com.graphproject.domain.graph;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents an edge connecting two vertices in a graph.
 * 
 * NOTE: This class should be used in conjunction with the Graph class.
 */
public class Edge {

    /* ===== ATTRIBUTES ===== */

    /**
     * Array storing the indices of the connected vertices.
     * If the edge is NOT bidirectional, connectedVertices[0] is always the FROM vertex.
     */
    private final int[] connectedVertices = new int[2];

    /** Describes the relationship between the two connected vertices (personages). */
    private String relation;

    /** Indicates whether the edge is bidirectional (undirected) or directed. */
    private boolean bidirectional;

    /** Weight of the edge representing the trust level between vertices. */
    private int trustLevel;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs an Edge connecting two vertices with a specified relation and directionality.
     * 
     * @param from          Index of the starting vertex (FROM)
     * @param to            Index of the ending vertex (TO)
     * @param relation      Description of the relation between vertices
     * @param bidirectional True if the edge is bidirectional, false if directed
     */
    public Edge(int from, int to, String relation, boolean bidirectional) {
        this.connectedVertices[0] = from;
        this.connectedVertices[1] = to;
        this.relation = relation;
        this.bidirectional = bidirectional;
    }

    /* ===== GETTERS ===== */

    /**
     * Checks if the edge is bidirectional.
     * 
     * @return True if the edge is bidirectional, false otherwise
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * Returns a copy of the indices of the connected vertices.
     * 
     * @return An int array containing the connected vertices indices
     */
    public int[] getConnectedVertexes() {
        return connectedVertices.clone();
    }

    /**
     * Returns a string representing the direction of the edge.
     * 
     * @return "<->" if bidirectional, "->" if directed
     */
    public String getDirection() {
        return bidirectional ? "<->" : "->";
    }

    /**
     * Returns the index of the FROM vertex.
     * 
     * @return The starting vertex index
     */
    public int getFrom() {
        return connectedVertices[0];
    }

    /**
     * Returns the index of the TO vertex.
     * 
     * @return The ending vertex index
     */
    public int getTo() {
        return connectedVertices[1];
    }

    /**
     * Returns the relation description between the connected vertices.
     * 
     * @return The relation string
     */
    public String getRelation() {
        return relation;
    }

    /**
     * Returns the trust level associated with the edge.
     * 
     * @return The trust level as an integer
     */
    public int getTrustLevel() {
        return this.trustLevel;
    }

    /* ===== SETTERS ===== */

    /**
     * Sets the trust level for the edge.
     * 
     * @param trustLevel The new trust level value
     */
    public void setTrustLevel(int trustLevel) {
        this.trustLevel = trustLevel;
    }

    /* ===== OVERRIDES ===== */

    /**
     * Returns a string representation of the edge, showing the direction and relation.
     * 
     * @return A formatted string like "from -> to (relation)"
     */
    @Override
    public String toString() {
        return getFrom() + " " + getDirection() + " " + getTo() + " (" + getRelation() + ")";
    }

} // Edge


