package Lab_3.Visitor;

import Lab_1.FactoryMethod.Car;
import Lab_1.FactoryMethod.Motorcycle;

public class VisitorImpl implements Visitor{

    @Override
    public void visit(Car car) {
        System.out.print(car.getMark());
        System.out.print(" ");
        String[] names = car.getNameAllModels();
        double[] prices = car.getPricesAllModels();
        for (int i = 0; i < car.getModelsSize(); i++) {
            System.out.print(names[i]);
            System.out.print(" ");
            System.out.print(prices[i]);
            System.out.print(" ");
        }
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        System.out.println(motorcycle.getMark());
        String[] names = motorcycle.getNameAllModels();
        double[] prices = motorcycle.getPricesAllModels();
        for (int i = 0; i < motorcycle.getModelsSize(); i++) {
            System.out.println(names[i]);
            System.out.println(prices[i]);
        }
    }
}
