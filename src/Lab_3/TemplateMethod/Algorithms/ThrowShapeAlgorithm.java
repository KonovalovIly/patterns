package Lab_3.TemplateMethod.Algorithms;

import Lab_3.TemplateMethod.DynamicPaintingPanel;
import Lab_3.TemplateMethod.Shape.DrawableShape;

public abstract class ThrowShapeAlgorithm {

    protected DrawableShape shape = null;
    protected DynamicPaintingPanel panel;

    private int dx = -1;
    private int dy = -1;

    private int x = 0;
    private int y = 0;

    public ThrowShapeAlgorithm(DynamicPaintingPanel panel) {
        this.panel = panel;
    }

    public void throwShape() {
        int width = panel.getDimension().width;
        int height = panel.getDimension().height;

        shape = doCreateShape(width, height);
        addShapeToPanel();
        x = shape.getX();
        y = shape.getY();

        Side touchingSide;

        while (true) {
            updateCoordinates();
            updateShape();

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                System.out.print(ex.getMessage());
            }

            touchingSide = getTouchingSide();
            if (touchingSide != Side.NO_SIDE) {
                updateMovement(touchingSide);
            }
        }
    }

    protected abstract DrawableShape doCreateShape(int width, int height);

    protected void addShapeToPanel() {
        panel.addShape(shape);
    }

    protected Side getTouchingSide() {
        int width = panel.getDimension().width;
        int height = panel.getDimension().height;

        if (x == 0) {
            if (y == 0) {
                return Side.TOP_LEFT;
            }
            if (y == height - shape.getHeight()) {
                return Side.BOTTOM_LEFT;
            }
            return Side.LEFT;
        }
        if (y == 0) {
            if (x == width - shape.getWidth()) {
                return Side.TOP_RIGHT;
            }
            return Side.TOP;
        }
        if (x == width - shape.getWidth()) {
            if (y == height - shape.getHeight()) {
                return Side.BOTTOM_RIGHT;
            }
            return Side.RIGHT;
        }
        if (y == height - shape.getHeight()) {
            return Side.BOTTOM;
        }

        return Side.NO_SIDE;
    }

    protected void updateCoordinates() {
        x = x + dx;
        y = y + dy;
    }

    protected void updateShape() {
        shape.setX(x);
        shape.setY(y);
        panel.revalidate();
        panel.repaint();
    }

    protected void updateMovement(Side side) {
        if ((side == Side.BOTTOM) || (side == Side.TOP)) {
            dy = -1 * dy;
        } else if ((side == Side.LEFT) || (side == Side.RIGHT)) {
            dx = -1 * dx;
        } else {
            dx = -1 * dx;
            dy = -1 * dy;
        }
    }
}
