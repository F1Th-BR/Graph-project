package graphviewer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.*;

import graph.*;
import geom.Circle2;
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

    private Point arrowP(Point p1, Point p2) {
        int newX = p2.x-p1.x;
        int newY = p2.y-p1.y;
        int pX, pY;
        
        int arrowLength = Circle2.getRadius();

        Point2D.Double director = new Point2D.Double(newX, newY);
        double lengthDirector = Math.sqrt(Math.pow(newX, 2) + Math.pow(newY, 2));
        
        director.x = newX/lengthDirector;
        director.y = newY/lengthDirector;
        
        pX = (int) (p1.x + arrowLength *  director.x);
        pY = (int) (p1.y + arrowLength * director.y);
        if(pX < 0)
            pX = -pX;
        if(pY < 0) 
            pY = -pY;
        return new Point(pX, pY);
    }
    

    private void drawArrowHead(Graphics2D g2d, Point p1, Point p2) {
        double phi = Math.toRadians(30);
        int barb = 15;

        double dy = p2.y - p1.y;
        double dx = p2.x - p1.x;
        double theta = Math.atan2(dy, dx);

        double x, y;
        for(int i = 0; i < 2; i++) {
            double rho = theta + (i == 0 ? phi : -phi);
            x = p2.x - barb * Math.cos(rho);
            y = p2.y - barb * Math.sin(rho);

            g2d.drawLine(p2.x, p2.y, (int) x, (int) y);
        }

    }

    public void drawEdges(Graphics2D g2d) {
        int i = 0;
        Point p;
        for (Line2 e : lines) {
            p = arrowP(e.getPoint2(), e.getPoint1());
            e.draw(g2d);
            
            drawArrowHead(g2d, e.getPoint1(), p);
            if(edges.get(i).isBidirectional()) {
                p = arrowP(e.getPoint1(), e.getPoint2());
                drawArrowHead(g2d, e.getPoint2(), p);
            }

            drawEdgeLabel(g2d, edges.get(i), e);
            i++;
        }
    }

    public void drawEdgeLabel(Graphics2D g2d, Edge edge, Line2 line) {
        int mx = (line.getPoint2().x + line.getPoint1().x) / 2;
        int my = (line.getPoint2().y + line.getPoint1().y) / 2;
        
        g2d.setColor(Color.RED);
        g2d.drawString(edge.toString(), mx, my);
    }

    public List<Line2> getLines() {
        return Collections.unmodifiableList(lines);
    }

}

