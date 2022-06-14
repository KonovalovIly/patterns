package Lab_3.TemplateMethod.Shape;

import java.awt.Graphics;

public interface DrawableShape {

    int getX();

    int getY();

    void setX(int value);

    void setY(int value);

    int getWidth();

    int getHeight();

    void draw(Graphics g);
}
