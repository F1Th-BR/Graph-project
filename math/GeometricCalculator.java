package math;

import java.awt.Point;

public interface GeometricCalculator {
    public double distanceBetweenPoints(Point p1, Point p2);
    public double distanceBetweenPointLine(Point p1Line, Point p2Line, Point p);    
    public Point directorLine(Point p1, Point p2);
}