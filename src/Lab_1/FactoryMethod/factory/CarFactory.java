package Lab_1.FactoryMethod.factory;

import Lab_1.FactoryMethod.Car;
import Lab_1.FactoryMethod.Transport;

public class CarFactory implements TransportFactory {

    @Override
    public Transport createInstance(String mark, int arraySize) {
        return new Car(mark, arraySize);
    }
}
