package graphviewer;


import graph.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.List;

public class GraphPanel extends JPanel {
    private final EdgeLine2 edgeLine2;
    private final VertexCircle2 vertexCircle2;

    public GraphPanel(List<Edge> edges, List<Vertex> vertices) {
        this.edgeLine2 = new EdgeLine2(edges, vertices);
        this.vertexCircle2 = new VertexCircle2(vertices);

        edgeLine2.createEdgesShapes();

        // Create Circle2 shapes for all vertices
        vertexCircle2.createVerticesShapes();
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
