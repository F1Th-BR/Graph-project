package com.graphproject.core.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * A drawable line segment between two points for graphical use.
 *
 * Inherits positioning and color from the Shape2 class.
 */
public class Line2 extends Shape2 {

    /* ===== ATTRIBUTES ===== */
    
    /** The second endpoint of the line (the first is inherited as 'p'). */
    private Point p2;

    /* ===== STATIC ATTRIBUTES ===== */
    
    /** Default color used for line instances without explicit color. */
    private static Color defaultColor = Color.BLACK;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs a Line2 object with two endpoints and the default color.
     * 
     * @param p1 The starting point of the line
     * @param p2 The ending point of the line
     */
    public Line2(Point p1, Point p2) {
        super(p1, defaultColor);
        this.p2 = p2;
    }

    /* ===== METHODS ===== */

    /**
     * Draws the line segment using the current graphics context.
     * 
     * @param g2d The Graphics2D context for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawLine(p.x, p.y, p2.x, p2.y);
    }

    /**
     * Returns a copy of the first point (p1) of the line.
     * 
     * @return A new Point object representing the starting point
     */
    public Point getPoint1() {
        return new Point(super.getPoint()); // Defensive copy
    }

    /**
     * Returns a copy of the second point (p2) of the line.
     * 
     * @return A new Point object representing the ending point
     */
    public Point getPoint2() {
        return new Point(p2); // Defensive copy
    }

} // Line2
