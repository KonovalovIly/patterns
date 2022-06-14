package Lab_3.TemplateMethod.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Star implements DrawableShape {

    private final int width;
    private int x, y;

    private final int[] xPoints = {9, 15, 0, 18, 3};
    private final int[] yPoints = {0, 18, 6, 6, 18};

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
        width = 18;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GeneralPath star = new GeneralPath();

        star.moveTo(xPoints[0] + x, yPoints[0] + y);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i] + x, yPoints[i] + y);
        }
        star.closePath();

        g2d.setColor(Color.YELLOW);
        g2d.fill(star);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int value) {
        x = value;
    }

    @Override
    public void setY(int value) {
        y = value;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return width;
    }
}
