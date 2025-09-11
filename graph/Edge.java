package graph;

public class Edge {

    // Obs: if the edge is NOT bidirectional, connectedVertexes[0] is ALWAYS the FROM vertex
    private final int[] connectedVertexes = new int[2]; // used for returning the con. v.
    private String relation;
    private boolean bidirectional;
    private int trustLevel; // weight


    public Edge(int from, int to, String relation, boolean bidirectional) {
        this.connectedVertexes[0] = from;
        this.connectedVertexes[1] = to;
        this.relation = relation;
        this.bidirectional = bidirectional;
    }   

    public boolean isBidirectional() {
        return bidirectional;
    }

    // getter
    public int[] getConnectedVertexes() {
        return connectedVertexes.clone();
    }

    public String getDirection() {
        String arrow = bidirectional ? "<->" : "->";
        return arrow;
    }

    public int getFrom() {
        return connectedVertexes[0];
    }

    public int getTo() {
        return connectedVertexes[1];
    }

    public String getRelation() {
        return relation;
    }

    public int getTrustLevel() {
        return this.trustLevel;
    }
    
    // setter
    public void setTrustLevel(int trustLevel) {
        this.trustLevel = trustLevel;
    }

    @Override // TODO: retrabalhar com o getDirection()
    public String toString() {
        return getFrom()+" "+getDirection()+" "+getTo()+" ("+getRelation()+")";
    }
}

