package Lab_3.TemplateMethod.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Square implements DrawableShape {

    private final int width;

    private int x, y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        width = 20;
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoints = {0, width, width, 0};
        int[] yPoints = {0, 0, width, width};

        Graphics2D g2d = (Graphics2D) g;
        GeneralPath square = new GeneralPath();

        square.moveTo(xPoints[0] + x, yPoints[0] + y);
        for (int i = 1; i < xPoints.length; i++) {
            square.lineTo(xPoints[i] + x, yPoints[i] + y);
        }
        square.closePath();

        g2d.setColor(Color.BLUE);
        g2d.fill(square);
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
