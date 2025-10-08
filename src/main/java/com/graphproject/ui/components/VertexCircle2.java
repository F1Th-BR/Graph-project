package com.graphproject.ui.components;

import com.graphproject.core.geom.Circle2;
import com.graphproject.domain.graph.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.*;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents the graphical representation of vertices as circles in the graph.
 * 
 * This class handles the creation of circle shapes for each vertex and draws them
 * on the screen. It also manages the rendering of associated images (if available)
 * and labels for each vertex.
 */
public class VertexCircle2 {
    private final List<Vertex> vertices;      // List of vertices in the graph
    private final List<Circle2> verticesShapes; // Drawable circle shapes for each vertex

    /**
     * Constructs a VertexCircle2 object with the given list of vertices.
     * 
     * @param vertices The list of vertices to be represented as circles.
     */
    public VertexCircle2(List<Vertex> vertices) {
        this.vertices = vertices;
        this.verticesShapes = new ArrayList<>();
    }

    /**
     * Creates the circle shapes for each vertex based on its coordinates.
     * This method prepares the graphical representation of the vertices.
     */
    public void createVerticesShapes() {
        for (Vertex v : vertices) {
            Circle2 vertexShape = new Circle2(v.getCoordinates());
            verticesShapes.add(vertexShape);
        }
    }

    /**
     * Draws the vertex circles on the screen. It also draws any associated images
     * (if present) and vertex labels.
     * 
     * @param g2d The graphics context used for drawing.
     */
    public void draw(Graphics2D g2d) {
        int i = 0;
        for (Circle2 vertexShape : verticesShapes) {
            int radius = Circle2.getRadius();
            int x = vertices.get(i).getCoordinates().x - radius;
            int y = vertices.get(i).getCoordinates().y - radius;

            vertexShape.draw(g2d); // Draw the circle

            // Draw the associated image if present, otherwise display a fallback message
            BufferedImage img = vertices.get(i).getPersonage().getPersonageImage();
            if (img != null) {
                g2d.drawImage(img, x, y, radius * 2, radius * 2, null);
            } else {
                g2d.drawString("no image", x + radius, y + radius);
            }

            // Draw the label for the vertex
            drawVertexLabel(g2d, vertices.get(i));
            i++;
        }
    }

    /**
     * Draws the label for a given vertex at its center, slightly offset by the vertex's radius.
     * 
     * @param g2d The graphics context used for drawing.
     * @param v   The vertex whose label is being drawn.
     */
    public void drawVertexLabel(Graphics2D g2d, Vertex v) {
        int radius = Circle2.getRadius();
        g2d.setColor(Color.BLACK);
        g2d.drawString(v.toString(), v.getCoordinates().x + radius, v.getCoordinates().y + radius);
    }
} // VertexCircle2
