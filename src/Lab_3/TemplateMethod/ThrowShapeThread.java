package Lab_3.TemplateMethod;

import Lab_3.TemplateMethod.Algorithms.ThrowShapeAlgorithm;

public class ThrowShapeThread extends Thread {

    private final ThrowShapeAlgorithm algorithm;

    public ThrowShapeThread(ThrowShapeAlgorithm alg) {
        algorithm = alg;
    }

    @Override
    public void run() {
        algorithm.throwShape();
    }
}
