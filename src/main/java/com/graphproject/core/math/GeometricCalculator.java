package com.graphproject.core.math;

import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

/**
 * Interface declaring geometric methods for distance calculations and
 * related geometric operations.
 */
public interface GeometricCalculator {

    /**
     * Calculates the distance between two points.
     * 
     * @param p1 The first point
     * @param p2 The second point
     * @return The distance as a double
     */
    double distanceBetweenPoints(Point p1, Point p2);

    /**
     * Calculates the shortest distance between a point and a line defined by two points.
     * 
     * @param p1Line The first point defining the line
     * @param p2Line The second point defining the line
     * @param p      The point from which the distance is measured
     * @return The shortest distance from the point to the line
     */
    double distanceBetweenPointLine(Point p1Line, Point p2Line, Point p);

    /**
     * Calculates the direction vector of the line defined by two points.
     * 
     * @param p1 The first point defining the line
     * @param p2 The second point defining the line
     * @return A Point representing the direction vector of the line
     */
    Point directorLine(Point p1, Point p2);
} // GeometricCalculator
