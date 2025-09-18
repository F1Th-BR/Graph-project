package graphviewer;


import graph.*;
import java.awt.Graphics;
import javax.swing.JPanel;

import geom.Circle2;

import java.awt.Point;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

public class GraphPanel extends JPanel {
    private final EdgeLine2 edgeLine2;
    private final VertexCircle2 vertexCircle2;

    public GraphPanel(Graph g) {
        List<Vertex> vertices = g.getVertices();
        List<Edge> edges = g.getEdges();
        generateRandomPositions(vertices);

        boolean changesMade;
        do {
            changesMade = false;
            for (Vertex u : vertices) {
                double distance;
                for (Vertex v : vertices) {
                    distance = g.distanceBetweenPoints(u.getCoordinates(), v.getCoordinates());
    
                    if(distance <= 4*(Circle2.getRadius()) && u != v) {
                        regenPosition(u);
                        changesMade = true;
                    }
                }
            }
    
            for (Vertex w : vertices) {
                Point wCoordinates = new Point(w.getCoordinates());
                
                Vertex u;
                Vertex v;
                double distance;
                for (Edge e : edges) {
                    u = vertices.get(e.getFrom());
                    v = vertices.get(e.getTo());
    
                    Point uCoordinates = new Point(u.getCoordinates());
                    Point vCoordinates = new Point(v.getCoordinates());
    
                    distance = g.distanceBetweenPointLine(uCoordinates, vCoordinates, wCoordinates);
                    
                    if(distance <= 4*(Circle2.getRadius()) && w != u && w != v) {
                        regenPosition(w);
                        changesMade = true;
                    }
                }
                
            }
            
        } while (changesMade);
        
        this.edgeLine2 = new EdgeLine2(edges, vertices);
        this.vertexCircle2 = new VertexCircle2(vertices);

        edgeLine2.createEdgesShapes();

        // Create Circle2 shapes for all vertices
        vertexCircle2.createVerticesShapes();
    }

        // Randomly position vertices within panel
    private void generateRandomPositions(List<Vertex> vertices) {
        Random rand = new Random();
        int width = 800;
        int height = 600;

        for (Vertex v : vertices) {
            int x = 100 + rand.nextInt(width - 200);
            int y = 100 + rand.nextInt(height - 200);
            v.setCoordinates(new Point(x, y));
        }
    }

    private void regenPosition(Vertex v) {
        Random rand = new Random();
        int width = 800;
        int height = 600;

        int x = 100 + rand.nextInt(width - 200);
        int y = 100 + rand.nextInt(height - 200);
        v.setCoordinates(new Point(x, y));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        // Draw edges first
        edgeLine2.drawEdges(g2d);

        // Draw vertices (circles)
        vertexCircle2.draw(g2d);
    }
}
