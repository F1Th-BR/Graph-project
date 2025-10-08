package com.graphproject.core.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * A graphical representation of a circle, extending the Shape2 class.
 * 
 * This class provides methods to draw a circle with a configurable center point, color,
 * and radius. It also supports default styling for consistent use across the application.
 */
public class Circle2 extends Shape2 {

    /* ===== STATIC ATTRIBUTES ===== */

    /** Default radius of the circle (in pixels). */
    private static int radius = 20;

    /** Default color used for circle instances without explicit color. */
    private static Color defaultColor = Color.BLUE;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs a Circle2 object with a specified center and color.
     * 
     * @param p     The center point of the circle
     * @param color The fill color of the circle
     */
    public Circle2(Point p, Color color) {
        super(p, color);
    }

    /**
     * Constructs a Circle2 object with a specified center point and
     * uses the default color.
     * 
     * @param p The center point of the circle
     */
    public Circle2(Point p) {
        super(p, defaultColor);
    }

    /* ===== METHODS ===== */

    /**
     * Draws the circle on the given Graphics2D context.
     * 
     * @param g2d The graphics context used for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(p.x - radius, p.y - radius, radius * 2, radius * 2);
    }

    /**
     * Returns a copy of the center point of the circle.
     * 
     * @return A new Point object representing the center
     */
    public Point getCenter() {
        return new Point(super.getPoint());
    }

    /**
     * Gets the current default color for all Circle2 instances.
     * 
     * @return The default Color used by Circle2
     */
    public static Color getDefaultColor() {
        return defaultColor;
    }

    /**
     * Gets the current default radius for all Circle2 instances.
     * 
     * @return The radius value in pixels
     */
    public static int getRadius() {
        return radius;
    }

    /**
     * Sets the default color used by Circle2 when no color is specified.
     * 
     * @param color The new default color
     */
    public static void setDefaultColor(Color color) {
        defaultColor = color;
    }

    /**
     * Sets the radius of the circle.
     * This change will affect all future Circle2 instances.
     * 
     * @param r The new radius in pixels
     */
    public static void setRadius(int r) {
        radius = r;
    }

} // Circle2
