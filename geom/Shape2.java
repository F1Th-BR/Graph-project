package geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * 
 * @author Thiago Feijó de Albuquerque
 */

public abstract class Shape2 {
    protected Point p; // a point to store the coordinates (x, y)
    protected Color color; // color of the shape

    Shape2(Point p, Color color) {
        this.p = p;
        this.color = color;
    }

    public abstract void draw(Graphics2D g2d);
    
    public Color getColor() {
        return this.color;
    }

    public Point getPoint() {
        return this.p;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPoint(Point p) {
        this.p = p;
    }
}
