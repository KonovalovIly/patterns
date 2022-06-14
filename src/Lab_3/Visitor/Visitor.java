package Lab_3.Visitor;

import Lab_1.FactoryMethod.Motorcycle;
import Lab_1.FactoryMethod.Car;

public interface Visitor {
    void visit(Car car);
    void visit(Motorcycle motorcycle);
}
