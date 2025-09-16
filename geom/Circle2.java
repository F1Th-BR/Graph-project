package geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

public class Circle2 extends Shape2 {
    private static int radius = 12; // default
    private static Color defaultColor = Color.BLUE;

    public Circle2(Point p, Color color) {
        super(p, color); // p is the center
    }

    public Circle2(Point p) {
        super(p, defaultColor);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(p.x - radius, p.y - radius, radius*2, radius*2);
    }

    public Point getCenter() {
        Point center = super.getPoint();
        return new Point(center);
    } // returns a copy of the center coordinates

    public static Color getDefaultColor() {
        return defaultColor;
    }

    public static int getRadius() {
        return radius;
    }


    public static void setDefaultColor(Color color) {
        defaultColor = color;
    }

    public static void setRadius(int r) {
        radius = r;
    }

    
}
