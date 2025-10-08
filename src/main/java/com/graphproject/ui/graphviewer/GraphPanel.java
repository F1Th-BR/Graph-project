package com.graphproject.ui.graphviewer;

import com.graphproject.domain.graph.*;
import com.graphproject.domain.seed.*;
import com.graphproject.ui.components.*;

import java.util.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */
 
/**
 * Represents the panel that displays the graph.
 * 
 * This class extends JPanel and is responsible for rendering the graph, including its
 * edges (as lines) and vertices (as circles with associated images). The graph's
 * positions are determined by a random seed and validated to ensure no overlapping vertices.
 */
public class GraphPanel extends JPanel {
    
    private final EdgeLine2 edgeLine2;     // Handles drawing of edges
    private final VertexCircle2 vertexCircle2; // Handles drawing of vertices
    private long seed;                    // The seed used for random vertex positions

    /**
     * Constructs a GraphPanel object that will display the given graph.
    * 
    * @param g The graph to be displayed on this panel.
    */
    public GraphPanel(Graph g) {
        List<Edge> edges = g.getEdges();
        List<Vertex> vertices = g.getVertices();
        
        // Validate and generate the seed for the graph
        SeedValidator seedValidator = new SeedValidator(g);
        this.seed = seedValidator.getSeed();

        // Generate random positions for the vertices based on the seed
        generateRandomPositions(this.seed, vertices, seedValidator.getScreenSize());

        // Initialize the edge and vertex renderers
        this.edgeLine2 = new EdgeLine2(edges, vertices);
        this.vertexCircle2 = new VertexCircle2(vertices);

        // Print debug information
        System.out.println("Graph ready after " + seedValidator.getTries() + " tries");

        // Create shapes for edges and vertices
        edgeLine2.createEdgesShapes();
        vertexCircle2.createVerticesShapes();
    }

    /**
     * Randomly positions vertices within the panel's screen bounds.
    * Ensures that vertices are placed with some padding from the edges.
    * 
    * @param seed       The seed for randomization.
    * @param vertices   The list of vertices to be positioned.
    * @param screenSize The size of the screen or panel where the vertices will be placed.
    */
    private void generateRandomPositions(long seed, List<Vertex> vertices, Dimension screenSize) {
        Random rand = new Random(seed);
        int width = screenSize.width;
        int height = screenSize.height;

        for (Vertex v : vertices) {
            // Ensure vertices are placed with a margin to avoid being too close to edges
            int x = 100 + rand.nextInt(width - 200);
            int y = 100 + rand.nextInt(height - 200);
            v.setCoordinates(new Point(x, y));
        }
    }

    /**
     * Paints the graph on the panel by drawing the edges and vertices.
    * This method is called automatically when the panel needs to be re-rendered.
    * 
    * @param g The Graphics object used for drawing.
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw edges first, as they should appear below the vertices
        edgeLine2.drawEdges(g2d);

        // Draw vertices (circles) after edges to ensure they appear on top
        vertexCircle2.draw(g2d);
    }
}
