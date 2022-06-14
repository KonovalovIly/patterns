package Lab_1.FactoryMethod.factory;

import Lab_1.FactoryMethod.Transport;

public interface TransportFactory {

    Transport createInstance(String mark, int arraySize);

}
