package Lab_3.ChainOfResponsibility;

import Lab_1.FactoryMethod.Transport;
import Lab_1.FactoryMethod.TransportStaticMethod;
import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_1.FactoryMethod.factory.TransportFactoryMethod;

import static Lab_1.FactoryMethod.Lab_1_2.CAR;

public class Lab_3_1 {

    public static void main(String[] args) throws Exception {
        Transport transportWithTwoModels = createCar(2);
        setChain(transportWithTwoModels);
        Transport transportWithFourModels = createCar(4);
        setChain(transportWithFourModels);
    }

    public static Transport createCar(int num) throws Exception {
        TransportFactory carFactory = TransportFactoryMethod.getTransportFactory(CAR);
        TransportStaticMethod.setTransportFactory(carFactory);
        Transport car = TransportStaticMethod.createInstance("Subaru", 0);
        for (int i = 0; i <= num; i++) {
            car.addModel("NewModel" + i, i+1);
        }
        return car;
    }

    private static void setChain(Transport transport) {
        TransportChainHandler firstChain = new TransportPrintInLine();
        TransportChainHandler secondChain = new TransportPrintInColumn();
        firstChain.setNextHandler(secondChain);
        firstChain.handle(transport);
    }
}
