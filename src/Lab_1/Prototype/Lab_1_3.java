package Lab_1.Prototype;

import Lab_1.FactoryMethod.Transport;
import Lab_1.FactoryMethod.TransportStaticMethod;
import Lab_1.FactoryMethod.exception.DuplicateModelNameException;
import Lab_1.FactoryMethod.exception.NoSuchModelNameException;
import Lab_1.FactoryMethod.factory.TransportFactory;
import Lab_1.FactoryMethod.factory.TransportFactoryMethod;

import static Lab_1.FactoryMethod.Lab_1_2.CAR;
import static Lab_1.FactoryMethod.Lab_1_2.MOTORCYCLE;

public class Lab_1_3 {

    public static void main(String[] args) throws Exception {
        checkCarCopy();
        checkMotorcycleCopy();
    }

    private static void checkCarCopy() throws Exception {
        TransportFactory carFactory = TransportFactoryMethod.getTransportFactory(CAR);
        TransportStaticMethod.setTransportFactory(carFactory);
        Transport car = TransportStaticMethod.createInstance("Subaru", 4);
        car.modificationNameModelByName("Subaru1", "Impreza");
        car.modificationPriceModelByName("Impreza", 12314.2);
        car.deleteModel("Subaru2");
        System.out.println("Original car: ");
        System.out.println(car);
        Transport motorcycleClone = car.clone();
        System.out.println("Copy car: ");
        System.out.println(motorcycleClone);
    }

    private static void checkMotorcycleCopy() throws Exception {
        TransportFactory motorcycleFactory = TransportFactoryMethod.getTransportFactory(MOTORCYCLE);
        TransportStaticMethod.setTransportFactory(motorcycleFactory);
        Transport motorcycle = TransportStaticMethod.createInstance("Suzuki", 10);
        motorcycle.modificationNameModelByName("Suzuki1", "GSX-S1000GT");
        motorcycle.modificationPriceModelByName("GSX-S1000GT", 123.123);
        motorcycle.addModel("KATANA", 13.499);
        motorcycle.deleteModel("Suzuki5");
        System.out.println("Original motorcycle: ");
        System.out.println(motorcycle);
        Transport motorcycleClone = motorcycle.clone();
        System.out.println("Copy motorcycle: ");
        System.out.println(motorcycleClone);
        checkClonable(motorcycle, motorcycleClone);
    }

    private static void checkClonable(Transport motorcycle, Transport motorcycleClone) throws DuplicateModelNameException, NoSuchModelNameException {
        motorcycle.modificationNameModelByName("KATANA", "NewModel");
        System.out.println("Original motorcycle: ");
        System.out.println(motorcycle);
        System.out.println("Copy motorcycle: ");
        System.out.println(motorcycleClone);
    }

}
