package com.graphproject.core.geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Abstract superclass defining common properties and behaviors for shapes.
 *
 * Stores the position and color of a shape and requires subclasses
 * to implement the drawing logic.
 */
public abstract class Shape2 {

    /* ===== ATTRIBUTES ===== */

    /** The reference point representing the position (x, y) of the shape. */
    protected Point p;

    /** The color of the shape. */
    protected Color color;

    /* ===== CONSTRUCTORS ===== */

    /**
     * Constructs a Shape2 with a specified position and color.
     * 
     * @param p     The position point of the shape
     * @param color The color of the shape
     */
    Shape2(Point p, Color color) {
        this.p = p;
        this.color = color;
    }

    /* ===== ABSTRACT METHODS ===== */

    /**
     * Draws the shape using the provided graphics context.
     * Must be implemented by subclasses.
     * 
     * @param g2d The Graphics2D context for drawing
     */
    public abstract void draw(Graphics2D g2d);

    /* ===== GETTERS ===== */

    /**
     * Returns the current color of the shape.
     * 
     * @return The shape's color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the position point of the shape.
     * 
     * @return The shape's position as a Point
     */
    public Point getPoint() {
        return this.p;
    }

    /* ===== SETTERS ===== */

    /**
     * Sets the color of the shape.
     * 
     * @param color The new color to assign
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the position point of the shape.
     * 
     * @param p The new position point
     */
    public void setPoint(Point p) {
        this.p = p;
    }
} // Shape2
