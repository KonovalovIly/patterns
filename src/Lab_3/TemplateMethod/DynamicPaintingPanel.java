package Lab_3.TemplateMethod;

import Lab_3.TemplateMethod.Shape.DrawableShape;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DynamicPaintingPanel extends JPanel {

    private final Dimension dimension = new Dimension(320, 273);
    private final LinkedList<DrawableShape> shapes = new LinkedList<>();

    public DynamicPaintingPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        shapes.forEach((s) -> {
            s.draw(g);
        });
    }

    public void addShape(DrawableShape shape) {
        shapes.add(shape);
        repaint();
    }

    public Dimension getDimension() {
        return dimension;
    }
}
