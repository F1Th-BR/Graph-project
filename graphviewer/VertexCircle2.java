package graphviewer;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.*;

import geom.Circle2;
import graph.*;

public class VertexCircle2 {
    private final List<Vertex> vertices;
    private final List<Circle2> verticesShapes;

    public VertexCircle2(List<Vertex> vertices) {
        this.vertices = vertices;
        this.verticesShapes = new ArrayList<>();
    }

    public void createVerticesShapes() {
        Circle2 vertex;

        for (Vertex v : vertices) {
            vertex = new Circle2(v.getCoordinates());
            verticesShapes.add(vertex);
        }
    }

    public void draw(Graphics2D g2d) {
        int i= 0;
        for (Circle2 vertexShape : verticesShapes) {
            vertexShape.draw(g2d);
            drawVertexLabel(g2d, vertices.get(i));
            i++;
        }
    }


    public void drawVertexLabel(Graphics2D g2d, Vertex v) {
        int radius = Circle2.getRadius();
        g2d.setColor(Color.BLACK);
        g2d.drawString(v.toString(), v.getCoordinates().x+radius, v.getCoordinates().y+radius);
    }

}
