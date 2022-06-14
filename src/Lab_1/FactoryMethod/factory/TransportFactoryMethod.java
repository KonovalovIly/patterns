package Lab_1.FactoryMethod.factory;

import static Lab_1.FactoryMethod.Lab_1_2.CAR;
import static Lab_1.FactoryMethod.Lab_1_2.MOTORCYCLE;

public class TransportFactoryMethod {

    public static TransportFactory getTransportFactory(String transportType) throws Exception {
        switch (transportType) {
            case (CAR):
                return new CarFactory();
            case (MOTORCYCLE):
                return new MotorcycleFactory();
            default:
                throw new Exception("Unknown type transport");
        }
    }

}
