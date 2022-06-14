package Lab_3.Memento;

import Lab_1.FactoryMethod.Car;
import Lab_1.FactoryMethod.Car.AutoMemento;
import Lab_1.FactoryMethod.exception.DuplicateModelNameException;

import java.io.IOException;

public class Lab_3_4 {

    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException {
        Car car = new Car("MementableCar", 6);
        AutoMemento memento = car.createMemento();
        System.out.println("Initial car:");
        System.out.println(car);
        System.out.println("----------------------");

        car.addModel("NewModel", 123);
        System.out.println("Changed car:");
        System.out.println(car);
        System.out.println("----------------------");

        car = car.setMemento(memento);

        System.out.println("Car from memento:");
        System.out.println(car);
        System.out.println("----------------------");

    }
}
