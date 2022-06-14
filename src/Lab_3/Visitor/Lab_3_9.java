package Lab_3.Visitor;

import Lab_1.FactoryMethod.Transport;
import Lab_1.FactoryMethod.TransportStaticMethod;
import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_1.FactoryMethod.factory.TransportFactoryMethod;

import static Lab_1.FactoryMethod.Lab_1_2.CAR;
import static Lab_1.FactoryMethod.Lab_1_2.MOTORCYCLE;

public class Lab_3_9 {

    public static void main(String[] args) throws Exception {
        checkCarVisitor();
        System.out.println();System.out.println();
        checkMotorcycleVisitor();

    }

    private static void checkCarVisitor() throws Exception {
        System.out.println("Creating auto:");
        TransportFactory carFactory = TransportFactoryMethod.getTransportFactory(CAR);
        TransportStaticMethod.setTransportFactory(carFactory);
        Transport car = TransportStaticMethod.createInstance("Subaru", 4);
        car.modificationNameModelByName("Subaru1", "Impreza");
        car.modificationPriceModelByName("Impreza", 12314.2);
        car.accept(new VisitorImpl());
    }

    private static void checkMotorcycleVisitor() throws Exception {
        System.out.println("Creating motorcycle:");
        TransportFactory motorcycleFactory = TransportFactoryMethod.getTransportFactory(MOTORCYCLE);
        TransportStaticMethod.setTransportFactory(motorcycleFactory);
        Transport motorcycle = TransportStaticMethod.createInstance("Suzuki", 10);
        motorcycle.modificationNameModelByName("Suzuki1", "GSX-S1000GT");
        motorcycle.accept(new VisitorImpl());
    }
}
