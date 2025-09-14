package graphviewer;

import java.awt.Graphics2D;
import java.util.*;
import graph.*;
import geom.Line2;


public class EdgeLine2 {
    private final List<Edge> edges;
    private final List<Vertex> vertices;

    private final List<Line2> lines;

    public EdgeLine2(List<Edge> edges, List<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
        this.lines = new ArrayList<>();
    }

    public void createEdgesShapes() {
        Vertex u;
        Vertex v;
        Line2 line;

        for (Edge e : edges) {
            int from = e.getFrom();
            int to = e.getTo();

            u = vertices.get(from);
            v = vertices.get(to);

            line = new Line2(u.getCoordinates(), v.getCoordinates());
            lines.add(line);
        }
    }

    public void drawEdges(Graphics2D g2d) {
        for (Line2 e : lines) {
            e.draw(g2d);
        }
    }

    public List<Line2> getLines() {
        return Collections.unmodifiableList(lines);
    }

}

