package com.graphproject.ui.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.*;

import com.graphproject.core.geom.Circle2;
import com.graphproject.core.geom.Line2;
import com.graphproject.domain.graph.*;

import java.awt.RenderingHints;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Represents a visual union of Edge and Line2 objects.
 * 
 * This class helps translate logical graph edges into drawable line segments (Line2 objects),
 * simplifying the process of rendering a graph on the screen, including arrowheads for directed edges.
 */
public class EdgeLine2 {
    
    /* ===== ATTRIBUTES ===== */
    
    private final List<Edge> edges;      // List of edges in the graph
    private final List<Vertex> vertices; // List of vertices in the graph
    private final List<Line2> lines;     // Corresponding drawable lines for each edge

    /* ===== CONSTRUCTOR ===== */
    
    /**
     * Constructs an EdgeLine2 object with a given list of edges and vertices.
     * 
     * @param edges    The list of edges in the graph
     * @param vertices The list of vertices in the graph
     */
    public EdgeLine2(List<Edge> edges, List<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
        this.lines = new ArrayList<>();
    }

    /* ===== METHODS ===== */

    /**
     * Creates the drawable Line2 shapes for each Edge by connecting the coordinates
     * of the vertices it links. The result is stored in the lines list.
     */
    public void createEdgesShapes() {
        for (Edge e : edges) {
            int from = e.getFrom();
            int to = e.getTo();

            Vertex u = vertices.get(from);
            Vertex v = vertices.get(to);

            Line2 line = new Line2(u.getCoordinates(), v.getCoordinates());
            lines.add(line);
        }
    }

    /**
     * Computes a point P' along the line from p1 to p2, at a distance equal to the
     * vertex radius from p1. Used to draw arrowheads that start outside of the node circle.
     * 
     * @param p1 The starting point (e.g. center of the vertex)
     * @param p2 The ending point (e.g. direction of the arrow)
     * @return A point on the line, offset from p1 by the radius
     */
    private Point arrowP(Point p1, Point p2) {
        int newX = p2.x - p1.x;
        int newY = p2.y - p1.y;

        int arrowLength = Circle2.getRadius();

        Point2D.Double director = new Point2D.Double(newX, newY);
        double lengthDirector = Math.sqrt(newX * newX + newY * newY);

        // Normalize the direction vector
        director.x = newX / lengthDirector;
        director.y = newY / lengthDirector;

        int pX = (int) (p1.x + arrowLength * director.x);
        int pY = (int) (p1.y + arrowLength * director.y);

        // Ensure positive coordinates (necessary, since the display doesn't have negative coordinates)
        if (pX < 0) pX = -pX;
        if (pY < 0) pY = -pY;

        return new Point(pX, pY);
    }

    /**
     * Draws the arrowhead at the end of a line between two points.
     * 
     * @param g2d The graphics context
     * @param p1  The start of the line
     * @param p2  The end of the line where the arrowhead is drawn
     */
    private void drawArrowHead(Graphics2D g2d, Point p1, Point p2) {
        double phi = Math.toRadians(30); // Arrowhead angle
        int barb = 15; // Length of the arrowhead lines

        double dy = p2.y - p1.y;
        double dx = p2.x - p1.x;
        double theta = Math.atan2(dy, dx); // Line angle

        for (int i = 0; i < 2; i++) {
            double rho = theta + (i == 0 ? phi : -phi);
            double x = p2.x - barb * Math.cos(rho);
            double y = p2.y - barb * Math.sin(rho);

            g2d.drawLine(p2.x, p2.y, (int) x, (int) y);
        }
    }

    /**
     * Draws all edges as lines with arrowheads.
     * If the edge is bidirectional, it draws arrowheads in both directions.
     * 
     * @param g2d The graphics context used for drawing
     */
    public void drawEdges(Graphics2D g2d) {
        // Enable anti-aliasing for smoother lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int i = 0;
        for (Line2 e : lines) {
            // Draw line and arrow from source to destination
            Point p = arrowP(e.getPoint2(), e.getPoint1());
            e.draw(g2d);
            drawArrowHead(g2d, e.getPoint1(), p);

            // If the edge is bidirectional, draw the opposite arrow
            if (edges.get(i).isBidirectional()) {
                p = arrowP(e.getPoint1(), e.getPoint2());
                drawArrowHead(g2d, e.getPoint2(), p);
            }

            // Uncomment the following line to display edge labels
            // drawEdgeLabel(g2d, edges.get(i), e);

            i++;
        }
    }

    /**
     * Draws the label for a given edge at the midpoint of its corresponding line.
     * 
     * @param g2d  The graphics context
     * @param edge The edge whose label is being drawn
     * @param line The line corresponding to the edge
     */
    public void drawEdgeLabel(Graphics2D g2d, Edge edge, Line2 line) {
        int mx = (line.getPoint2().x + line.getPoint1().x) / 2;
        int my = (line.getPoint2().y + line.getPoint1().y) / 2;

        g2d.setColor(Color.RED);
        g2d.drawString(edge.toString(), mx, my);
    }

    /**
     * Returns an unmodifiable view of the drawable lines.
     * 
     * @return A read-only list of Line2 objects representing the edges
     */
    public List<Line2> getLines() {
        return Collections.unmodifiableList(lines);
    }

} // EdgeLine2