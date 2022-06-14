package Lab_1.FactoryMethod.factory;

import Lab_1.FactoryMethod.Motorcycle;
import Lab_1.FactoryMethod.Transport;

public class MotorcycleFactory implements TransportFactory {

    @Override
    public Transport createInstance(String mark, int arraySize) {
        return new Motorcycle(mark, arraySize);
    }
}
