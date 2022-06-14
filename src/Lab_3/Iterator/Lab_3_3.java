package Lab_3.Iterator;

import Lab_1.FactoryMethod.Car;

import java.util.Iterator;

public class Lab_3_3 {

    public static void main(String[] args) {
        Car car = new Car("IterableCar", 6);
        Iterator iterator = car.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
