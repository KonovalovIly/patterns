package Lab_1.FactoryMethod;

import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_1.FactoryMethod.factory.TransportFactoryMethod;

public class Lab_1_2 {

    public static final String CAR = "Car";
    public static final String MOTORCYCLE = "Motorcycle";

    public static void main(String[] args) throws Exception {
        checkMotorcycleFactory();
        checkCarFactory();
    }

    private static void checkMotorcycleFactory() throws Exception {
        System.out.println("Creating motorcycle:");
        TransportFactory motorcycleFactory = TransportFactoryMethod.getTransportFactory(MOTORCYCLE);
        TransportStaticMethod.setTransportFactory(motorcycleFactory);
        Transport motorcycle = TransportStaticMethod.createInstance("Suzuki", 10);
        System.out.println(TransportStaticMethod.getFactory());
        motorcycle.modificationNameModelByName("Suzuki1","GSX-S1000GT");
        System.out.println(TransportStaticMethod.getAveragePrice(motorcycle));
        TransportStaticMethod.printModels(motorcycle);
        TransportStaticMethod.printModelsPrices(motorcycle);
    }

    private static void checkCarFactory() throws Exception {
        System.out.println("Creating auto:");
        TransportFactory carFactory = TransportFactoryMethod.getTransportFactory(CAR);
        TransportStaticMethod.setTransportFactory(carFactory);
        Transport car = TransportStaticMethod.createInstance("Subaru", 4);
        System.out.println(TransportStaticMethod.getFactory());
        car.modificationNameModelByName("Subaru1", "Impreza");
        car.modificationPriceModelByName("Impreza", 12314.2);
        System.out.println(TransportStaticMethod.getAveragePrice(car));
        TransportStaticMethod.printModels(car);
        TransportStaticMethod.printModelsPrices(car);
    }
}
