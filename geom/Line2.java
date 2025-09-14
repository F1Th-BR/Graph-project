package geom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line2 extends Shape2 {
    private Point p2;
    private static Color defaultColor = Color.BLACK;

    public Line2(Point p1, Point p2) {
        super(p1, defaultColor);
        this.p2 = p2;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawLine(p.x, p.y, p2.x, p2.y);
        
    }

    public Point getPoint1() {
        Point p1 = super.getPoint();
        return new Point(p1); // defensive copy
    }

    public Point getPoint2() {
        return new Point(p2);
    }

}
