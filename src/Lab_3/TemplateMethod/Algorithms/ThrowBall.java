package Lab_3.TemplateMethod.Algorithms;

import Lab_3.TemplateMethod.DynamicPaintingPanel;
import Lab_3.TemplateMethod.Shape.Ball;
import Lab_3.TemplateMethod.Shape.DrawableShape;

public class ThrowBall extends ThrowShapeAlgorithm {

    public ThrowBall(DynamicPaintingPanel panel) {
        super(panel);
    }

    @Override
    protected DrawableShape doCreateShape(int width, int height) {
        Ball ball = new Ball(0, 0);
        ball.setX(width - ball.getWidth());
        ball.setY(height - ball.getHeight());
        return ball;
    }

}
